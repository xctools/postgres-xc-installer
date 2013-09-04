#/bin/sh
#
# create_config_file_template.sh
#
# Copyright (C) 2013 NTT Software Corporation.

create_config_file_directory=$1
log_time=$2

pushd ./resource/PGXC-Tools/pgxc_ctl

if [ $? -ne 0 ]; then
	exit 100
fi

sed "s|^pgxcInstallDir=\$HOME/pgxc|pgxcInstallDir=$create_config_file_directory|" ./pgxc_ctl > ./pgxc_ctl-1

if [ $? -ne 0 ]; then
	exit 100
fi

sed "s/^verbose=n/verbose=y/" ./pgxc_ctl-1 > ./pgxc_ctl-2

if [ $? -ne 0 ]; then
	exit 100
fi

sed "s/^datetime=\`date +%y%m%d_%H%M\`/datetime=$log_time/" ./pgxc_ctl-2 > ./pgxc_ctl-foruse

if [ $? -ne 0 ]; then
	exit 100
fi

if [ -f ./pgxc_ctl-2 ]; then 
	rm -f ./pgxc_ctl-2
fi

if [ $? -ne 0 ]; then
	exit 100
fi

if [ -f ./pgxc_ctl-1 ]; then
	rm -f ./pgxc_ctl-1
fi

if [ $? -ne 0 ]; then
	exit 100
fi

chmod 777 ./pgxc_ctl-foruse

if [ $? -ne 0 ]; then
	exit 100
fi

./pgxc_ctl-foruse create_config_file_template

if [ $? -ne 0 ]; then
	exit 100
fi

popd

if [ $? -ne 0 ]; then
	exit 100
fi

exit 0
