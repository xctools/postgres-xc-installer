#!/bin/sh
#
# init_all.sh
#
# Copyright (C) 2013 NTT Software Corporation.

installation_directory=$1
superuser=$2

cp -r ./resource/PGXC-Tools $installation_directory

if [ $? -ne 0 ]; then
	exit 100
fi

su $superuser -c "
	cp ~/.bashrc ~/.bashrc.org;
	cat >> ~/.bashrc <<EOF;
	export PATH_ORG=\$PATH
	export PATH=$installation_directory/bin:\$PATH
	export LD_LIBRARY_PATH_ORG=\$LD_LIBRARY_PATH
	export LD_LIBRARY_PATH=$installation_directory/lib:\$LD_LIBRARY_PATH
	export MANPATH_ORG=\$MANPATH
	export MANPATH=$installation_directory/share/man:\$MANPATH
EOF
	source ~/.bashrc;
	$installation_directory/PGXC-Tools/pgxc_ctl/pgxc_ctl-foruse init_all
	"

if [ $? -ne 0 ]; then
	exit 100
fi

if [ -d $installation_directory/PGXC-Tools ]; then
	rm -fr $installation_directory/PGXC-Tools
fi

if [ $? -ne 0 ]; then
	exit 100
fi

if [ -f ./resource/PGXC-Tools/pgxc_ctl/pgxc_ctl-foruse ]; then
	rm -f ./resource/PGXC-Tools/pgxc_ctl/pgxc_ctl-foruse
fi

if [ $? -ne 0 ]; then
	exit 100
fi

exit 0
