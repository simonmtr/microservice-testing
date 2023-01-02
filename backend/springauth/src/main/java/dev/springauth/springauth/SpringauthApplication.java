package dev.springauth.springauth;

import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.springauth.springauth.model.AppUser;
import dev.springauth.springauth.model.ERole;
import dev.springauth.springauth.model.Role;
import dev.springauth.springauth.service.AppUserService;

@SpringBootApplication
public class SpringauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringauthApplication.class, args);
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
