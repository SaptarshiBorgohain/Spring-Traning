package com.stackroute.newsMongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//@EnableMongoRepositories
@SpringBootApplication
public class NewsMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsMongoApplication.class, args);
	}

}
