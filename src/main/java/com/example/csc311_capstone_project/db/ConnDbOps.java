/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.csc311_capstone_project.db;

import com.example.csc311_capstone_project.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class which handles the database operations for all the tables.
 * This includes creating, returning, deleting, and editing the tables
 * @since 4/21/2025
 * @author Nathaniel Rivera
 */
public class ConnDbOps {
    final String MYSQL_SERVER_URL = "jdbc:mysql://jabaltariq2.database.windows.net/";
    final String DB_URL = MYSQL_SERVER_URL + "/DBname";
    final String USERNAME = "jabaltariq";
    final String PASSWORD = "thisisouradminpassword1";

    /**
     * Current username of the user.
     */
    private String currUsername;
    /**
     * Current email of the user.
     */
    private String currEmail;

    /**
     * Connects to the Database and creates the tables if they do not already exist.
     * @since 4/21/2025
     * @author Nathaniel Rivera
     */
    public void connectToDatabase() {

        try {
            //First, connect to MYSQL server and create the database if not created
            Connection conn = DriverManager.getConnection(MYSQL_SERVER_URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS DBname");
            statement.close();
            conn.close();

            //Second, connect to the database and create the table "users" if cot created
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users ("
                    + "username VARCHAR(50) NOT NULL,"
                    + "email VARCHAR(50) NOT NULL,"
                    + "[password] VARCHAR(50) NOT NULL,"
                    + "first_name VARCHAR(50),"
                    + "last_name VARCHAR(50),"
                    + "CONSTRAINT pk_users PRIMARY KEY (username, email)"
                    + ")";
            statement.executeUpdate(sql);

            statement = conn.createStatement();
            String sql2 = "CREATE TABLE IF NOT EXISTS invoice ("
                    + "invoice_id VARCHAR(50) NOT NULL,"
                    + "username VARCHAR(50) NOT NULL,"
                    + "email VARCHAR(50) NOT NULL,"
                    + "order_date DATE,"
                    + "delivery_date DATE,"
                    + "[status] VARCHAR(50),"
                    + "account_id VARCHAR(50),"
                    + "invoice_image VARCHAR(250),"
                    + "invoice_name VARCHAR(50),"
                    + "CONSTRAINT pk_invoice PRIMARY KEY (invoice_id, username, email),"
                    + "CONSTRAINT fk_invoice_users FOREIGN KEY (username, email) REFERENCES users(username, email),"
                    + "CONSTRAINT fk_invoice_customer FOREIGN KEY (account_id) REFERENCES customer(account_id)"
                    + ")";
            statement.executeUpdate(sql2);

            statement = conn.createStatement();
            String sql3 = "CREATE TABLE IF NOT EXISTS customer ("
                    + "account_id VARCHAR(50) NOT NULL,"
                    + "[address] VARCHAR(50) NOT NULL,"
                    + "CONSTRAINT pk_customer PRIMARY KEY (account_id)"
                    + ")";
            statement.executeUpdate(sql3);

            statement = conn.createStatement();
            String sql4 = "CREATE TABLE IF NOT EXISTS items ("
                    + "item_id VARCHAR(50),"
                    + "price MONEY,"
                    + "CONSTRAINT pk_user PRIMARY KEY (item_id)"
                    + ")";
            statement.executeUpdate(sql4);

            statement = conn.createStatement();
            String sql5 = "CREATE TABLE IF NOT EXISTS users ("
                    + "username VARCHAR(50) NOT NULL,"
                    + "email VARCHAR(50) NOT NULL,"
                    + "[password] VARCHAR(50) NOT NULL,"
                    + "first_name VARCHAR(50),"
                    + "last_name VARCHAR(50),"
                    + "CONSTRAINT pk_user PRIMARY KEY (username, email)"
                    + ")";
            statement.executeUpdate(sql5);

            statement.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new user into the SQL table user. The PK consists of username and email,
     * both of which must be unique and NOT NULL.
     * @param username The username of the user must be unique.
     * @param email The email of the user must be unique.
     * @param password The password of the user.
     * @param first_name The first_name of the user.
     * @param last_name The last_name of the user.
     * @since 4/14/2025
     * @author Nathaniel Rivera
     */
    public void insertUser(String username, String email, String password, String first_name, String last_name) {

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "INSERT INTO users (username, email, password, first_name, last_name) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, first_name);
            preparedStatement.setString(5, last_name);

            int row = preparedStatement.executeUpdate();

            if (row > 0) {
                System.out.println("A new user was inserted successfully.");
            }

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new invoice into the SQL Table invoice. The PK consists of invoice_id, username and email.
     * Username and Email are both foreign keys from the user table.
     * @param invoice_id The id for the current invoice must be unique for the current user.
     * @param order_date The date the invoice was ordered.
     * @param delivery_date The day the invoice was delivered if applicable.
     * @param status The current status of the invoice.
     * @param account_id The id of the account on the invoice.
     * @param invoice_image The image-path of the selected invoice.
     * @since 4/14/2025
     * @author Nathaniel Rivera
     */
    public void insertInvoice(String invoice_id, String order_date , String delivery_date, String status, String account_id, String invoice_image, String invoice_name) {

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "INSERT INTO invoice (invoice_id, username, email, order_date, delivery_date, status, account_id, invoice_image, invoice_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, invoice_id);
            preparedStatement.setString(2, currUsername);
            preparedStatement.setString(3, currEmail);
            preparedStatement.setString(4, order_date);
            preparedStatement.setString(5, delivery_date);
            preparedStatement.setString(6, status);
            preparedStatement.setString(7, account_id);
            preparedStatement.setString(8, invoice_image);
            preparedStatement.setString(9, invoice_name);

            int row = preparedStatement.executeUpdate();

            if (row > 0) {
                System.out.println("A new invoice was inserted successfully.");
            }

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes the selected invoice from the table of invoices. The invoice deleted
     * depends on the current User
     * @param invoice_id The id of the invoice being deleted.
     * @since 4/26/2025
     * @author Nathaniel Rivera
     */
    public void removeInvoice(String invoice_id) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "DELETE FROM invoice WHERE invoice_id = " + invoice_id + " and username = '" + currUsername + "'" + " and email = '" + currEmail + "'";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new customer into the SQL Table invoice.
     * This PK consists of account_id alone.
     * @param account_id The account id of the customer must be unique.
     * @param address The address for the customer
     * @since 4/21/2025
     * @author Nathaniel Rivera
     */
    public void insertCustomer(String account_id, String address) {

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "INSERT INTO customers (account_id, [address]) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, account_id);
            preparedStatement.setString(2, address);

            int row = preparedStatement.executeUpdate();

            if (row > 0) {
                System.out.println("A new customer was inserted successfully.");
            }

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a list of the Users inputted into the database.
     * @return A list of all the registered users.
     * @since 4/26/2025
     * @author Nathaniel Rivera
     */
    public List<User> retrieveUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM users";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                users.add(new User(first_name, last_name, username, email, password));
            }

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Sets the current User of the application. This must be done before
     * accessing the invoices table in any way.
     * @param username The current User's username
     * @param email The current User's email
     * @since 4/26/2025
     * @author Nathaniel Rivera
     */
    public void setCurrentUser(String username, String email) {
        currUsername = username;
        currEmail = email;
    }

}