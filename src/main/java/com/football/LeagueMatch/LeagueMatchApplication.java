package com.football.LeagueMatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LeagueMatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeagueMatchApplication.class, args);
	}

}
