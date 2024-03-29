package com.wallet;

public class LoginDto {
    String name;
    String password;
    String role;


    public LoginDto() {
    }

    public LoginDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public LoginDto(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
