package dev.springangularlogin.springangularlogin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.springangularlogin.springangularlogin.model.AppUser;
import dev.springangularlogin.springangularlogin.model.ERole;
import dev.springangularlogin.springangularlogin.model.Role;
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
    public void addRoleToAppUser(String username, ERole eRole) {
        log.info("Adding role {} to user {}", eRole, username);
        Optional<AppUser> user = appUserRepository.findByUsername(username);
        Optional<Role> role = roleRepository.findByName(eRole);
        user.get().getRoles().add(role.get());
    }

    @Override
    public Optional<AppUser> getAppUser(String username) {
        log.info("Getting user {}", username);
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getAppUsers() {
        log.info("Getting all users");
        return appUserRepository.findAll();
    }
    
}
