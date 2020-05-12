package com.ssafy.moida;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:${db.config}"})
public class MoidaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoidaApplication.class, args);
	}

}
