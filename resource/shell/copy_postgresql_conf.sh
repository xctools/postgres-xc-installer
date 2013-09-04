#!/bin/sh
#
# copy_postgresql_conf.sh
#
# Copyright (C) 2013 NTT Software Corporation.

postgresql_conf=$1
ip_address=$2
data_directory=$3
root_password=$4
superuser=$5
installation_directory=$6
coordinator_or_datanode=$7

expect -c "
	set timeout 5
	spawn scp $postgresql_conf root@$ip_address:$data_directory/postgresql.conf
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
	spawn ssh root@$ip_address \"chmod 600 $data_directory/postgresql.conf; chown $superuser:$superuser $data_directory/postgresql.conf\"
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

su $superuser -c "
	ssh $superuser@$ip_address \"$installation_directory/bin/pg_ctl -D $data_directory stop > /dev/null\"
	"

if [ $? -ne 0 ]; then
	exit 100
fi

su $superuser -c "
	ssh $superuser@$ip_address \"$installation_directory/bin/pg_ctl -D $data_directory -Z $coordinator_or_datanode start > /dev/null\"
	"

if [ $? -ne 0 ]; then
	exit 100
fi

exit 0
