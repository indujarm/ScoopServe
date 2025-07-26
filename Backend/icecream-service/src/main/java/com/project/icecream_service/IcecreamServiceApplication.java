package com.project.icecream_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IcecreamServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(IcecreamServiceApplication.class, args);
		System.out.println("Icecream service started");
	}

}
