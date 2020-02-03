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
1. **Running a service**

    1. Firstlly lets start the DiscoveryService so you can see how to run a spring boot service.
    1. Open The DiscoverService class (ctrl+n)
    1. ctrl+shift+F10 to run it
    1. lets have a look at it running.  Open a browser and navigate to [localhost:8761](http://localhost:8761)
    1. have a look at the configuration files associated with the service.
    
1. **Building the Bank Account service**

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
    1. Open up the swagger link and you can see the exposed APIs etc

1. **Adding a Post end point**

    1. Now lets add an endpoint to the service we need to be able to create accounts before we can perform any transactions on them:
    1. The first thing we are going to do is to create the test that will a) start up the service and b) call the create account URL.
    1. Open up the existing test skeleton (ctrl+n) for `BankAccountServiceEndpointTest`
    1. Before we do anything run the test class.  What do you notice about the test logging.  Its takes a while to start up, right?
    1. Now uncomment out the first test and you should see that we are getting compiler errors.  We need to create a domain object for the service to operate.
    1. Create a package called domain in the same package as the service.  Create the domain object in there.
    1. Now rerun the test.  Again what happens when it fails, what's the status we get back from the service? What does a 404 status mean?  It's telling us that the url that we called does not exist.  We need to create the endpoint in the service.
    1. Start by creating an endpoints package next to the domain and create a `BankAccountEntpoint`.  We need to annotate this class with `@RestController`.
    1. In this class we need to create the end point.  Put simply this is a methods that is annotated as a PostEndpoint using `@PostMapping(value = "/accounts", consumes = APPLICATION_JSON_VALUE)` and it should return a new Account.
    1. Create an account and return the ID.
    1. Now run the test and it should pass.
    1. Start the application and navigate to the swagger page.  You should  now see the new end point listed.  The details are a bit rubbish, we should really update api documentation.  Open up the endpoint (ctrl+n).  You need to add a description of teh API using teh `@ApiOperation` annotation.  Google it and see how it works.  Now restart and review the swagger page.
    1. Yay, if the test is now green you are done, right?  Wrong ... remember red->green->refactor.  What can you do to improve things before you check in your code?

1. **Create the repository**

    1. OK we have a create service but the accounts aren't stored anywhere
    1. But we have a snag.  if we create an account in the method the account will not actually exist.  We need a repository to store them in longer term. Lets build a repository for the accounts and create it in the endpoint.  This is where we should have a chat about injection of objects into services.
    1. Create a class called account repository and change the endpoint to ask the repository to create an account with a unique number.
    1. Did you remember to write the test first?
    1. Have a look at the way that the repository is associated with the service.  Its hard wired, yet we are using spring.  Seems odd right?  We should change this and inject something using spring.
    1. Firstly lets put an interface over the top of the repository and make the interface the member of the endpoint (rename the original one first) .... ctrl+f6 to rename existing and then ctrl+alt+shift+T to open the refactoring function.
    1. In the main application class create a bean to build the repository and then force injection through the endpoint constructor.
    1. Run the test again to check its all working (you will know if its failed as you will get a null pointer exception) when you try and use the repository.

1. **Adding a Get end point**

    1. 

