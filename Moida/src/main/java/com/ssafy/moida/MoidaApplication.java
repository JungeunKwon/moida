package com.ssafy.moida;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.filter.OrderedHttpPutFormContentFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.HttpPutFormContentFilter;

@EnableJpaAuditing
@SpringBootApplication
@PropertySource(value = {"classpath:${db.config}", "classpath:${aws.config}"})
public class MoidaApplication {

	
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MoidaApplication.class, args);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public HttpPutFormContentFilter httpPutFormFilter() {
		return new HttpPutFormContentFilter();
	}
	
	@Bean
	public OrderedHttpPutFormContentFilter httpputFormContentFilter() {
		return new OrderedHttpPutFormContentFilter();
	}
}
