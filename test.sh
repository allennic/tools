#!/bin/bash

get_ifconfig() {
    ifconfig >> ifconfig.txt
}

get_ifconfig
echo

get_psinfo() {
	ps aux >> ps.txt
}

get_psinfo
echo

get_crontab() {
    crontab -l >> crontab.txt
}

get_crontab
echo

get_netstat() {
    netstat -antlp >> netstat.txt
}

get_netstat
echo

get_top() {
    top >> top.txt
}

get_top
echo

get_tar() {
    tar czf target.tar.gz ifconfig.txt ps.txt crontab.txt netstat.txt top.txt
}

get_tar
echo
# get_find() {
#     find / -name $1
# }

# get_find "tldr"
# echo "Waiting"