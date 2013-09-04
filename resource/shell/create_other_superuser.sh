#!/bin/sh
#
# create_other_superuser.sh
#
# Copyright (C) 2013 NTT Software Corporation.

ip_address=$1
superuser=$2
root_password=$3
password=$4

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
	spawn ssh root@$ip_address \"echo $superuser":"$password | chpasswd\"
        expect {
                \"Are you sure you want to continue connecting (yes/no)?\" {
                        send \"yes\n\"
                        expect \"root@$ip_address's password:\" {
                                send \"$root_password\n\"
                        }
                }
                \"root@$ip_address's password:\" {
                        send \"$root_password\n\"
                }
                default {
                        exit 100
                }
        }	
	interact"

if [ $? -ne 0 ]; then
	exit 100
fi

exit 0
