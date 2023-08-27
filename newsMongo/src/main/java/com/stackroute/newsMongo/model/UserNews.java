package com.stackroute.newsMongo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "user_news")
public class UserNews {
    @Id
    private String userId;
    @DBRef
    @JsonIgnore
    private List<News> newsList;

    public UserNews() {
        this.newsList = new ArrayList<>();
    }

    public UserNews(String userId) {
        this.userId = userId;
        this.newsList = new ArrayList<>();
    }


    // Getters and setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
    @Override
    public String toString() {
        return "UserNews{" +
                "userId=" + userId +
                ", newsList=" + newsList +
                '}';
    }
}
