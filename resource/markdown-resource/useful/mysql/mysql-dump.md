# 全量备份和时间点（增量）备份

全量备份这会将服务器实例还原到进行备份时的状态。完全恢复将从完全备份还原所有数据。这会将服务器实例恢复到进行备份时的状态。如果该状态不够当前，则可以在完全恢复之后恢复自完全备份以来所做的增量备份，以使服务器处于最新状态。

增量恢复是给定时间段内所作更改的恢复，也可以叫做时间点备份，因为它使服务器状态在给定时间段内最新，
增量恢复基于二进制日志，通常情况下先进行全量恢复，然后写入二进制日志的更改会被应用，作为增量恢复去重新修改数据以让服务器达到预期的时间点状态。

# 物理备份和逻辑备份

物理备份，MYSQL 企业备份技术执行整个实例的物理备份，当备份innodb数据文件时，优化并减少了负载和避免打断。

逻辑备份，mysqldump技术提供了联机逻辑备份。

但是mysqldump不是最优解，因为它会持续生成连续大量的全数据逻辑备份，并且有的数据根本就没有改变。
所以比较有效率的做法，就是先全量备份，后增量备份。

# 解决方案

The tradeoff is that, at recovery time, you cannot restore your data just by reloading the full backup. You must also process the incremental backups to recover the incremental changes.

# docker-way

[docker-dump-git](https://github.com/databacker/mysql-backup)

## 本地文件

```cmd

docker pull databack/mysql-backup 

#  Backup Dependent 

docker exec -ti docker_mysqlback /bin/bash

docker run -d  --name docker_mysqlback --user 122 --restart=always -e DB_DUMP_FREQ=50 -e DB_DUMP_TARGET=/backup -e DB_SERVER=192.168.134.122 \
-e MYSQLDUMP_OPTS=" --single-transaction --flush-logs --master-data=1 --delete-master-logs"  \
-e DB_PORT=3306 -e DB_USER=backup -e DB_PASS=backup -v /home/docker/mysql/mysql_back:/backup databack/mysql-backup 

docker logs -f -t --tail 100  docker_mysqlback
dcrm docker_mysqlback

#  Restore 

docker run --name docker_mysqlback_restore -e DB_SERVER=192.168.134.122  -e DB_PORT=3306  -e DB_USER=backup -e DB_PASS=backup \
-e DB_RESTORE_TARGET=/backup/db_backup_20190809094127.tgz -v /home/docker/mysql/mysql_back:/backup databack/mysql-backup

dcrm docker_mysqlback_restore
```

# 删除某一时间点之前的备份文件

 - find . -mmin +5 |xargs rm -f    #删除5分钟之前的备份文件
 - find . -type f -mtime +7 -print -exec rm -f {} \;    ##删除7天之前的备份文件