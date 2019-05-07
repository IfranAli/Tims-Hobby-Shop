package com.csci334.TimsHobbyShop.DTO;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class RegisterForm {

    @NotNull @Size(min=2, max=30)
    private String name;
    @NotNull @Size(min=2, max=30)
    private String username;
    @NotNull @Size(min=2, max=30)
    private String email;
    @NotNull @Size(min=2, max=30)
    private String phone;
    @NotNull @Size(min=2, max=30)
    private String address;
    @NotNull @Size(min=2, max=30)
    private String password;
    @NotNull @Size(min=2, max=30)
    private String passwordConfirm;
    @NotNull @Size(min=2, max=30)
    private String creditline;
    @NotNull @Positive
    private double balance;

    public RegisterForm() {}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreditline() {
        return creditline;
    }

    public void setCreditline(String creditline) {
        this.creditline = creditline;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}