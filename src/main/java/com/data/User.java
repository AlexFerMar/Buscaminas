package com.data;

public class User {

    private String code;

    private String name;

    private String password;


    public User(String code, String name, String password) {
        this.code = code;
        this.name = name;
        this.password = password;

    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
