```cmd
./bin/elasticsearch -d -p pid    #启动

kill -9 `cat pid`  #停止

cd /home/elk/elasticsearch-6.5.3 && ./bin/elasticsearch -d -p pid    #快速启动

cd /home/elk/elasticsearch-6.5.3 && kill -9 `cat pid`   #快速停止
```
