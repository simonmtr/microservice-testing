package dev.microservicetesting.authenticationservice.service;

import java.util.List;
import java.util.Optional;

import dev.microservicetesting.authenticationservice.model.AppUser;
import dev.microservicetesting.authenticationservice.model.ERole;
import dev.microservicetesting.authenticationservice.model.Role;

public interface AppUserService {
    AppUser saveAppUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToAppUser(String username, ERole role);
    Optional<AppUser> getAppUser(String username);
    List<AppUser> getAppUsers();
}
