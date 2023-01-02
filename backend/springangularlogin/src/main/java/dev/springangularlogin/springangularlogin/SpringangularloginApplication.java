package dev.springangularlogin.springangularlogin;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.springangularlogin.springangularlogin.domain.AppUser;
import dev.springangularlogin.springangularlogin.domain.Role;
import dev.springangularlogin.springangularlogin.service.AppUserService;

@SpringBootApplication
public class SpringangularloginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringangularloginApplication.class, args);
		System.out.println("Starting server...");
	}

	@Bean
	CommandLineRunner run(AppUserService appUserService) {
		return args -> {
			appUserService.saveRole(new Role(null, "ROLE_USER"));
			appUserService.saveRole(new Role(null, "ROLE_ADMIN"));
			appUserService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			appUserService.saveAppUser(new AppUser(null, "user", "user", new ArrayList<>()));
			appUserService.saveAppUser(new AppUser(null, "admin", "admin", new ArrayList<>()));
			appUserService.saveAppUser(new AppUser(null, "superadmin", "superadmin", new ArrayList<>()));
		
			appUserService.addRoleToAppUser("user", "ROLE_USER");
			appUserService.addRoleToAppUser("admin", "ROLE_ADMIN");
			appUserService.addRoleToAppUser("superadmin", "ROLE_SUPER_ADMIN");

			appUserService.addRoleToAppUser("user", "ROLE_ADMIN");
		};
	}

}
