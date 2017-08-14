## GamingPlatform

Project for Object-Oriented-Software-Design course of [University of L'Aquila](http://univaq.it)

# Team

- D'Ascenzo Andrea
- Icaro Iari
- Martella Stefano
- Morelli Davide
- Tramontozzi Paolo
# Repository structure


# Prerequisites

 - [Java](https://www.java.com/it/download/) to run application.
 - [MySql](https://www.mysql.com/it/downloads/) to make app work with database.
 
 # Start using it
 
 In order to run the project create a user in the MySQL database with:
 
 ```CREATE USER 'gaming'@'localhost' IDENTIFIED BY 'gaming';```
 
 Then give him right privileges with:
 
 ```GRANT ALL PRIVILEGES ON * . * TO 'gaming'@'localhost';```
 
 Now import  this project or cloning it by entering the following command in the command line:
 
 ```git clone https://github.com/StefanoMartella/GamingPlatform.git```
 
 Either downloading the zip from this site.
 Now move into the downloaded/cloned directory and enter the following command to import database:
 
 ```mysql -u gaming -p < src/database/SQL.sql```
 
 You will be prompted for password which is ```gaming```.
 You are finally ready to launch application with:
 
 ```java ----```
