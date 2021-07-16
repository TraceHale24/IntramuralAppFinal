package com.example.intramuralsappfinal.models.request;

public class TeamsRequest {
    private String netID;
    //Make this a user potentially? Would allow us to have access to more data through the program.
    private TeamsRequest(String netID) {
        this.netID = netID;
    }

    private TeamsRequest() {}

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public String getNetID() {
        return this.netID;
    }

    /*
    private User user;
    private TeamsRequest(User user) {
        this.user = user;
    }

    private TeamsRequest() {}

    public void setUser(this.user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
     */
}
