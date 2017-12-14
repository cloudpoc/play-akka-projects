# play-akka-projects using java

This is as part of personalization POC . Mainly to collect customer trend in terms of recently viewed product,recently viewed categories etc.

Before you download makesure below s/w are installed in your machine

1) Java 8
2) SBT (Scala build tool)
3) Redis server 

Follow the below steps to install redis on windows
--------------------------------------------------------------
1.	Download and extract the Redis binaries from the 2.6  branch(https://github.com/MSOpenTech/redis/raw/2.6/bin/release/redisbin64.zip)
2.	Copy all extracted binaries to c:\redis\bin
3.	Create another folder at c:\redis\inst1
4.	Download and extract the RedisWatcher binaries from the 2.4 branch
5.	Run InstallWatcher.msi. This should create a Windows service called Redis watcher.
6.	Open up the Windows Services console and start the Redis watcher service. 


It contain 2 projects . play and akka respectivily. Both are configured in different port so you can run in same machine only

TO START AKKA INSTANCE -- open a command window from play-akka-projects folder
-------------
1) cmd  akka-product-remote-actor
2) sbt clean
3) sbt compile
4) sbt run

TO START PLAY APP INSTANCE -- open a command window from play-akka-projects folder
-------------
1) cmd  play-app-browse
2) sbt clean
3) sbt compile
4) sbt run

HIT BELOW URL TO {POST} THE DATA
--------------------------------------------
http://localhost:9000/personalization/profile

{
   "globalId": "113656",
   "browsedDeviceList": "IPHONE X,Nokia 80000"
}

HIT BELOW URL TO {GET} THE DATA
--------------------------------------------
http://localhost:9000/personalization/profile/{ID}

Note: Make sure a redis server running in localhost 
