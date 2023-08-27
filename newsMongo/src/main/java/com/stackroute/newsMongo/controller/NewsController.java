package com.stackroute.newsMongo.controller;


import com.stackroute.newsMongo.exception.NewsAlreadyExistsException;
import com.stackroute.newsMongo.exception.NewsNotFoundException;
import com.stackroute.newsMongo.model.News;
import com.stackroute.newsMongo.model.UserNews;
import com.stackroute.newsMongo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/news")
public class NewsController {
//    private final NewsService newsService;
//
//    @Autowired
//    public NewsController(NewsService newsService) {
//        this.newsService = newsService;
//    }
//
//    @PostMapping
//    public ResponseEntity<?> saveNews(@RequestBody News news) {
//        try {
//            News savedNews = newsService.saveNews(news);
//            return new ResponseEntity<>(savedNews, HttpStatus.CREATED);
//        } catch (NewsAlreadyExistsException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//        }
//    }
//
////
//@GetMapping("/{newsId}")
//public ResponseEntity<News> getNewsById(@PathVariable("newsId") String newsId) throws NewsNotFoundException {
//    Optional<News> newsOptional = newsService.getNewsById(newsId);
//    if (newsOptional.isPresent()) {
//        News news = newsOptional.get();
//        return new ResponseEntity<>(news, HttpStatus.OK);
//    } else {
//        throw new NewsNotFoundException("News not found with ID: " + newsId);
//    }
//}
//
//
//    @GetMapping
//    public ResponseEntity<List<News>> getAllNews() {
//        List<News> allNews = newsService.getAllNews();
//        return new ResponseEntity<>(allNews, HttpStatus.OK);
//    }
//
////    @PutMapping("/{newsId}")
////    public ResponseEntity<?> updateNews(@PathVariable String newsId, @RequestBody News news) {
////        try {
////            News updatedNews = newsService.updateNews(newsId, news);
////            if (updatedNews != null) {
////                return new ResponseEntity<>(updatedNews, HttpStatus.OK);
////            } else {
////                throw new NewsNotFoundException("News not found");
////            }
////        } catch (NewsNotFoundException e) {
////            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
////        }
////    }
//@PutMapping("/{newsId}")
//public ResponseEntity<News> updateNews(@PathVariable("newsId") String newsId, @RequestBody News news) throws NewsNotFoundException {
//    News updatedNews = newsService.updateNews(newsId, news);
//    if (updatedNews != null) {
//        return new ResponseEntity<>(updatedNews, HttpStatus.OK);
//    } else {
//        throw new NewsNotFoundException("News not found with ID: " + newsId);
//    }
//}
//
////    @DeleteMapping("/{newsId}")
////    public ResponseEntity<String> deleteNews(@PathVariable String newsId) {
////        boolean isDeleted = newsService.deleteNews(newsId);
////        if (isDeleted) {
////            return new ResponseEntity<>("News deleted successfully", HttpStatus.OK);
////        } else {
////            return new ResponseEntity<>("News not found", HttpStatus.NOT_FOUND);
////        }
////    }
//@DeleteMapping("/{newsId}")
//public ResponseEntity<String> deleteNews(@PathVariable("newsId") String newsId) throws NewsNotFoundException {
//    boolean isDeleted = newsService.deleteNews(newsId);
//    if (isDeleted) {
//        return new ResponseEntity<>("News deleted successfully", HttpStatus.OK);
//    } else {
//        throw new NewsNotFoundException("News not found with ID: " + newsId);
//    }
//}
private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<News> saveNewsForUser(@PathVariable String userId, @RequestBody News news) {
        News savedNews = newsService.saveNewsForUser(userId, news);
        return new ResponseEntity<>(savedNews, HttpStatus.CREATED);
    }

//    @PutMapping("/user/{userId}/{newsId}")
//    public ResponseEntity<News> updateNewsForUser(@PathVariable String userId, @PathVariable String newsId, @RequestBody News news) {
//        News updatedNews = newsService.updateNewsForUser(userId, newsId, news);
//        if (updatedNews != null) {
//            return new ResponseEntity<>(updatedNews, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
@PutMapping("/user/{userId}")
public ResponseEntity<News> updateNewsForUser(@PathVariable String userId, @RequestBody News news) throws NewsNotFoundException {
    News updatedNews = newsService.updateNewsForUser(userId, news.getNewsId(), news);
    if (updatedNews != null) {
        return new ResponseEntity<>(updatedNews, HttpStatus.OK);
    } else {
        throw new NewsNotFoundException("News not found for the user with ID: " + userId);
    }
}


    @DeleteMapping("/user/{userId}/{newsId}")
    public ResponseEntity<String> deleteNewsForUser(@PathVariable String userId, @PathVariable String newsId) {
        boolean isDeleted = newsService.deleteNewsForUser(userId, newsId);
        if (isDeleted) {
            return new ResponseEntity<>("News deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("News not found", HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<News> getNewsForUserById(@PathVariable String userId, @PathVariable String newsId) throws NewsNotFoundException {
//        News news = newsService.getNewsForUserById(userId, newsId);
//        if (news != null) {
//            return new ResponseEntity<>(news, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//    }
//
//    @GetMapping("/user")
//    public ResponseEntity<UserNews> getUserNewsById(@PathVariable String userId) {
//        UserNews userNews = newsService.getUserNewsById(userId);
//        if (userNews != null) {
//            return new ResponseEntity<>(userNews, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
@GetMapping("/user/{userId}")
public ResponseEntity<List<News>> getAllNewsForUser(@PathVariable String userId, @RequestParam(required = false) String newsId) throws NewsNotFoundException {
    UserNews userNews = newsService.getUserNewsById(userId);
    if (userNews != null) {
        if (newsId != null) {
            Optional<News> news = userNews.getNewsList().stream()
                    .filter(n -> n.getNewsId().equals(newsId))
                    .findFirst();
            if (news.isPresent()) {
                return new ResponseEntity<>(Collections.singletonList(news.get()), HttpStatus.OK);
            }
            throw new NewsNotFoundException("News not found with ID: " + newsId);
        } else {
            List<News> newsList = userNews.getNewsList();
            return new ResponseEntity<>(newsList, HttpStatus.OK);
        }
    }
    throw new NewsNotFoundException("No news found for the user with ID: " + userId);
}


}
