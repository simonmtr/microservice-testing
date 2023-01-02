package dev.springangularlogin.springangularlogin.service;

import java.util.List;
import java.util.Optional;

import dev.springangularlogin.springangularlogin.model.AppUser;
import dev.springangularlogin.springangularlogin.model.ERole;
import dev.springangularlogin.springangularlogin.model.Role;

public interface AppUserService {
    AppUser saveAppUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToAppUser(String username, ERole role);
    Optional<AppUser> getAppUser(String username);
    List<AppUser> getAppUsers();
}
