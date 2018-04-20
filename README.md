SPRING BOOT + REST + X509 AND BASIC AUTH ON TOMCAT
==================================================


DESCRIPTION
-----------

This is example project of usage Spring Boot + Rest.
In this project x509 authentication is displayed.


PRECONDITIONS
-------------

On Tomcat update following files:
- <tomcat_home>/conf/tomcat-users.xml
<user username="ocid" password="Roche2018" roles="ocid"/>
- <tomcat_home>conf/server.xml
<Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
               maxThreads="150" SSLEnabled="true" scheme="https" secure="true"
               clientAuth="want" sslProtocol="TLS" 
			   keystoreFile="${catalina.home}/conf/serverkeystore.jks"
			   keystoreType="JKS" 
			   keystorePass="1qaz2wsx"
               truststoreFile="${catalina.home}/conf/servertruststore.jks"
               truststoreType="JKS" 
			   truststorePass="1qaz2wsx"/>
  

USAGE
-----

To run project run class: 
Application.java

Link to local:
https://localhost:8443/hello/{name}
example:
https://localhost:8443/hello/Chris

Link to Tomcat:
https://localhost:8443/rest/hello/{name}
example:
https://localhost:8443/rest/hello/Chris

Client has to use certificate:
- clientkeystore.jks
- password: 3edc4rfv