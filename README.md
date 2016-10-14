# Event Manager


## Installation 

To install front-end dependencies run (in the `front-src` directory) :
 
```
npm install
bower install
```

## Build

To create, compile SASS files and copy useful JavaScript and fonts files in the `assets` directory run (in the `front-src` directory) :

```
gulp
```

To build the application and run it in a embedded Tomcat (in the root folder) : 

```
clean tomcat7:run-war
```

## Create WAR

```
war:war
```