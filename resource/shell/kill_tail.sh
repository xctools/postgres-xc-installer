#!/bin/sh
#
# kill_tail.sh
#
# Copyright (C) 2013 NTT Software Corporation.

log_time=$1

tail_pid=`ps -ef | grep tail | grep pgxc_ctl_$log_time.log | awk '{print $2}'`

if [ $? -ne 0 ]; then
	exit 100
fi

kill $tail_pid

if [ $? -ne 0 ]; then
	exit 100
fi

exit 0
