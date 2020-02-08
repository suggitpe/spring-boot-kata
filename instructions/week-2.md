# Week 2

Last week we created a REST service for creating accounts.  We hard coded the account creation as it was enough to make the core test pass.  Now we need to create a repository and then some more endpoints to get accounts (all and by ID).

## Create the repository

Firstly lets create the repository.

* We need a repository to store accounts in longer term. Lets build a repository for the accounts and create it in the endpoint.  This is where we should have a chat about injection of objects into services.
* Create an class called `AccountRepository` in a new package called repository and change the account endpoint to ask the repository to create an account with a unique number (the create on the repository should return the ID).
* Did you remember to write the test first?
* Have a look at the way that the repository is associated with the service.  It's hard wired, yet we are using spring.  Seems odd right?  We should change this and inject it using spring's Inversion of Control pattern.
* Firstly lets put an interface over the top of the repository and make the interface the member of the endpoint  .... 
    * `ctrl+f6` to rename existing to `InMemoryAccountRepository`
    * then `ctrl+alt+shift+T` to open the refactoring function and create an interface called `AccountRepository`.
* In the main application class create a bean to build the repository and then force injection through the endpoint constructor.
    * Create a new method in the main application class that returns an `AccountRepository` and annotate it with `@Bean`
    * Update the member of the endpoint to be final and of type `AccountRepository` and then use intellij to create the constructor.  When Spring creates classes, it looks for objects of the same type as contructor parameters.
* Run the test again to check its all working (you will know if its failed as you will get a null pointer exception) when you try and use the repository.

## Adding a "Get All" end point

So far we have been creating accounts only, now we need to get at the accounts.  We need a Get All endpoint that will return all the accounts.

* 
