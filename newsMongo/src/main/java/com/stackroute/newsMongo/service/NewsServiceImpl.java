package com.stackroute.newsMongo.service;

import com.stackroute.newsMongo.exception.NewsAlreadyExistsException;
import com.stackroute.newsMongo.exception.NewsNotFoundException;
import com.stackroute.newsMongo.model.News;
import com.stackroute.newsMongo.model.UserNews;
import com.stackroute.newsMongo.repository.NewsRepository;
import com.stackroute.newsMongo.repository.UserNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService{
//    private final NewsRepository newsRepository;
//
//    @Autowired
//    public NewsServiceImpl(NewsRepository newsRepository) {
//        this.newsRepository = newsRepository;
//    }
//
//    @Override
//    public News saveNews(News news) throws NewsAlreadyExistsException {
//        String newsId = String.valueOf(news.getNewsId());
//        Optional<News> existingNews = newsRepository.findById(newsId);
//        if (existingNews.isPresent()) {
//            throw new NewsAlreadyExistsException("News with ID " + newsId + " already exists");
//        }
//        return newsRepository.save(news);
//    }
//
//
//
//
//
//
//
//
//    @Override
//    public Optional<News> getNewsById(String newsId) throws NewsNotFoundException {
//        return newsRepository.findById(String.valueOf(newsId));
//    }
//
//
//    @Override
//    public List<News> getAllNews() {
//        return newsRepository.findAll();
//    }
//
//    @Override
//    public News updateNews(String newsId, News news) throws NewsNotFoundException  {
//        Optional<News> existingNewsOptional = getNewsById(newsId);
//        if (existingNewsOptional.isPresent()) {
//            News existingNews = existingNewsOptional.get();
//            existingNews.setTitle(news.getTitle());
//            existingNews.setAuthor(news.getAuthor());
//            existingNews.setDescription(news.getDescription());
//            existingNews.setContent(news.getContent());
//            existingNews.setUrl(news.getUrl());
//            existingNews.setUrlToImage(news.getUrlToImage());
//            return newsRepository.save(existingNews);
//        }
//        return null;
//    }
//
//    @Override
//    public boolean deleteNews(String newsId) {
//        if (newsRepository.existsById(String.valueOf(newsId))) {
//            newsRepository.deleteById(String.valueOf(newsId));
//            return true;
//        }
//        return false;
//    }
private final NewsRepository newsRepository;
    private final UserNewsRepository userNewsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository, UserNewsRepository userNewsRepository) {
        this.newsRepository = newsRepository;
        this.userNewsRepository = userNewsRepository;
    }

    @Override
    public News saveNewsForUser(String userId, News news) {
        UserNews userNews = userNewsRepository.findById(userId).orElse(new UserNews(userId));
        news.setUserNews(userNews);
        News savedNews = newsRepository.save(news);
        userNews.getNewsList().add(savedNews);
        userNewsRepository.save(userNews);
        return savedNews;
    }

    @Override
    public News updateNewsForUser(String userId, String newsId, News news) {
        News existingNews = newsRepository.findById(newsId).orElse(null);
        if (existingNews != null && existingNews.getUserNews().getUserId().equals(userId)) {
            news.setNewsId(existingNews.getNewsId());
            news.setUserNews(existingNews.getUserNews());
            return newsRepository.save(news);
        }
        return null;
    }

    @Override
    public boolean deleteNewsForUser(String userId, String newsId) {
        News existingNews = newsRepository.findById(newsId).orElse(null);
        if (existingNews != null && existingNews.getUserNews().getUserId().equals(userId)) {
            newsRepository.delete(existingNews);
            UserNews userNews = userNewsRepository.findById(userId).orElse(null);
            if (userNews != null) {
                userNews.getNewsList().removeIf(news -> news.getNewsId().equals(newsId));
                userNewsRepository.save(userNews);
            }
            return true;
        }
        return false;
    }

    @Override
    public News getNewsForUserById(String userId, String newsId) {
        News news = newsRepository.findById(newsId).orElse(null);
        if (news != null && news.getUserNews().getUserId().equals(userId)) {
            return news;
        }
        return null;
    }

    @Override
    public UserNews getUserNewsById(String userId) {
        return userNewsRepository.findById(userId).orElse(null);
    }
}
