# Building a bank one service at a time
This session is aimed at teaching you the basics of Spring Boot and how we can use a few basic concepts to show how a service oriented architecture can work.  We ask for you to work in pairs to build out the service using a test driven approach.

* Rule #1: you cannot write any production code without a failing test (think of the compiler as a test).
* Rule #2: you are not allowed to use your mouse, intellij was designed ergonomically
* Rule #3: do this in pairs
* Rule #4: once you have finished in the led session, delete it and do it again at your own pace.

----
# Prerequisites

You will need Java and an IDE (we prefer Intellij IDEA). 

The project uses [Gradle](https://gradle.org/), you will either need to work with Gradle from the terminal or, in the case of IntelliJ IDEA, use the Gradle plugin. 

As we use Gradle, performing the steps below before the session will save time downloading various dependencies.


1. Clone the repository with the following command.

   `git clone https://github.com/suggitpe/spring-boot-kata.git`
  
   If you have problems with SSL, you can try the following.
   
   `git clone -c http.sslVerify=false https://github.com/suggitpe/spring-boot-kata.git`
   
   If you have problems with a proxy, you can `unset http_proxy` and `unset https_proxy` (or equivalent for your OS).

1. Open the project from IntelliJ IDEA (community edition is fine). 

   If you have the Gradle plugin installed, things should "just work". Gradle will download all the dependencies and you will see the project compile. Your millage may vary.

1. To test everything is compiling, navigate to `DiscoveryService.java` (under the `discovery-service` folder) and run it as an application.

   You should see a green run icon to the left of the class declaration. If you don't or can't run it, speak to an instructor.

----
# Instructions
This kata is structured a set of weekly exercises that are instructor led:

* [Week 1](instructions/week-1.md) creating an application and adding a single put service
* [Week 2](instructions/week-2.md) creating a repository and adding gets into the service
* [Week 3](instructions/week-3.md) using the discovery service to call another restful application
* [Week-4](instructions/week-4.md) securing the services
* [Week-5](instructions/week-5.md)



