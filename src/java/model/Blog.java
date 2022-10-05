package model;

import java.sql.Blob;
import java.sql.Date;
import model.Settings;

/**
 *
 * @author Xuan Son
 */
public class Blog {

    private int id;
    private Blob thumbnail;
    private String thumbnailString;
    private String title;
    private Account author;
    private Settings category;
    private int featured;
    private int status;
    private String content;
    private String briefInfo;
    private Date postDate;

    public Blog() {
    }

    public Blog(int id, String thumbnailString, String title, Account author, Settings category, int featured, int status, String content, String briefInfo, Date postDate) {
        this.id = id;
        this.thumbnailString = thumbnailString;
        this.title = title;
        this.author = author;
        this.category = category;
        this.featured = featured;
        this.status = status;
        this.content = content;
        this.briefInfo = briefInfo;
        this.postDate = postDate;
    }

    public String getThumbnailString() {
        return thumbnailString;
    }

    public void setThumbnailString(String thumbnailString) {
        this.thumbnailString = thumbnailString;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Blob getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Blob thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public Settings getCategory() {
        return category;
    }

    public void setCategory(Settings category) {
        this.category = category;
    }

    public int getFeatured() {
        return featured;
    }

    public void setFeatured(int featured) {
        this.featured = featured;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
