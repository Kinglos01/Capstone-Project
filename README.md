# autoCommerce Application

## Table of Contents

- [Introduction](#introduction)
- [How to Use](#how-to-use)
- [Layouts and Formatting](#layouts-and-formatting)
  - [Splash Screen](#splash-screen)
  - [Login Screen](#login-screen)
  - [Register Screen](#register-screen)
  - [Landing Screen](#landing-screen)
  - [Scanner Screen](#scanner-screen)
  - [Item Screen](#item-screen)
- [Technology](#technology)
- [Presentation](#presentation)
- [Contributors](#contributors)

## Introduction

The autoCommerce application allows users to store invoices quickly and efficiently. Its main feature is an auto invoice parser, which allows the user to automatically scan an invoice into the system without having to store any of the text on their own.

## How to Use

autoCommerce allows users to store their lists of invoices and items and, therefore, requires them to create and use an account. If the user does not possess an account, it is important that they go to the register page to make one. After creating an account, the user can log in and start up the main application.

Before parsing an invoice the values of the items on the invoice must be inputted into the system. On the items page, the user can either import and CSV or TXT file see the item screen for the format. Once the items are inputted into the item list the user is ready to use the automatic invoice scanner. If the user desires to use the automatic field generate, the invoice must follow the following [template invoices](https://mega.nz/file/tlggXDaT#HUWvAdF_WVcEwoSpare4bWBUpwnBVXPC8IvbkNwrBVI) format. The invoice must also be a PDF file. Once all these requirements are met the user can begin to automatically scan invoices into the system for storage (see requirements in the Scanner Screen section).

## Layouts and Formatting

### Splash Screen

![Splash Screen](https://github.com/user-attachments/assets/62746a78-9ce9-4fb1-9ca8-944207e4a003)

The Splash Screen of the autoCommerce application acts as a hub that allows one to access the other portions of the program. The Splash Screen showcases a slick design that has its own customized taskbar. The taskbar consists of three options, a custom Close button, which closes the application normally but sports a nice and clean design. The Minimize button serves a similar function as it replaces the standard minimize with a cleaner-looking button. The last button on the new taskbar is the Login button which opens the Login pages where users are able to make sure they are using their account to save their data. All of this is accompanied by a custom splash art which adds charm to the splash page. The most important function of the Splash Screen is the launch button which closes the Splash Screen but sends the user to the application with the account of their choosing.

### Login Screen

![image](https://github.com/user-attachments/assets/62df0940-31a1-48d5-9601-7a3384820920)

The Login Screen of the autoCommerce application is a standard login screen for an application. The login screen has a refreshing design with its own customized taskbar. The taskbar consists only of a close button but with a cleaner more refreshing design. The Login Screen has three required fields which represent the user's username, email, and password. The login screen has two buttons other than the taskbar, the login button and the Register button. The Log button exactly as stated logs in checks the email, username, and password of the user, and checks them against the users entered into the database. If the user is not in the database, the login will be rejected and the user will stay on the login screen with an error message displayed. If the user is already located within the database the user will be logged in and sent back to the Splash Screen. The other button on the Login screen is the register button. The button appears as a text label saying "Create an Account Here." When clicked on the Login will be closed and the Register screen will be opened in its place.

### Register Screen

![image](https://github.com/user-attachments/assets/e8fff275-3896-4cfb-b2c1-9a4394e45df9)

The Register Screen of the autoCommerce application is a standard register screen for an application. The Register Screen possesses 5 necessary text fields for the user to fill out username, email, password, first name, and last name.

The username of the user must follow the following criteria, it must be 2-25 characters long, it must consist of only the word characters as well as the - character, and it must also be unique in that two users may not share the same username.

The email of the user must follow the following criteria, it must be a valid email containing an @ and a .domain, and it must also be unique in that two users may not share the same username.

The password of the user must follow the following criteria, it must be 2-25 characters long, and it must consist of only word characters.

The first and last name of the user must be 2-25 characters long as well as only consisting of letters and 's. 

### Landing Screen

![color change landing](https://github.com/user-attachments/assets/9b3741e4-af5c-4070-b080-9549fee32bf7)


The Landing Screen acts as a hub for the functional portitions of the applciation. It contains the feature log out and a close button which shuts down the application as a whole. The landing page allows users to delete invoices and mark previously non-delivered invoices as delivered. The Landing Page contains the branches to the Scanner and Item Screen and allows users to go straight to those pages. The user is able to export their own invoice lists into a CSV file.

![color change landing full screen](https://github.com/user-attachments/assets/837c4bd6-d58c-4a83-a5be-b34f9b777595)
Full Screen Landing Screen

### Scanner Screen

![image](https://github.com/user-attachments/assets/5aa0f7e0-6aac-440a-8cd3-e095e3d9c4d4)

The Scanner Screen of the autoCommerce application consists of a multitude of fields for the user to enter. The fields are invoice number, customer account ID, order date, delivery date, shipping address, status, and the items. The user can also use the Generate Fields button to allow the invoice to be automatically parsed by the application.

The invoice number must follow the following criteria, it must start with **IN followed by 8 number characters**, no two invoices that belong to the same user may share an ID, and this field is mandatory.

The customer ID must follow the following criteria, it must start with **CUS followed by 7 number characters**, no two customers can share an ID, and this field is mandatory.

The order date must follow the following criteria, it must be in **MM-DD-YYYY** format separated by dashes, and this field is mandatory

The delivery date must follow the following criteria, it must be in **MM-DD-YYYY** format separated by dashes, and this field is not mandatory.

The shipping address must follow the following criteria, it must be written as **STREET ADDRESS(Allows all characters), CITY(Allows all characters), STATE(2 letter characters), ZIPCODE(5 number digits)**, this field is mandatory.

The status field must follow the following criteria, it must be one of the four terms, **Delivered, Not Delivered, En-Route, or Unknown**.

The item fields must follow the following criteria, they must be written in the order of **ITEM NAME:QUANTITY** if there are multiple items they must be split with a comma with the final value having no comma at the end. For items in the scanner to be blank, the user can insert None into the item section with 0 as its price. This will allow the scanner to flow freely and allow the user to input a range of items. 

![image](https://github.com/user-attachments/assets/3fd049df-5709-47d9-a5eb-eb6c4447663f)
Scanner Screen in with filled fields.

### Item Screen

![image](https://github.com/user-attachments/assets/9d60ac11-07b7-421b-ba59-69c86817db3e)

The Item Screen of the autoCommerce applciation is a list where users can add and remove items from their list of items. The user can add, remove, or edit the item's names and their prices within the field. The user is also able to import a **CSV or TXT** file which stores the items alongside their price with the **ITEM NAME,ITEM PRICE** format. The user is able to export their own item lists into a CSV file.

The item name must follow the following criteria, it must be **2, 25 characters**.

The price of the item must folllow the following criteria, it must be a **double with at most 2 significant figures after the decimal**.

## Technology

1. IntelliJ IDEA Community Edition

2. Java

3. JavaFX

5. Maven

6. Apache PDFBox

7. iTextPDF.

8. Microsoft Azure

9. MySQL

## Presentation

[Presentation Link](https://www.youtube.com/watch?v=jKpwuv_Gidg)
    
## Contributors

Berio, Carlos

Costa, Anthony

Mitchell, Jared

Rivera, Nathaniel 

Rodriguez, Aidan
