# QuestDDD

Simple training app created with DDD. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

## Prerequisites

Knowledge of Java 8, Spring, Lombok. 


## Installing

You can use this maven command or let your IDE do the job.

```
mvn clean install
```

## App 

### Technologies Used

Java 8, Lombok, Spring Boot, Spring Data, JPA/Hibernate, Maven, Junit 5, log4j 2.  

### Description

This is a training DDD web app created in free time. 

### Use Cases

- User can register and create an Acount:
  - His country name will be validated by outside REST api
  - After registration he will be send an email
- User can:
  - Add an new ActivityList
  - Add an new Activity to ActivityList
  - Start an Activity
  - Finish an Activity:
    - A certain amount of XP will calculated and added to his User account for finishing new Activity
    - An email will be send to him after finishing an Activity
  - He can search for NearbyQuesters - people who are close to him
    - They can be searched by different algorithims, right now it is based on longitude and latitude
      - Country name based on longitude and latitude is provided by external REST api
  - ActivitiesList, Activities and User are persistent. NearbyQuesters will be calculated when it will be needed. 
  

## Running the tests

Use:

```
mvn test
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Contributing

Lol why would you? 

## Versioning

Git obviously

## Authors

* **ME** - *Initial work* - [CountZero](https://github.com/GraphZero)

## Acknowledgments

* Hat tip to anyone who's code was used
* Bottega ddd-leaven-v2

