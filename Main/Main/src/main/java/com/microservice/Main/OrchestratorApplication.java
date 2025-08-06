package com.microservice.Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrchestratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrchestratorApplication.class, args);
	}

}


// response to users with http codes.
//proper api designing.
//writing code inside custom exception
//proper logging in each step

//storing the requests in db(BLOB).
//UUID to be stored as primary key.

//scheduler
//petty cash

//Acid transaction

//caching
//DB indexing

//string value too long for displaying all the info
//Clob cannot display all the info.

//attributes naming
//declaring the datatype as clob


