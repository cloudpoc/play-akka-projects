# play-akka-projects using java

This is a basic example project using Play and Akka . Here we have created a play app to expose rest service which wil return the product details . Once play recive the http request , it will connect remote akka instance to get the product details. PLZ FEEL FREE TO ADD MORE FEATURE TO IT

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

HIT BELOW URL TO GET THE PRODUCT DETAILS
--------------------------------------------
http://localhost:9000/product/1
