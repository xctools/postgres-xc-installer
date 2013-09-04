#!/bin/sh
#
# check_superuser.sh
#
# Copyright (C) 2013 NTT Software Corporation.

superuser=$1

name_list=`cut -d: -f1 /etc/passwd`

if [ $? -ne 0 ]; then
	exit 100
fi

for name in $name_list
do
	if [ $name = $superuser ]; then
		exit 1
	fi
done

exit 0
