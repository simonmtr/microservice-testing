package dev.microservicetesting.authenticationservice.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import dev.microservicetesting.authenticationservice.model.ERole;
import dev.microservicetesting.authenticationservice.model.Role;

public interface RoleRepository extends ListCrudRepository<Role, Long> {
    Optional<Role> findByName(ERole string);
}
