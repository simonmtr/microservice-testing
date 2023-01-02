package dev.springauth.springauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.springauth.springauth.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
