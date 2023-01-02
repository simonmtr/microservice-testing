package dev.springangularlogin.springangularlogin.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestAuthController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole(USER) or hasRole(ADMIN) or hasRole('SUPERADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/admin")
    @Secured("hasRole('ADMIN')")
    public String moderatorAccess() {
        return "Admin Board.";
    }

    @GetMapping("/superadmin")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public String adminAccess() {
        return "Superadmin Board.";
    }
}
