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

* Have a look at the Account class.  Now that we have competed the kata, the account class contains all the transactions.  When we get the account data we only want the account summary.  We are going to need a new domain object to represent account summaries.
* Open the end-point test and uncomment out the test details and remove the `@Disabled` annotation.  You will need to resolve all the compiling errors by creating the summary class.
* You should see a `405` error.  We have only mapped POST to the /accounts endpoint.  We need a GET.  In the endpoint create a getAllAccounts method and annotate it with `@GetMapping` in the same manner as the post endpoint.  It should return a `List<AccountSummary>`. 
* You will need a get all on the repository, but dont allow the respository to be poluted with the AccountSummary class.  In the end point you should stream them into AccountSummary classes using `repository.getAllAccounts().stream().map(acc -> new AccountSummary(acc)).collect(toList())`
* The test you have created is implicitly going to fail sometimes?  Why?  What can we do about this?  Have a look at the Junit5 `@Order()` annotation.
* You will likely need to update the AccountSummary class with getters, equals/hashcode etc.  To do this use `ctrl+shift+a` to get the actions menu up.
* You will also likely need to create a new constructor and annotate the parameters with the `@JsonProperty` annotation.

## Adding a "Get by ID" end point

We should now create a new end point that allows us to get an account summary by ID.

* Open up the End Point Test and see the retrieves by Id test.  You can see that we have stubbed out with a random account number.  We need a real one.  Firstly update the test to create a new account and then use the ID from that to retrieve the summary.
* Now we need to create a new end point to query by ID.  Create a new method that returns an `AccountSummary`.  This method should be a `@GetMapping("/accounts/{accountId}")` and then the method signature should annotate the accountId with `@PathVariable` to bind the mapping amd the method parameter.  You will need to implement a few more methods on the repository to make it work.

----
# Homework assignment

Last week for your homework you created a simple employee service.  Do the same as today by adding in the repository and the getter end points.
