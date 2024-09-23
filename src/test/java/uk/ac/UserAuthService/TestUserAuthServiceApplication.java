package uk.ac.UserAuthService;

import org.springframework.boot.SpringApplication;

public class TestUserAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(UserAuthServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
