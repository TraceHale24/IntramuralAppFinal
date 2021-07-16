package com.example.intramuralsappfinal.models.request;

/**
 * Contains all the information needed to make a login request.
 */
public class LoginRequest {

    private String email;
    private String password;

    private LoginRequest() {
        password = null;
        email = null;
    }

    /**
     * Creates an instance.
     *
     * @param email the username of the user to be logged in.
     * @param password the password of the user to be logged in.
     */
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the username of the user to be logged in by this request.
     *
     * @return the username.
     */
    public String getUsername() {
        return email;
    }

    /**
     * Returns the password of the user to be logged in by this request.
     *
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}