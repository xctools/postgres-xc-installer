#!/bin/sh
#
# deploy_all.sh
#
# Copyright (C) 2013 NTT Software Corporation.

superuser=$1

su $superuser -c "./resource/PGXC-Tools/pgxc_ctl/pgxc_ctl-foruse pgxc_deploy_all"

if [ $? -ne 0 ]; then
	exit 100
fi

exit 0
