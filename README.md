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
2. [Install Maven](https://maven.apache.org/download.cgi)
3. [Install Postman](https://www.postman.com/downloads/)
    Postman to run API


