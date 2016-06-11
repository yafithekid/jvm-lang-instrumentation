# JVM Based Instrumentation

### What is this ?

This project demonstrates how to measure execution time of a method without modifying the source code itself. By using
Javassist, the class files definition in JAR archive can be altered by modifying the bytecode of class files 
during class loading. Modification of bytecode is language independent - it can be applied on any JVM-based programming
languages such as Java, Scala, and Groovy.

### What is the purpose ?

Measuring execution time of a method can be used to monitor application performance. The `spring` project is a Java Web application
built with Spring Boot that has only one controller. The response time of HTTP request can be monitored by measuring execution time
of method invocation within the controller.

## Structure of Project

`agent` - The [Javaagent](http://docs.oracle.com/javase/7/docs/api/java/lang/instrument/package-summary.html) that modifies bytecode of loaded class file from JAR archive.

`groovy` - Simple program written in Groovy.

`java` - Simple program written in Java.

`scala` - Simple program written in Scala.

`spring` - Web application built with Spring Boot.

## How to build

Install gradle and make sure it can be run in command prompt or terminal.

To clean all JAR files, run `gradle clean` in root project directory.

To clean specific JAR file, run `:module_name:clean`. For example `gradle :agent:clean`

To build the JARs, run `gradle build`. The JAR can be accessed in `module_name/build/libs/module_name.jar`

To build specific JAR file, run `:module_name:build`. For example `gradle :agent:build`

## How to run

**Scala** - see `runscala.bat`

**Groovy** - see `rungroovy.bat`

**Java** - see `runjava.bat`

**Spring** - see `runspring.bat`


