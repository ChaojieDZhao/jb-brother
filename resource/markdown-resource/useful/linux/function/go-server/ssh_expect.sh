#!/usr/bin/expect -f

set username [lindex $argv 0]
set passwd [lindex $argv 1]
set ip [lindex $argv 2]

set timeout 10
spawn ssh "$username@$ip"
expect "*password:"
send "$passwd\r"
interact
