package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CathayApplication {

	private static Logger log = LogManager.getLogger(CathayApplication.class);
	
	public static void main(String[] args) {
		log.info("programer start");
		SpringApplication.run(CathayApplication.class, args);
	}

}
