package dev.springauth.springauth.service;

import java.util.List;
import java.util.Optional;

import dev.springauth.springauth.model.AppUser;
import dev.springauth.springauth.model.ERole;
import dev.springauth.springauth.model.Role;

public interface AppUserService {
    AppUser saveAppUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToAppUser(String username, ERole role);
    Optional<AppUser> getAppUser(String username);
    List<AppUser> getAppUsers();
}
