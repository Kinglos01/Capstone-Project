package com.example.csc311_capstone_project;

/**\
 * This class is being created to collect the user information from the sign in  page
 * this will allow us to collect the information and organize it in a useful means
 * this class was connected at the else statement towards the bottom of the registerSetup() method
 * @date 03/20
 * @author Carlos Berio
 */

public class User {

    private String firstName;
    private String lastName;
    private int id;
    private String username;
    private String email;
    private String password;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

}
