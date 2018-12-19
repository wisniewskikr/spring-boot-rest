SPRING BOOT + REST + BASIC AUTHENTICATION + USER DETAILS SERVICE
================================================================


DESCRIPTION
-----------

This is example project of usage Spring Boot + Rest.
This is the simplest possible application return "Hello World" + name.
It shows how basic authentication works.

UserDetailsService creates new user basic on his username. Creates:
- username
- password
- roles
 In other words it replaces configure(AuthenticationManagerBuilder auth)
 basing on username.
  

USAGE
-----

To run project run class: 
Application.java

Link:
http://localhost:8080/app/hello/{name}
example:
http://localhost:8080/app/hello/Chris

Basic authentication:
user/password