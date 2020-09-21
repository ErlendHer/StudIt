# Gruppe60 StudIt App

[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2060/gr2060)


## Project structure
Main code is under *Studit/src/main/..*  
Testing is found under *Studit/src/test/..*  

---
Application logic is under *studit/core/..*  
GUI implementations is under *studi/ui/..*

## Installation and testing

To run the application, click the gitpod link and run: `mvn javafx:run`  
For unit testing and code coverage, run `mvn test`  

To login once the application is started, enter *user* as username and *password* as password

Code coverage is found under *target/site/jacoco*

## User Stories

- As a coming student I want to find information about a subject I am going to take at NTNU, so that I can be prepared when I start to study
- As a student I want to find information about different subjects at NTNU, so I can compare subjects I consider to choose
- As a student I want to ask questions about a subject I am taking at NTNU, that I want to know the answer to immediately

**Issues:**
* [X] Enkelt brukergrensesnitt for selve applikasjonen
* [X] Brukergrensesnitt innlogging
* [X] Håndtdering av brukernavn / passord (midlertidlig)
