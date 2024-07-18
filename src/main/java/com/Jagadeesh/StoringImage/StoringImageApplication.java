package com.Jagadeesh.StoringImage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableTransactionManagement
//@EntityScan(basePackages = "com.Jagadeesh.StoringImage.entity")
public class StoringImageApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoringImageApplication.class, args);
	}

}
