# Amortization Service

The Amortization service is a Spring boot service, which is responsible for publishing the amortization schedule.

The service receives as input, the following fields:
 - Loan amount (or principal)
 - Interest rate (expressed as an annual percentage rate or APY)
 - Term (in years)
 
And returns, as output, the following:
 - Month
 - Starting balance
 - Fixed Payment
 - Interest Payment
 - Principal Payment
 - Ending balance
 - Total interest

----

### About the service.

The service is built using Spring boot framework, and uses JDK 11, as the JVM. It uses gradle, as the build tool, for building and running the service.

It exposes a RESTFul API, for computing the amortization schedule.

Here is a sample request for the API:

```
POST http://localhost:8080/payments/amortization/schedule
{
    "loanAmount": 100000,
    "interestRate": 2.5,
    "loanTerm": 10
}
```

There is a folder called 'postman' at the root of the project, which has the Postman collection, which you could import into Postman, and execute the API.

### How to run the service.
Assuming that you have JDK 11, installed on your machine, you could just unzip the folder, and execute either of the following
commands, from the root of the unzipped folder:

```
java -jar build/libs/amortization-service-0.0.1-SNAPSHOT.jar
```

or if you are interested in using gradle to build and run the application, you could run:
```
./gradlew clean build bootRun
```

