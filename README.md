# Project Automation API

Hi! This is a project automation API with study case [Go Rest](https://gorest.co.in/) using TestNG

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Intermezzo

**TestNG** is a testing framework inspired from JUnit and NUnit but introducing some new functionalities that make it more powerful and easier to use, such as:

* Annotations.
( Run your tests in arbitrarily big thread pools with various policies available (all methods in their own thread, one thread per test class, etc...).
( Test that your code is multithread safe.
* Flexible test configuration.
* Support for data-driven testing (with @DataProvider).
* Support for parameters.
* Powerful execution model (no more TestSuite).
* Supported by a variety of tools and plug-ins (Eclipse, IDEA, Maven, etc...).
* Embeds BeanShell for further flexibility.
* Default JDK functions for runtime and logging (no dependencies).
* Dependent methods for application server testing.

TestNG is designed to cover all categories of tests:  unit, functional, end-to-end, integration, etc...

### Prerequisites

What things you need to install:

- [Java Development Kit](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 
- [Java Runtime Engine](https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)
- [Maven](https://maven.apache.org/install.html)

## How to Run the Project
* ### Running on local machine
	
	1. Make sure you already install the JDK, JRE, and Maven on your local machine
	2. Open the project using any IDE (Eclipse, IDEA, etc)
    3. Download sources Maven and update the project
	4. Running the project using command `mvn test` 
	5. Or if you install Maven plugin on your IDE, right click on the project `Run As -> Maven test`