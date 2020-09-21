# NTNU - Choose the right course?

This project is a three-layer application, with domain, user interface (UI) and persistence. The project consists of tests with good coverage and is configured to use **maven** as build tool.


## The organization of the code
The project is organized with 2 * 2 = 4 source code folders, code and resources for the application itself and the tests, respectively:

* **src/main/java** for the code of the application

* **src/main/resources** for associated resources, such as FXML-files or data-files that the application uses.  

* **src/test/java** for the testcode.

* **src/test/resources** for associated resources, such as FXML-files or data-files that is used by the tests.


## The Domain-layer
The domain-layer consists of all classes and logic related to the data which the application is about and deals with. The classes to related database-management and the chatbot-management can be found in the studit.core-package. 

## The UI-layer
The UI-layer consits of all classes and logic related to the visuals and actions affecting the UI. The user interface of the app stats with a login-page which leads to the main-page where the user can see a overview of the courses. In addition there is a button, when clicked on, a chatbot appears which works as a overlay on the main-page.
The UI is developed with JavaFX and FXML and can be found in the studit.main.resources.ui-package (the JavaFX-code in src/main/java and the FXML-files in src/main/resources)

## The Persistence-layer
The persistence-layer consists of all classes and logic related to storage of the data in the domainlayer. Our persistence-layer is not yet implemented. 

