#!/bin/sh
#
# install_postgres_xc_and_pgxc_monitor.sh
#
# Copyright (C) 2013 NTT Software Corporation.

installation_directory=$1

pushd ./resource/postgres-xc

if [ $? -ne 0 ]; then
	exit 100
fi

./configure --prefix=$installation_directory

if [ $? -ne 0 ]; then
	exit 100
fi

make

if [ $? -ne 0 ]; then
	exit 100
fi

make install

if [ $? -ne 0 ]; then
	exit 100
fi

popd

if [ $? -ne 0 ]; then
	exit 100
fi

pushd ./resource/postgres-xc/contrib/pgxc_monitor

if [ $? -ne 0 ]; then
	exit 100
fi

make

if [ $? -ne 0 ]; then
	exit 100
fi

make install

if [ $? -ne 0 ]; then
	exit 100
fi

popd

if [ $? -ne 0 ]; then
	exit 100
fi

exit 0
