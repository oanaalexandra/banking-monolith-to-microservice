# From monolith to microservice exercise

## Exercise 1
Identify which are the candidates to become a microservice

## Exercise 2
Step 1. Create a new module for the new service

How:
```
Right click -> New Module 
Add a new for your service
Make sure Language is Java and Build System Maven 
```

Step 2. Refactor name package to org.java.accountservice

Step 3. Add a springboot application runner like HomebankApplication class

Step 4. Create presentation layer controller and DTO

How:

1. Create a new package named domain

2. Create a new class in domain package named Account with the following structure

```


public class Account {

  private Long identifier;
  private String number;
  private String type;
  private Float balance;
  private List<Transaction> transactions = new ArrayList<>();
}

```
3. Add getters, setters, no arguments constructor, all arguments constructor but not including transaction list for Account
4. Create a new class in domain package named Transaction with the following structure

```

public class Transaction {

  private long id;
  private String source;
  private String destination;
  private Float amount;
  
}

```
5. Add getters, setters, no arguments constructor, all arguments constructor for Transaction class
6. Add a new package within our package named controller 
7. Create a new rest controller named AccountController
8. All the endpoint defined in AccountController will have in the base path (base URL) /account

Hint: Use a springboot annotation to map the base URL 



Step 5. Create domain layer service interface
1. Add a new package within org.java.accountservice package named service
2. Create an interface named AccountService annotated as springboot service
3. Add one method in the interface to create an account 

Step 6. Integrate domain layer with presentation layer 
1. Inject a bean of AccountService type into the controller 
2. Add a POST endpoint (method) that calls the method created in AccountService


Step 7.Create storage layer with entity

1. Create a new package named entity 
2. Copy Transaction class from banking-monolith and rename it to TransactionEntity
3. Copy Account class from banking-monolith and rename it to AccountEntity
4. Create a new package named repository
5. Copy AccountRepository interface from banking-monolith-app and replace everywhere Account with AccountEntity

Step 8. Integrate storage layer with domain layer
1. Create a new class AccountServiceImpl that implements AccountService
2. Inject AccountRepository bean
3. Create a method into Account class to map from Account to AccountEntity
4. Create a method into AccountEntity class to map from AccountEntity to Account
5. Use the above method to call save method from repository and return an account
## Exercise 3
Configure as a separate spring-boot app

1. Create an application.properties file and add configuration for a port
another than 8080 and h2 configuration 



