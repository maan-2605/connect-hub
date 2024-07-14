package com.example.connect_hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ConnectHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectHubApplication.class, args);
	}

}
