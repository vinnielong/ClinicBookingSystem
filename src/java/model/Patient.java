/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

/**
 *
 * @author Vinnie Long
 */
public class Patient implements Serializable {

    private int ID;
    private String name;
    private int gender;
    private Date DOB;
    private String phone;
    private String email;
    private int status;
    private String address;
    private Blob image;
    private String imageString;
    private Date maxDate;
    private static final long serialVersionUID = 6106269076155338045L;

    public Patient() {
    }

    public Patient(int ID, String name, int gender, Date DOB, String phone, String email, int status, String address, Blob image, String imageString) {
        this.ID = ID;
        this.name = name;
        this.gender = gender;
        this.DOB = DOB;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.address = address;
        this.image = image;
        this.imageString = imageString;
    }

    public Patient(int ID, String name, int gender, Date DOB, Date maxDate, String phone, String email, String imageString) {
        this.ID = ID;
        this.name = name;
        this.gender = gender;
        this.DOB = DOB;
        this.maxDate = maxDate;
        this.phone = phone;
        this.email = email;
        this.imageString = imageString;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String description) {
        this.address = description;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

}
