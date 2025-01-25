package com.Jagadeesh.StoringImage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class StoringImageApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoringImageApplication.class, args);
	}

}
