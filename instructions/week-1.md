# Week one

## Instructions


1. **Running a service**
The very first thing we are going to do is to take an existing service and show you how to start it and stop it.

    1. Firstlly lets start the DiscoveryService so you can see how to run a spring boot service.
    1. Open The DiscoverService class (ctrl+n)
    1. ctrl+shift+F10 to run it
    1. lets have a look at it running.  Open a browser and navigate to [localhost:8761](http://localhost:8761)
    1. have a look at the configuration files associated with the service.
    
1. **Building the Bank Account service**
We are going to start building a real service.  We have a skelton service to start from with a few bits of configuration that you need.  If you want to learn how to create one from scratch, have a look at [Sprint Initializr](https://start.spring.io/) as a quick way to create a spring boot project.

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
You remember the bank account kata, right?  Now we are going to put a REST service over the top of it so you can access it remotely (like from a phone app).

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

Thats about enough for week 1.