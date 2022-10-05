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
public class Doctor implements Serializable {

    private int id;
    private String doctorName;
    private String role;
    private int gender;
    private Date dob;
    private String address;
    private double rate;
    private String phone;
    private String email;
    private int status;
    private Settings speciality;
    private String description;
    private Blob image;
    private String imageString;
    private static final long serialVersionUID = 6106269076155338045L;

    public Doctor() {
    }

    public Doctor(int id, String doctorName, String role, int gender, Date dob, String address, double rate, String phone, String email, int status, Settings speciality, String description, Blob image, String imageString) {
        this.id = id;
        this.doctorName = doctorName;
        this.role = role;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.rate = rate;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.speciality = speciality;
        this.description = description;
        this.image = image;
        this.imageString = imageString;
    }
    
    public Doctor(int id, String doctorName, String role, int gender, Date dob, String phone, String email, int status, Settings speciality, String description, Blob image, String imageString) {
        this.id = id;
        this.doctorName = doctorName;
        this.role = role;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.speciality = speciality;
        this.description = description;
        this.image = image;
        this.imageString = imageString;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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

    public Settings getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Settings speciality) {
        this.speciality = speciality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
