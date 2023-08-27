package com.stackroute.newsMongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;

@Document(collection = "news")
public class News {
    @Id
    private String newsId;
    private String title;
    private String author;
    private String description;
    private Date publishedAt;
    private String content;
    private String url;
    private byte[] urlToImage;

    public UserNews getUserNews() {
        return userNews;
    }

    public void setUserNews(UserNews userNews) {
        this.userNews = userNews;
    }

    @DBRef
    private UserNews userNews;



    public News() { this.publishedAt = new Date();
    }

    public News(String newsId, String title, String author, String description, String content, String url, byte[] urlToImage, UserNews userNews) {
        this.newsId = newsId;
        this.title = title;
        this.author = author;
        this.description = description;
        this.publishedAt = new Date();
        this.content = content;
        this.url = url;
        this.urlToImage = urlToImage;
        this.userNews = userNews;
    }

    // Getters and setters

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(byte[] urlToImage) {
        this.urlToImage = urlToImage;
    }


    @Override
    public String toString() {
        return "News{" +
                "newsId='" + newsId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", publishedAt=" + publishedAt +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" +urlToImage + '\'' +
                '}';
    }
}
