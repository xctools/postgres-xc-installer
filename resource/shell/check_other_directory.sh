#!/bin/sh
#
# check_other_directory.sh
#
# Copyright (C) 2013 NTT Software Corporation.

ip_address=$1
superuser=$2
root_password=$3
directory=$4

expect -c "
	set timeout 5
	spawn ssh root@$ip_address \"if \[ ! -e $directory \]; then echo right; else echo miss; fi\"
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

result=$?

if [ $result -ne 0 ]; then
	exit $result
fi

expect -c "
	set timeout 5
	spawn ssh root@$ip_address \"useradd $superuser && echo right\"
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
                                                exit 100
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
                                        exit 100
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
        interact"

if [ $? -ne 0 ]; then
	exit 100
fi

expect -c "
	set timeout 5
	spawn ssh root@$ip_address \"su $superuser -c \\\"mkdir -p $directory && echo right; rm -fr $directory\\\"\"
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
                                                exit 2
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
                                        exit 2
                                }
                                \"right\" {
                                        exit 0
                                }
                        }
                }
                default {
                        exit 2
                }
        }
        interact"	

result=$?

expect -c "
	set timeout 5
	spawn ssh root@$ip_address \"userdel -r $superuser && echo right\"
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
                                                exit 100
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
                                        exit 100
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
        interact"

if [ $? -ne 0 ]; then
	exit 100
fi

exit $result
