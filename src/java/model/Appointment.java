/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Vinnie Long
 */
public class Appointment {

    private int ID;
    private Patient patient;
    private Doctor doctor;
    private Date date;
    private int status;
    private String description;
    private Settings slot;
    private Account staff;

    public Appointment() {
    }

    public Appointment(int ID, Patient patient, Doctor doctor, Date date, int status, String description, Settings slot, Account staff) {
        this.ID = ID;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.status = status;
        this.description = description;
        this.slot = slot;
        this.staff = staff;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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
