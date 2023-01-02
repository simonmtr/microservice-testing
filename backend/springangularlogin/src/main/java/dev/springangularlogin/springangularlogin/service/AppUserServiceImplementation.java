package dev.springangularlogin.springangularlogin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.springangularlogin.springangularlogin.domain.AppUser;
import dev.springangularlogin.springangularlogin.domain.Role;
import dev.springangularlogin.springangularlogin.repository.AppUserRepository;
import dev.springangularlogin.springangularlogin.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AppUserServiceImplementation implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;

    @Override
    public AppUser saveAppUser(AppUser user) {
        log.info("Saving new user {}", user.getUsername());
        return appUserRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {}", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToAppUser(String username, String rolename) {
        log.info("Adding role {} to user {}", rolename, username);
        AppUser user = appUserRepository.findByUsername(username);
        Role role = roleRepository.findByName(rolename);
        user.getRoles().add(role);
    }

    @Override
    public AppUser getAppUser(String username) {
        log.info("Getting user {}", username);
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getAppUsers() {
        log.info("Getting all users");
        return appUserRepository.findAll();
    }
    
}
