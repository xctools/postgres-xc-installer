#!/bin/sh
#
# setup_bashrc.sh
#
# Copyright (C) 2013 NTT Software Corporation.

superuser=$1

su $superuser -c "./resource/PGXC-Tools/pgxc_ctl/pgxc_ctl-foruse setup_bashrc"

if [ $? -ne 0 ]; then
	exit 100
fi

exit 0
