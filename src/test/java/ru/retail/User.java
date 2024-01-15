package ru.retail;

public class User {
    protected String email;
    protected String pass;

    public String getEmail() {
        return email;
    }
    public void setEmail(String emailOpt) {
        this.email = email;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String passOpt) {
        this.pass = pass;
    }

    public User (String emailOpt, String passOpt) {
        this.email = emailOpt;
        this.pass = passOpt;
    }

}
