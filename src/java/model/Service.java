/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Blob;
/**
 *
 * @author Vinnie Long
 */
public class Service {
    private int ID;
    private String title;
    private Settings category;
    private int price;
    private Blob thumbnail;
    private String thumbnailString;
    private int status;
    private String description;

    public Service() {
    }

    public Service(int ID, String title, Settings category, int price, String thumbnailString, int status, String description) {
        this.ID = ID;
        this.title = title;
        this.category = category;
        this.price = price;
        this.thumbnailString = thumbnailString;
        this.status = status;
        this.description = description;
    }
    
    

    public Service(int ID, String title, Settings category, int price, Blob thumbnail, int status, String description) {
        this.ID = ID;
        this.title = title;
        this.category = category;
        this.price = price;
        this.thumbnail = thumbnail;
        this.status = status;
        this.description = description;
    }
    
    public Service(int ID, String title, Settings category, int price, int status, String description) {
        this.ID = ID;
        this.title = title;
        this.category = category;
        this.price = price;
        this.status = status;
        this.description = description;
    }
    
    public Service(String title, Settings category, int price, int status, String description) {
        this.title = title;
        this.category = category;
        this.price = price;
        this.status = status;
        this.description = description;
    }

    public String getThumbnailString() {
        return thumbnailString;
    }

    public void setThumbnailString(String thumbnailString) {
        this.thumbnailString = thumbnailString;
    }

    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Settings getCategory() {
        return category;
    }

    public void setCategory(Settings category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Blob getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Blob thumbnail) {
        this.thumbnail = thumbnail;
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
    
    
}
