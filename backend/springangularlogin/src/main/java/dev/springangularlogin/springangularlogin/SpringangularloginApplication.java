package dev.springangularlogin.springangularlogin;

import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.springangularlogin.springangularlogin.model.AppUser;
import dev.springangularlogin.springangularlogin.model.ERole;
import dev.springangularlogin.springangularlogin.model.Role;
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
			
			appUserService.saveRole(new Role(null, ERole.USER));
			appUserService.saveRole(new Role(null, ERole.ADMIN));
			appUserService.saveRole(new Role(null, ERole.SUPER_ADMIN));

			appUserService.saveAppUser(new AppUser(null, "user", "user", "user@mail.com", new HashSet<>()));
			appUserService.saveAppUser(new AppUser(null, "mismic", "mismic", "mismic@mail.com", new HashSet<>()));
			appUserService.saveAppUser(new AppUser(null, "admin", "admin", "admin@mail.com", new HashSet<>()));
			appUserService.saveAppUser(new AppUser(null, "superadmin", "superadmin", "superadmin@mail.com", new HashSet<>()));
		
			appUserService.addRoleToAppUser("user", ERole.USER);
			appUserService.addRoleToAppUser("admin", ERole.ADMIN);
			appUserService.addRoleToAppUser("superadmin", ERole.SUPER_ADMIN);

			appUserService.addRoleToAppUser("mismic", ERole.USER);
			appUserService.addRoleToAppUser("mismic", ERole.ADMIN);
			appUserService.addRoleToAppUser("mismic", ERole.SUPER_ADMIN);

		};
	}

}
