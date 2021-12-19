package com.michaelmogessie.trainspotter.trainservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableDiscoveryClient
public class TrainserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainserviceApplication.class, args);
	}

}
