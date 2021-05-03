# Fetch-Rewards
## Intrduction
A web service that accepts HTTP requests and returns responses based on the conditions outlined below.

Users have points in their accounts. Users only see a single balance in their accounts. But for reporting purposes we actually track their
points per payer/partner. In our system, each transaction record contains: payer (string), points (integer), timestamp (date).
For earning points it is easy to assign a payer, we know which actions earned the points. And thus which partner should be paying for the points.
When a user spends points, they don't know or care which payer the points come from. But, our accounting team does care how the points are
spent. There are two rules for determining what points to "spend" first:

● We want the oldest points to be spent first (oldest based on transaction timestamp, not the order they’re received)  

● We want no payer's points to go negative.

Full docment explaing the problem:  

[Click Here To Open The Document](https://fetch-hiring.s3.us-east-1.amazonaws.com/points.pdf)  


## Host System Specification
System specfications are as follows:  

OS: Ubuntu 20.04.2 LTS  

Java version: openjdk version "14.0.2" 2020-07-14  

Spring Boot  

MySQL  


## Running the Application
Pre Requirement:  


1. [Install Java](https://www.java.com/en/download/)    
3. [Install Maven](https://maven.apache.org/download.cgi)      
5. [Install Postman](https://www.postman.com/downloads/)  
    Postman to run API


Step 1  
Clone the repository:  
```
https://github.com/PrakharS2811/Fetch-Rewards.git
```   
Step 2   
Run command to get JAR file
```
mvn clean install
```  
Step 3  
Run command in terminal to execute the jar  
```
java -jar target/Points-0.0.1-SNAPSHOT.jar
```

Now the application is running on localhost:8080  
Open Postman app to run the API  


1. Type the URL in the URL section
```
http://localhost:8080/fetchRewards/addTransaction
```
Add following json in the "Body"
```json
{ "payer": "DANNON", "points": 1000, "timestamp": "2020-11-02T14:00:00Z" }
```
and send using POST method to add the transaction  
