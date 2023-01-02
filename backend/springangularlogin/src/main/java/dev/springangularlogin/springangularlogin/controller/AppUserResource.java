package dev.springangularlogin.springangularlogin.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.springangularlogin.springangularlogin.DTO.RoleToAppUserDTO;
import dev.springangularlogin.springangularlogin.domain.AppUser;
import dev.springangularlogin.springangularlogin.domain.Role;
import dev.springangularlogin.springangularlogin.service.AppUserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AppUserResource {
    
    private final AppUserService appUserService;


    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAppUsers() {
        return ResponseEntity.ok().body(appUserService.getAppUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<AppUser> saveAppUsers(@RequestBody AppUser user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/login/user/save").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveAppUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/login/role/save").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToAppUserDTO roleToAppUserDTO) {
        appUserService.addRoleToAppUser(roleToAppUserDTO.getUsername(), roleToAppUserDTO.getRoleName());
        return ResponseEntity.ok().build();
    }
}
