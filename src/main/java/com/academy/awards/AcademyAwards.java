package com.academy.awards;

import com.academy.awards.entitys.User;
import com.academy.awards.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ComponentScan( basePackages = { "com" } )
@SpringBootApplication
@Slf4j
public class AcademyAwards {

	/**
     * Mihai Stefan
	 * @param args˙
	 */
	public static void main(String[] args) {
		SpringApplication.run(AcademyAwards.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		log.info("Added only one user for now !");
		return args -> {
		   userService.saveUser(new User(null, "Mihai Stefan", "mihai", "11111"));
		//	userService.saveUser(new User(null, "jack Stefan", "jack", "11111"));
		//	userService.saveUser(new User(null, "Tom Stefan", "tom", "11111"));
	   };
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
