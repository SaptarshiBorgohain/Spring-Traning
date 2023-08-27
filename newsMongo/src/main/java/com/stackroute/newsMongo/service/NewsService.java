package com.stackroute.newsMongo.service;

import com.stackroute.newsMongo.exception.NewsAlreadyExistsException;
import com.stackroute.newsMongo.exception.NewsNotFoundException;
import com.stackroute.newsMongo.model.News;
import com.stackroute.newsMongo.model.UserNews;

import java.util.List;
import java.util.Optional;

public interface NewsService {
//    News saveNews(News news) throws NewsAlreadyExistsException;
//
//
//
//
//
////    News updateNews(News news);
//
//    News updateNews(String newsId, News news) throws NewsNotFoundException;
//
//    boolean deleteNews(String newsId) ;
//
//    Optional<News> getNewsById(String newsId) throws NewsNotFoundException;
//
//    List<News> getAllNews() ;
    News saveNewsForUser(String userId, News news);
    News updateNewsForUser(String userId, String newsId, News news);
    boolean deleteNewsForUser(String userId, String newsId);
    News getNewsForUserById(String userId, String newsId);
    UserNews getUserNewsById(String userId);

}
