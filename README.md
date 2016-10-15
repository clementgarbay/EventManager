# Event Manager


## Installation 

To install front-end dependencies run (in the `front-src` directory) :
 
```
npm install
bower install
```

To configure data persistence (in the `META-INF` directory) :
 
```
cp persistence-default.xml persistence.xml
```

and configure your own persistence properties.

## Build

To create, compile SASS files and copy useful JavaScript and fonts files in the `assets` directory run (in the `front-src` directory) :

```
gulp
```

To build the application and run it in a embedded Tomcat (in the root folder) : 

```
mvn clean tomcat7:run-war
```

## Create WAR

```
mvn war:war
```