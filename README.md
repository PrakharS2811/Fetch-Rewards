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
2. Add following json in the "Body"
```json
{ "payer": "DANNON", "points": 1000, "timestamp": "2020-11-02T14:00:00Z" }
```
3. Send using POST method to add the transaction   

Similarly add all the transactions:
```json
{ "payer": "UNILEVER", "points": 200, "timestamp": "2020-10-31T11:00:00Z" }
{ "payer": "DANNON", "points": -200, "timestamp": "2020-10-31T15:00:00Z" }
{ "payer": "MILLER COORS", "points": 10000, "timestamp": "2020-11-01T14:00:00Z" }
{ "payer": "DANNON", "points": 300, "timestamp": "2020-10-31T10:00:00Z" }
```

To spend the points  
1. Type URL
```
http://localhost:8080/fetchRewards/spendPoints
```
2. Add Json in the body
```json
{ "points": 5000 }
```
3. Send using POST method  

Output wil be as shown  
```json
[
{ "payer": "DANNON", "points": -100 },
{ "payer": "UNILEVER", "points": -200 },
{ "payer": "MILLER COORS", "points": -4,700 }
]
```
      
To view the left over balance
1. Type the URL 
```
http://localhost:8080/fetchRewards/getBalance
```
2. Send using GET method  

Output will be shown as  
```json
{
"DANNON": 1000,
"UNILEVER": 0,
"MILLER COORS": 5300
}
```
