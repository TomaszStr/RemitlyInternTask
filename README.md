# JSON Validator

## Description

The `JSON Validator` program is a simple tool for validating the structure of a AWS::IAM::Role Policy JSON file. It checks whether the JSON contains the correct fields.
If JSON file format is incorrect it informs about it, if it's correct it checks whether the resource field contains single "*" if so it returns false, otherwise true.


## Installation

1. Clone this repository.

2. You can use program with fat-jar.

java -jar pathToFile\RemitlyInternTask-1.0-jar-with-dependencies.jar

3. You can also put everything in Intellij and run program and tests with it.

## TESTS

To run tests:

mvn clean tests

## USED TECHNOLOGIES

- Java 21
- Maven
- JUnit
- JSON in Java
