#!/bin/sh
#
# chown_postgres_xc.sh
#
# Copyright (C) 2013 NTT Software Corporation.

superuser=$1
installation_directory=$2

chown -R $superuser:$superuser $installation_directory

if [ $? -ne 0 ]; then
	exit 100
fi

exit 0
