# Event Manager

Project at Ecole des Mines de Nantes to discover basis concepts of JEE.

The goal was to create a web application to manage public events (Eventbrite, Meetup like).

## Installation 

To install front-end dependencies run (in the `front-src` directory) :
 
```
npm install
bower install
```

To change data persistence configure properties in the `persistence.xml` file (in the `META-INF` directory).

## Front-end (with gulp)

To create, compile SASS files and copy useful JavaScript and fonts files in the `assets` directory run (in the `front-src` directory) :

```
gulp
```

## Build

To run unit tests, build the application (front and back end) and run it in a embedded Tomcat (in the root folder) : 

```
mvn clean tomcat7:run-war
```

## Create WAR

```
mvn war:war
```