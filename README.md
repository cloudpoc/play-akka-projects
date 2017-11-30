# play-akka-projects using java

This is as part of personalization POC . Mainly to collect customer trend in terms of recently viewed product,recently viewed categories etc.

Before you download makesure below s/w are installed in your machine

1) Java 8
2) SBT (Scala build tool)

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
