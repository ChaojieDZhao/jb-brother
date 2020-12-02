#!/bin/bash
# 用途：入侵报告工具，以auth.log作为日志文件

AUTHLOG=/var/log/auth.log

if [[ -n $1 ]];
then
	AUTHLOG=$1
	echo Using Log File: $AUTHLOG
fi

LOG=/tmp/valid.$$.log
grep -v "invalid" $AUTHLOG > $LOG
USERS=$(grep "Failed password" $LOG | awk '{print $(NF-5)}' | sort | uniq)

printf "%-5s|%-10s|%-10s|%-13s|%-33s|%s\n" "Sr#" "User" "Attempts" "IP address" "Host_Mapping" "Time range"

USER_COUNT=0;
IP_LIST="$(egrep -o "[0-9]+\.[0-9]+\.[0-9]+\.[0-9]+" $LOG | sort | uniq) "   #获取IP地址列表

for IP in $IP_LIST;
do
	grep $IP $LOG > /tmp/temp.$$.log

	for USER in $USERS;
	do
		grep $USER /tmp/temp.$$.log > /tmp/$$.log
		cut -c 16 /tmp/$$.log > $$.time
		TSTART=$(head -1 $$.time);
		START=$(date -d "$TSTART" "+%s");
		TEND=$(tail -1 $$.time);
		END=$(date -d "$TEND" "+%s");
		LIMIT=$(( $END - $START ))
		if [ $LIMIT -gt 120 ];
		then
			let USER_COUNT++;

			IP_ADDRESS=$(egrep -o "[0-9]+\.[0-9]+\.[0-9]+\.[0-9]+" /tmp/$$.log | head -1);
			TIME_RANGE="$TSTART-->$TEND"
			ATTEMPTS=$(cat /tmp/$$.log | wc -l);
			HOST=$(host $IP_ADDRESS | awk '{print $NF}');

			printf "%-5s|%-10s|%-10s|%-10s|%-13s|%-33s|%s\n" "$USER_COUNT" "$USER" "$ATTEMPTS" "$IP_ADDRESS" "$HOST" "$TIME_RANGE";
		fi
	done
done

rm $LOG /tmp/$$.log $$.time /tmp/temp.$$.log 2> /dev/null

		
