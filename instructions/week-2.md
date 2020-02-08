# Week 2


1. **Create the repository**

    * OK we have a create service but the accounts aren't stored anywhere
    * But we have a snag.  if we create an account in the method the account will not actually exist.  We need a repository to store them in longer term. Lets build a repository for the accounts and create it in the endpoint.  This is where we should have a chat about injection of objects into services.
    * Create a class called account repository and change the endpoint to ask the repository to create an account with a unique number.
    * Did you remember to write the test first?
    * Have a look at the way that the repository is associated with the service.  Its hard wired, yet we are using spring.  Seems odd right?  We should change this and inject something using spring.
    * Firstly lets put an interface over the top of the repository and make the interface the member of the endpoint (rename the original one first) .... ctrl+f6 to rename existing and then ctrl+alt+shift+T to open the refactoring function.
    * In the main application class create a bean to build the repository and then force injection through the endpoint constructor.
    * Run the test again to check its all working (you will know if its failed as you will get a null pointer exception) when you try and use the repository.

1. **Adding a Get end point**

    * 
