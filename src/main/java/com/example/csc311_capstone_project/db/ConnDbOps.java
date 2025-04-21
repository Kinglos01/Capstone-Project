/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.csc311_capstone_project.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnDbOps {
    final String MYSQL_SERVER_URL = "jdbc:mysql://jabaltariq2.database.windows.net/";
    final String DB_URL = MYSQL_SERVER_URL + "/DBname";
    final String USERNAME = "jabaltariq";
    final String PASSWORD = "thisisouradminpassword1";

    /**
     *
     * @return
     */
    public  boolean connectToDatabase() {
        boolean hasRegistredUsers = false;


        //Class.forName("com.mysql.jdbc.Driver");
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
                    + "[status] VARCHAR(50)"
                    + "account_id VARCHAR(50)"
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

            //check if we have users in the table users
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");

            if (resultSet.next()) {
                int numUsers = resultSet.getInt(1);
                if (numUsers > 0) {
                    hasRegistredUsers = true;
                }
            }

            statement.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hasRegistredUsers;
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
     * @param username The username for the user, must match one of the usernames in the user table.
     * @param email The current email for the user, must match one of the emails in the user table.
     * @param order_date The date the invoice was ordered.
     * @param delivery_date The day the invoice was delivered if applicable.
     * @param status The current status of the invoice.
     * @param account_id The id of the account on the invoice.
     * @since 4/14/2025
     * @author Nathaniel Rivera
     */
    public void insertInvoice(String invoice_id, String username, String email, String order_date , String delivery_date, String status, String account_id) {

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "INSERT INTO invoice (invoice_id, username, email, order_date, delivery_date, status, account_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, invoice_id);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, order_date);
            preparedStatement.setString(5, delivery_date);
            preparedStatement.setString(6, status);
            preparedStatement.setString(7, account_id);

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

}