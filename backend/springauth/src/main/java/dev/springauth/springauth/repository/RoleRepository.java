package dev.springauth.springauth.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import dev.springauth.springauth.model.ERole;
import dev.springauth.springauth.model.Role;

public interface RoleRepository extends ListCrudRepository<Role, Long> {
    Optional<Role> findByName(ERole string);
}
