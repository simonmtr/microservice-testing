package dev.microservicetesting.usermanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usermanagement")
public class TestController {
    @GetMapping("/test")
	public ResponseEntity<String> testUsermanagement() {
        return ResponseEntity.ok().body("usermanagement requested");
    }
}
