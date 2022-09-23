# Divinity Squad Bank
## Made by Flawden

A banking application for issuing loans, debit and credit cards, obtaining information on accounts by bank customers.

## Features 

- Issuance of loans and credit cards for them.
- Opening debit cards, for keeping money in the bank
- Displaying basic information about customers and their accounts with thymeleaf
- Registration of new users in the system, their removal and editing

The banking system is easy to use. The main advantage of this application is the absence of Spring boot, which allows you to look at the Spring MVC configuration with your own eyes and see what Spring boot left behind the scenes.

# Preparation for use

Before using, you need to get the project on your device. To do this, use the "Code" button at the top of the page with the repository and choose a convenient method for obtaining it, or use the terminal command 
> git clone https://github.com/Flawden/Divinity-Squad-Bank.git

Next, you need to set up the project to work with your databases. To do this, go to the resources folder:

> src/main/webapp/resources

Make a copy of the hibernate.properties.origin file, removing the ".origin" extension from the name of the resulting copy (You should get "hibernate.properties")

Open the resulting file, we will carry out a couple of manipulations:

> hibernate.driver_class= (Enter your database driver here)
> hibernate.connection.url= (Enter your database link here)
> hibernate.connection.username= (Here enter your database username)
> hibernate.connection.password= (Enter your database password here)
> hibernate.hbm2ddl.auto= (Here enter the mode of your database creation process (create, update or whatever you need)
> hibernate.dialect= (Enter your database dialect)
> hibernate.show_sql=false (Change to true if you want to see information on hibernate queries to the database)

Congratulations. The application is ready to build.

# Application assembly

You can build with Maven. There are two ways

1) Through the IDE (I'll show you with Intellij Idea as an example)
2) Using the command line

## Building with the IDE

To assemble, follow these steps:

1) Run the project in IntelliJ IDEA IDE
2) Open the Maven menu
3) Go to Lifecycle tab
4) Click clean
5) Click package

You now have a WAR ready to upload to your server.
(If for some reason you can't find the Maven menu, the online guides will surely help you)

## Building with Maven 

To build with [Maven](https://maven.apache.org/), first make sure you have it installed on your computer.

If maven is already installed - open a command line and navigate to the project folder using the command
> cd <Path_to_Project>

Next, enter the following 2 commands in turn:
> mvn clean
> mvn install

A "Target" folder will appear in the root of the project. Log into it via the console. The folder will contain a .jar file

Run it with the command:
>  java -jar <Full_name_of_jar_file>

