## 查询数据库所有用户
select username from dba_users; 

## 解锁账户和修改密码
alter user system account unlock;
alter user system identified by "manager";

## 查询改用户有多少张表
select count（*） from user_tables ;

## 删除用户
drop user parking cascade;

## 重建用户
create user parking identified by 123456;   
grant connect,resource,dba,sysdba to parking;

## 查看nls字符格式
select * from v$nls_parameters where parameter='NLS_CHARACTERSET';

## 创建逻辑目录并且导入导出使用
create directory dump_dir as '/home/oracle/dump_dir';   
select * from dba_directories;   
grant read,write on directory dump_dir to parking;   

## exp导出命令
exp parking/123456 buffer=64000 file=/opt/parking_export.dmp owner=parking

## expdp导出命令
expdp parking/123456@shibdz_ypt schemas=parking dumpfile=parking_export.dmp  directory=dump_dir;

## impdp导入命令
chmod -R 777 /home/oracle/dump_dir   
impdp parking/123456 DIRECTORY=dump_dir DUMPFILE=expdp_parking20180425.dmp  SCHEMAS=parking;  #exp 1

impdp system directory=dump_dir  dumpfile=parking0425_export.dmp logfile=parking0425_export.log SCHEMAS=parking0425 remap_schema=parking0425:parkingmq remap_tablespace=TBS_ETC_DATA:TBS_PARKING_DATA #exp 2

## 创建表空间
select * from dba_tablespaces;       #查看所有的表空间   
create tablespace TBS_PARKING_DATA datafile '/disk3/oradata/TBS_PARKING_DATA.dbf' size 2048m autoextend on next 100M maxsize unlimited  extent management local autoallocate segment space management auto;

## 设置表空间默认
alter database default tablespace TBS_PARKING_DATA;

## 删除表空间
alter database datafile '/disk3/oradata/TBS_PARKING_DATA.dbf' offline drop;
drop tablespace TBS_PARKING_DATA including contents and datafiles CASCADE CONSTRAINTS;

## 恢复误删除的表
flashback table EA_DEMO_UPLOAD to before drop;

## 查询所有的空间
select * from dba_directories;

## connect by
> 语法：    select * from table [start with condition1]   connect by [prior] id=parentid
- start with condition1 是用来限制第一层的数据，或者叫根节点数据；以这部分数据为基础来查找第二层数据，然后以第二层数据查找第三层数据以此类推。
- connect by [prior] id=parentid 这部分是用来指明oracle在查找数据时以怎样的一种关系去查找；
比如说查找第二层的数据时用第一层数据的id去跟表里面记录的parentid字段进行匹配，如果这个条件成立那么查找出来的数据就是第二层数据，同理查找第三层第四层…等等都是按这样去匹配。
```sql
CREATE TABLE T_TREE (ID NUMBER PRIMARY KEY, FATHER_ID NUMBER, NAME VARCHAR2(30));  
select * from T_TREE;
INSERT INTO T_TREE VALUES (1, 0, 'A');
INSERT INTO T_TREE VALUES (2, 1, 'BC');
INSERT INTO T_TREE VALUES (3, 1, 'DE');
INSERT INTO T_TREE VALUES (4, 1, 'FG');
INSERT INTO T_TREE VALUES (5, 2, 'HIJ');
INSERT INTO T_TREE VALUES (6, 4, 'KLM');
INSERT INTO T_TREE VALUES (7, 6, 'NOPQ');

SELECT ID, FATHER_ID, NAME, CONNECT_BY_ROOT(NAME) ROOT_NAME,LEVEL
  FROM T_TREE
 START WITH FATHER_ID = 0
CONNECT BY PRIOR ID = FATHER_ID;

SELECT ID,
       FATHER_ID,
       NAME,
       CONNECT_BY_ROOT(NAME) ROOT_NAME,
       CONNECT_BY_ROOT(ID) ROOT_ID,LEVEL
  FROM T_TREE
 START WITH ID = 7
CONNECT BY PRIOR FATHER_ID = ID;
```

## Exists、In、ANY、ALL的区别
`Exists`：子查询至少返回一行时条件为true。    
`Not Exists`：子查询不返回任何一行时条件为true。    
> EXISTS(包括 NOT EXISTS )子句的返回值是一个BOOLEAN值。 EXISTS内部有一个子查询语句(SELECT ... FROM...)， 称为EXIST的内查询语句。其内查询语句返回一个结果集。 
EXISTS子句根据其内查询语句的结果集空或者非空，返回一个布尔值。    
将外查询表的每一行，代入内查询作为检验，如果内查询返回的结果取非空值，则EXISTS子句返回TRUE，这一行则可作为外查询的结果行，否则不能作为结果。

`In`：与子查询返回结果集中某个值相等。    
`Not In`：与子查询返回结果集中任何一个值不相等。    
`>ANY`：比子查询返回结果中的某个值大。     
`=ANY`：与子查询返回结果中的某个值相等。    
`<ANY`：比子查询返回结果中的某个值小。     
`>ALL`：比子查询返回结果中的所有值都大。     
`<ALL` ：比子查询返回结果中的所有值都小。       

## 查询oracle数据库有无死锁
```oracle
SELECT 'alter system kill session ''' || sid || ',' || serial# || ''';' "Deadlock"
  FROM v$session
 WHERE sid IN (SELECT sid FROM v$lock WHERE block = 1);
```
然后执行返回即可
> NOTE:应当注意对于 sid 在 100 以下的应当谨慎，可能该进程对应某个application，如对应某个事务,可以 kill

## 查看导致死锁的 SQL
```oracle
SELECT s.sid, q.sql_text
FROM v$sqltext q, v$session s
WHERE q.address = s.sql_address AND s.sid = &sid     -- 这个&sid 是第一步查询出来的
ORDER BY piece;
```


[数据库解锁](https://www.cnblogs.com/zhoading/p/8547320.html)

