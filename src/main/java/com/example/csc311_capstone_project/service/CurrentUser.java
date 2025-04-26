package com.example.csc311_capstone_project.service;

/**
 * Tracks the current user that is using the application
 * Stores their email and username so that their records are trackable.
 * @since 4/26/2025
 * @author Nathaniel Rivera
 */
public class CurrentUser {

    /**
     * A static string current username of the logged-in User.
     */
    private static String currUsername;
    /**
     * A static string current email of the logged-in User.
     */
    private static String currEmail;

    /**
     * Static method which sets the current user by getting the username and email of the User
     * @param username The username of the new current User
     * @param email The email of the new current User
     * @since 4/26/2025
     * @author Nathaniel Rivera
     */
    public static void setCurrentUser(String username, String email) {
        currUsername = username;
        currEmail = email;
    }

    /**
     * Static method which returns the username of the current User.
     * @return The current User's username
     * @since 4/26/2025
     * @author Nathaniel Rivera
     */
    public static String getCurrentUsername() {
        return currUsername;
    }

    /**
     * Static method which returns the email of the current User.
     * @return The current User's email
     * @since 4/26/2025
     * @author Nathaniel Rivera
     */
    public static String getCurrentEmail() {
        return currEmail;
    }
}
