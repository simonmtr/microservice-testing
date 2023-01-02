package dev.springangularlogin.springangularlogin.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import dev.springangularlogin.springangularlogin.model.ERole;
import dev.springangularlogin.springangularlogin.model.Role;

public interface RoleRepository extends ListCrudRepository<Role, Long> {
    Optional<Role> findByName(ERole string);
}
