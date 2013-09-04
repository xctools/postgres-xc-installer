#!/bin/sh
#
# construct_nopass_ssh.sh
#
# Copyright (C) 2013 NTT Software Corporation.

username=$1
password=$2
ipaddress_list=$3

su $username -c "rm -fr ~/.ssh; mkdir ~/.ssh; ssh-keygen -t rsa -f ~/.ssh/id_rsa -N ''; chmod 700 ~/.ssh; chmod 600 ~/.ssh/*"

if [ $? -ne 0 ]; then
	exit 100
fi

id_rsa_pub=`su $username -c "cat ~/.ssh/id_rsa.pub"`

if [ $? -ne 0 ]; then
	exit 100
fi

for ipaddress in $ipaddress_list
do
	expect -c "
	set timeout 5
	spawn ssh $username@$ipaddress \"rm -fr ~/.ssh\"
	expect {
		\"Are you sure you want to continue connecting (yes/no)?\" {
			send \"yes\n\"
			exp_continue
		}
		\"$username@$ipaddress's password:\" {
			send \"$password\n\"
		}
        }
	interact
	"
done

if [ $? -ne 0 ]; then
	exit 100
fi

for ipaddress in $ipaddress_list
do
	su $username -c "
		expect -c \"
		set timeout 5
		spawn ssh $username@$ipaddress \\\"mkdir ~/.ssh; echo $id_rsa_pub >> ~/.ssh/authorized_keys; echo $id_rsa_pub >> ~/.ssh/authorized_keys2; chmod 700 ~/.ssh; chmod 600 ~/.ssh/*\\\"
		expect {
			\\\"Are you sure you want to continue connecting (yes/no)?\\\" {
				send \\\"yes\\n\\\"
				exp_continue
			}
			\\\"$username@$ipaddress's password:\\\" {
				send \\\"$password\\n\\\"
			}
		}
		interact
		\"
	"
done

if [ $? -ne 0 ]; then
	exit 100
fi

exit 0
