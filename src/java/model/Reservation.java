/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Reservation {

    private int id;
    private Patient patientname;
    private Service servicename;
    private Date date;
    private int status;
    private Account staff;
    private String description;
    private Settings slot;

    public Reservation() {
    }

    public Reservation(int id, Patient patientname, Service servicename, Date date, int status, Account staff, String description, Settings slot) {
        this.id = id;
        this.patientname = patientname;
        this.servicename = servicename;
        this.date = date;
        this.status = status;
        this.staff = staff;
        this.description = description;
        this.slot = slot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatientname() {
        return patientname;
    }

    public void setPatientname(Patient patientname) {
        this.patientname = patientname;
    }

    public Service getServicename() {
        return servicename;
    }

    public void setServicename(Service servicename) {
        this.servicename = servicename;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Settings getSlot() {
        return slot;
    }

    public void setSlot(Settings slot) {
        this.slot = slot;
    }

    public Account getStaff() {
        return staff;
    }

    public void setStaff(Account staff) {
        this.staff = staff;
    }

}
