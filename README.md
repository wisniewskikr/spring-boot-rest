SPRING BOOT + REST + SWAGGER 2 + API KEY IN HEADER
==================================================


DESCRIPTION
-----------

This is example project of usage Spring Boot + Rest + Swagger 2. 
This project provides CRUD (Create, Read, Update, Delete) operations on users
by REST Api and enables test it by Swagger UI.

Additionally service is secured by API KEY in header:
- name: API-KEY
- value: 123

Useful tools:
- SoapUI: tool for sending REST requests.
  

USAGE
-----

To run project run class: 
Application.java

Link to Swagger UI:
http://localhost:8080/swagger-ui.html 


ATTENTION
---------

Swagger UI does not work because service is secured by API KEY. 
Please use another tool - for example SoapUI.