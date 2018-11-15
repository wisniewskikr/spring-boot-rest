SPRING BOOT + REST + SSL
========================


DESCRIPTION
-----------

This is example project of usage Spring Boot + Rest.
In this project usage of SSL is presented.
  

USAGE
-----

To run project run class: 
Application.java

Link to local:
https://localhost:8443/hello/{name}
example:
https://localhost:8443/hello/Chris

Link to Tomcat:
https://localhost:8443/spring_boot_rest-0.0.1/hello/{name}
example:
https://localhost:8443/spring_boot_rest-0.0.1/hello/Chris

Client has to use certificate:
- clientkeystore.jks
- password: 3edc4rfv


SPRING BOOT
-----------

To configure ssl in Spring Boot you have to make following changes in file application.properties:
- server.port
- server.ssl.key-store
- server.ssl.key-store-password


TOMCAT
------

To configure ssl in Tomcat you have to make following changes in file <tomcat_home>/conf/server.xml:

<Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />
               
<Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
               maxThreads="150" SSLEnabled="true" scheme="https" secure="true"
               clientAuth="false" sslProtocol="TLS" 
			   keystoreFile="${catalina.home}/conf/serverkeystore.jks"			   
			   keystorePass="1qaz2wsx"/>                