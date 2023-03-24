package com.wallet;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Objects;

@Entity
public class WalletDto { // POJO
    @Id
    private Integer id;
    private String name;
    //@JsonIgnore
    private String password;
    private Double balance;
    @Transient
    String jwt;
    String role;
    // email, pWD, date of wallet creation

    public WalletDto() {
    }

    public WalletDto(Integer id, String name, String password, Double balance) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public WalletDto(Integer id, String name, String password, Double balance, String jwt, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.jwt = jwt;
        this.role = role;
    }

    public WalletDto(Integer id, String name, String password, Double balance, String jwt) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.jwt = jwt;
    }
    //    public WalletDto(Integer id, String name, Double balance) {
//        this.id = id;
//        this.name = name;
//        this.balance = balance;
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletDto walletDto = (WalletDto) o;
        return Objects.equals(id, walletDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
