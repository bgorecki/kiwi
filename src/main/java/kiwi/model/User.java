package kiwi.model;

public abstract class User {
    protected String username;
    protected String password;   
    
    public boolean authenticate() {
        return true;
    }
    
    public boolean authorizate() {
        return true;
    }
}
