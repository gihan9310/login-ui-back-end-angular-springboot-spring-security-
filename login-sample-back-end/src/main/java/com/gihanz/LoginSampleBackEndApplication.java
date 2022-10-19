package com.gihanz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class LoginSampleBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginSampleBackEndApplication.class, args);
	}

}
