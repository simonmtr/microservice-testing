package dev.springangularlogin.springangularlogin.service;

import java.util.List;

import dev.springangularlogin.springangularlogin.domain.AppUser;
import dev.springangularlogin.springangularlogin.domain.Role;

public interface AppUserService {
    AppUser saveAppUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToAppUser(String username, String rolename);
    AppUser getAppUser(String username);
    List<AppUser> getAppUsers();
}
