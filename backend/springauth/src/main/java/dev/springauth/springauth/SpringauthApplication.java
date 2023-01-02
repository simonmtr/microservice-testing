package dev.springauth.springauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringauthApplication.class, args);
		System.out.println("Starting server...");
	}
}
