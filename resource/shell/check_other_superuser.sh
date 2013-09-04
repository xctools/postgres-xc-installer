#!/bin/sh
#
# check_other_superuser.sh
#
# Copyright (C) 2013 NTT Software Corporation.

ip_address=$1
superuser=$2
root_password=$3

expect -c "
	set timeout 5
	spawn ssh root@$ip_address \"if \[ \`cut -d: -f1 /etc/passwd | grep ^$superuser\$\` = "" \]; then echo right; else echo miss; fi\"
	expect {
		\"right\" {
			exit 0
		}
		\"miss\" {
			exit 1
		}
		\"Are you sure you want to continue connecting (yes/no)?\" {
			send \"yes\n\"
			expect \"root@$ip_address's password:\" {
				send \"$root_password\n\"
				expect {
					default {
						exit 100
					}
					\"right\" {
						exit 0
					}
					\"miss\" {
						exit 1
					}
				}
			}
		}
		\"root@$ip_address's password:\" {
			send \"$root_password\n\"
			expect {
				default {
					exit 100
				}
				\"right\" {
					exit 0
				}
				\"miss\" {
					exit 1
				}
			}
		}
		default {
			exit 100
		}
	}
	interact"

exit $?
