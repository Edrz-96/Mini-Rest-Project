package com.esg.main.config;

import java.time.LocalTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public String startTime() {
		return "Project started at: " + LocalTime.now();

	}

}
