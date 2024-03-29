<div>
  <p>
    <img src= "https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="Spring"/>
    <img src= "https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" alt="Java"/>
  </p>
</div>

# Banking API 

A simple Spring Boot project of a simple banking API <br>

Project executed to study Spring Boot and the creation of API'S REST <br>

**Proposed activities:**<br>

* Implement path that performs the creation of an account;
* Implement path that performs deposit operation in an account;
* Implement a path that performs a balance inquiry operation in a given account;
* Implement a path that performs a withdrawal operation on an account;
* Implement path that performs the blocking of an account;
* Implement path that retrieves the transaction statement from an account;

## Running through `.jar`

### Requirements: <br>

* JDK 17 (or newer)
* Postman Api Platform *( You can use Insomnia, or any other API testing tool)*
<br> <br>


### Execution: <br>

Download the latest version of `BankingAPI.zip` from the releases tab which already contains the Postman Collection and latest `.jar` . <br>

Run with the `java -jar` command, passing the path to the `.jar` file as a parameter.

and use Postman presets on to request `Bank Project.postman_collection.json` <br>
**OR**  
open `localhost:8080/swagger` to see all requests


## Running through IDE

### Requirements: <br>

* JDK 17 (or newer)
* Maven 3.8.1
* An IDE *( I used Intellij IDEA Ultimate )*
* Postman Api Platform *( You can use Insomnia, or any other API testing tool)*
<br> <br>


### Execution: <br>

After cloning the project, access and run the project through the IDE.

Import Postman presets on `Bank Project.postman_collection.json` and use pre-made requests.
<br>**OR**<br>
Open `localhost:8080/swagger` to check all possible requests.<br>

### Example of a request to create an account:

<br>Post request to `localhost:8080/account` with JSON body: <br>


```json
{
  "balance" : 10,
  "accountType" : 1
}
```
