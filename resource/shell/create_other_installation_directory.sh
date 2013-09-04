#!/bin/sh
#
# create_other_installation_directory.sh
#
# Copyright (C) 2013 NTT Software Corporation.

ip_address=$1
installation_directory=$2
root_password=$3
superuser=$4

expect -c "
	set timeout 5
	spawn ssh root@$ip_address \"mkdir $installation_directory\"
	expect {
		\"Are you sure you want to continue connecting (yes/no)?\" {
			send \"yes\n\"
			exp_continue
		}
		\"root@$ip_address's password:\" {
			send \"$root_password\n\"
		}
	}
	interact"

if [ $? -ne 0 ]; then
	exit 100
fi

expect -c "
        set timeout 5
        spawn ssh root@$ip_address \"chown $superuser:$superuser $installation_directory\"
        expect {
                \"Are you sure you want to continue connecting (yes/no)?\" {
                        send \"yes\n\"
                        exp_continue
                }
                \"root@$ip_address's password:\" {
                        send \"$root_password\n\"
                }
        }
        interact"

if [ $? -ne 0 ]; then
	exit 100
fi

exit 0
