package com.testevvvsis2.sistema2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.testevvvsis2.sistema2.config.AppConfig;

@SpringBootApplication
@Import(AppConfig.class)
public class Sistema2Application {

	public static void main(String[] args) {
		SpringApplication.run(Sistema2Application.class, args);
	}

}
