package com.example.csc311_capstone_project;

/**\
 * This class is being created to collect the user information from the sign-in  page
 * this will allow us to collect the information and organize it in a useful means
 * this class was connected at the else statement towards the bottom of the registerSetup() method
 * @date 03/20
 * @author Carlos Berio
 */

public class User {
    /**
     * setting all variables at the top to be used in future methods making sure they are private to
     * follow the rules of encapsulation
     */
    private String firstName;
    private String lastName;
    private int id;
    private String username;
    private String email;
    private String password;

    /**
     * collecting the first name from the user input
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     *creating get method for future uses
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * collecting the last name from the user input
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * creating get method for future uses
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * may be unused but for now is a placeholder if we wanted a secret key to be connected for any sql code
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * making us able to collect the id if needed
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     *  collecting the username from the user input
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * creating a get method for future uses of username
     * @return username
     */
    public String getUsername() {
        return username;
    }
    /**
     *  collecting the email from the user input
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * creating a get method to get the email from a user
     * @return email
     */
    public String getEmail() {
        return email;
    }
    /**
     * collecting the password from the user input
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * creating a gte method so we can use the users password in other locations
     * @return password
     */
    public String getPassword() {
        return password;
    }

}
