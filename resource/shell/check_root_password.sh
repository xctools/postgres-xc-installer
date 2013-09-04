#!/bin/sh
#
# check_root_password.sh
#
# Copyright (C) 2013 NTT Software Corporation.

ip_address=$1
root_password=$2

expect -c "
	set timeout 5
	spawn ssh root@$ip_address \"echo right\"
	expect {
		\"right\" {
			exit 0
		}
		\"Are you sure you want to continue connecting (yes/no)?\" {
			send \"yes\n\"
			expect \"root@$ip_address's password:\" {
				send \"$root_password\n\"
				expect {
					default {
						exit 1
					}
					\"right\" {
						exit 0
					}
				}
			}
		}
		\"root@$ip_address's password:\" {
			send \"$root_password\n\"
			expect {
				default {
					exit 1
				}
				\"right\" {
					exit 0
				}
			}
		}
		default {
			exit 100
		}
	}
	interact
	"

exit $?
