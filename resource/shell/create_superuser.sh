#!/bin/sh
#
# create_superuser.sh
#
# Copyright (C) 2013 NTT Software Corporation.

superuser=$1
password=$2

useradd $superuser

if [ $? -ne 0 ]; then
	exit 100
fi

echo $superuser":"$password | chpasswd

if [ $? -ne 0 ]; then
	exit 100
fi

exit 0
