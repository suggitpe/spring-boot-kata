# Building a bank one service at a time
This session is aimed at teaching you the basics of Spring Boot and how we can use a few basic concepts to show how a service oriented architecture can work.  We ask for you to work in pairs to build out the service using a test driven approach.

Rule #1: you cannot write any production code without a failing test (think of the compiler as a test).
Rule #2: you are not allowed to use your mouse, intellij was designed ergonomically

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
1. Firstlly lets start the DiscoveryService

    1. Open The DiscoverService class (ctrl+n)
    1. ctrl+shift+F10 to run it
    1. lets have a look at it running.  Open a browser and navigate to [localhost:8761](http://localhost:8761)
    
1. We are now going to get the BankAccountService running and in there we will create some services that other applications can call.

    1. In the build.gradle (ctrl+shift+n) you need to uncomment out the lines at the top of the file
    1. Open the BankAccountService (ctrl+n)
    1. We need to give the class a main method implement the SpringApplication (`new SpringApplicationBuilder(BankAccountService.class).web(SERVLET).run(args)`)
    1. Add the following annotation on the top of the class: @SpringBootApplication
    1. Now run it (ctrl+shift+F10).
    1. Congratulations you have built your first SpringBoot application
    1. You should now have a look at the config files in the resources directory and get a feel for what they are
    1. Open up the Discovery Service again and notice that the application has registered itself with the service.  To enable others to contact this service we need to also annotate the class with the `@EnableDiscoveryClient` annotation.
    1. While we are here lets also annotate the class with Swagger so we can look at the APIs later `@EnableSwagger2`
    1. Restart the application and lets navigate to [localhost:8901](http://localhost:8901)

1. Now lets add an endpoint to the service:

    1. Firstly lets create the test
    2. 


