#!/bin/bash
# iplist dir
IP_LIST=/home/iotcloud/ip_list
# where you want to go
SERVER_NAME=$1
TEMP_ARRAY=()
while read line

do
        if [[ $line == *$SERVER_NAME* ]]
        then
                TEMP_ARRAY[${#TEMP_ARRAY[*]}]=$line
        fi
done <$IP_LIST
LENGTH=${#TEMP_ARRAY[*]}
[ $LENGTH -eq 0 ] && { echo "not found: [$SERVER_NAME]";exit 1; }
[ $LENGTH -eq 1 ] && { TEMP_STR=${TEMP_ARRAY[0]}; }
if [[ $LENGTH -gt 1  ]]
then
        for(( i=0;i<$LENGTH;i++))
        do
                a=`expr $i + 1`
                echo "$a ${TEMP_ARRAY[i]}";
        done;
        read -p "please enter no:" var
        expr $var + 0 &>/dev/null
        [ $? -ne 0 ] && { echo "Illegal parameter";exit 1; }
        while [[ $var -gt $LENGTH || $var -lt 1 ]]
        do
                read -p "please enter correct no:" var
                expr $var + 0 &>/dev/null
                [ $? -ne 0 ] && { echo "Illegal parameter";exit 1; }
        done
        var=`expr $var - 1`

        TEMP_STR=${TEMP_ARRAY[var]}
fi
array=(${TEMP_STR// / })
username=${array[0]}
ip=${array[1]}
passwd="$username!Q2w"
if [[ ${#array[*]} -eq 3 ]]
then
        passwd=${array[2]}
fi
/usr/bin/expect <<EOF
set timeout 10
spawn ssh "$username@$ip"
expect "*password:"
send "$passwd\r"
expect eof
EOF