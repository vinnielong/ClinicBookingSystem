/*
 * Xuan Son
 * 
 * Jan 17, 2022
 *
 */
package model;

import java.io.Serializable;
import java.sql.Blob;

/**
 *
 * @author Xuan Son
 */
public class Account implements Serializable{

    private int id;
    private String fullName;
    private int gender;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String code;
    private int role;
    private int status;
    private Blob avatar;
    private static final long serialVersionUID = 6106269076155338045L;

    public Account() {
    }

    public Account(String fullName, int gender, String email, String phoneNumber, String username, String password, String code) {
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.code = code;
    }

    public Account(int id, String fullName, int gender, String email, String phoneNumber, String username, String password, int role, int status) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public Account(int id, String fullName, int gender, String email, String phoneNumber, String username, String password, int role, int status, Blob avatar, String code) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.avatar = avatar;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Blob getAvatar() {
        return avatar;
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
