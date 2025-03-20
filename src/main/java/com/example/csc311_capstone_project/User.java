package com.example.csc311_capstone_project;

/**
 * This class is being created to collect the user information from the sign-in page
 * this will allow us to collect the information and organize it in a useful means
 * this class was connected at the else statement towards the bottom of the registerSetup() method.
 * @since 3/20/25
 * @author Carlos Berio, Nathaniel Rivera
 */
public class User {

    /**
     * String firstName holds the first name of the user
     */
    private String firstName;
    /**
     * String lastName holds the last name of the user
     */
    private String lastName;
    /**
     * String id holds the id of the user
     */
    private int id;
    /**
     * String username holds the username of the user
     */
    private String username;
    /**
     * String firstName holds the first name of the user
     */
    private String email;
    /**
     * String email holds the email of the user
     */
    private String password;

    /**
     * Default constructor for the User
     * @since 3/20/25
     * @author Nathaniel Rivera
     */
    User() {
        firstName = "";
        lastName = "";
        id = 0;
        username = "";
        email = "";
        password = "";
    }

    /**
     * Parameterized constructor for the User class.
     * @param inFirst The initial first name of the User.
     * @param inLast The initial last name of the User.
     * @param inUser The initial username of the User.
     * @param inEmail The initial email of the User.
     * @param inPass The initial password of the User.
     * @since 3/20/25
     * @author Nathaniel Rivera
     */
    User(String inFirst, String inLast, String inUser, String inEmail, String inPass) {
        firstName = inFirst;
        lastName = inLast;
        username = inUser;
        email = inEmail;
        password = inPass;
        id = 0;
    }

    /**
     * Setter method for the firstName param
     * @param firstName The new firstName of the user
     * @since 3/20/25
     * @author Carlos Berio
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter method for the firstName param.
     * @return The firstName of the User.
     * @since 3/20/25
     * @author Carlos Berio
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter method for the lastName param
     * @param lastName The new lastName of the user
     * @since 3/20/25
     * @author Carlos Berio
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter method for the lastName param.
     * @return The lastName of the User.
     * @since 3/20/25
     * @author Carlos Berio
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter method for the id param
     * @param id The new id of the user
     * @since 3/20/25
     * @author Carlos Berio
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter method for the id param.
     * @return The id of the User.
     * @since 3/20/25
     * @author Carlos Berio
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for the username param.
     * @param username The new username of the user.
     * @since 3/20/25
     * @author Carlos Berio
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter method for the username param.
     * @return The username of the User.
     * @since 3/20/25
     * @author Carlos Berio
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter method for the email param.
     * @param email The new email of the user.
     * @since 3/20/25
     * @author Carlos Berio
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method for the email param.
     * @return The email of the User.
     * @since 3/20/25
     * @author Carlos Berio
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for the password param
     * @param password The new password of the user
     * @since 3/20/25
     * @author Carlos Berio
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for the password param.
     * @return The password of the User.
     * @since 3/20/25
     * @author Carlos Berio
     */
    public String getPassword() {
        return password;
    }

}