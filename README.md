# Automated Inventory System

## Introduction

## How to Use

## Layouts and Formatting

### Splash Screen

The Splash Screen of the autoCommerce application acts as a hub that allows one to access the other portions of the program. The Splash Screen showcases a slick design that has its own customized taskbar. The taskbar consists of three options, a custom Close button, which closes the application normally but sports a nice and clean design. The Minimize button serves a similar function as it replaces the standard minimize with a cleaner-looking button. The last button on the new taskbar is the Login button which opens the Login pages where users are able to make sure they are using their account to save their data. All of this is accompanied by a custom splash art which adds charm to the splash page. The most important function of the Splash Screen is the launch button which closes the Splash Screen but sends the user to the application with the account of their choosing.

### Login Screen

The Login Screen of the autoCommerce application is a standard login screen for an application. The login screen has a refreshing design with its own customized taskbar. The taskbar consists only of a close button but with a cleaner more refreshing design. The Login Screen has three required fields which represent the user's username, email, and password. The login screen has two buttons other than the taskbar, the login button and the Register button. The Log button exactly as stated logs in checks the email, username, and password of the user, and checks them against the users entered into the database. If the user is not in the database, the login will be rejected and the user will stay on the login screen with an error message displayed. If the user is already located within the database the user will be logged in and sent back to the Splash Screen. The other button on the Login screen is the register button. The button appears as a text label saying "Create an Account Here." When clicked on the Login will be closed and the Register screen will be opened in its place.

### Register Screen

The Register Screen of the autoCommerce application is a standard register screen for an application. The Register Screen possesses 5 necessary text fields for the user to fill out username, email, password, first name, and last name.

The username of the user must follow the following criteria, it must be 2-25 characters long, it must consist of only the word characters as well as the - character, and it must also be unique in that two users may not share the same username.

The email of the user must follow the following criteria, it must be a valid email containing an @ and a .domain, and it must also be unique in that two users may not share the same username.

The password of the user must follow the following criteria, it must be 2-25 characters long, and it must consist of only word characters.

The first and last name of the user must be 2-25 characters long as well as only consisting of letters and 's. 

### Scanner Screen

The Scanner Screen of the autoCommerce application consists of a multitude of fields for the user to enter. The fields are invoice number, customer account ID, order date, delivery date, shipping address, status, and the items.

The invoice number must follow the following criteria, it must start with **IN followed by 8 number characters**, no two invoices that belong to the same user may share an ID, and this field is mandatory.

The customer ID must follow the following criteria, it must start with **CUS followed by 7 number characters**, no two customers can share an ID, and this field is mandatory.

The order date must follow the following criteria, it must be in **MM-DD-YYYY** format separated by dashes, and this field is mandatory

The delivery date must follow the following criteria, it must be in **MM-DD-YYYY** format separated by dashes, and this field is not mandatory.

The shipping address must follow the following criteria, it must be written as **STREET ADDRESS(Allows all characters), CITY(Allows all characters), STATE(2 letter characters), ZIPCODE(5 number digits)**, this field is currently mandatory but may change in later versions.

The status field must follow the following criteria, it must be one of the four terms, **Delivered, Not Delivered, En-Route, or Unknown**.

The item fields must follow the following criteria, it must be written in the order of **ITEM NAME:QUANTITY** if there are multiple items they must be split with a comma with the final value having no comma at the end.

### Item Screen

The Item Screen of the autoCommerce applciation is a list where users can add and remove items from their own personal list of items. The user can add, remove or edit the item's names and their prices within the field. The user is also able to import a **CSV or TXT** file which stores the items alongside their price with the **ITEM NAME,ITEM PRICE** format.

The item name must follow the following criteria, it must be **2, 25 characters**.

The price of the item must folllow the following criteria, it must be a **double with at most 2 significant figures after the decimal**.

## Contributors

Berio, Carlos

Costa, Anthony

Mitchell, Jared

Rivera, Nathaniel 

Rodriguez, Aidan
