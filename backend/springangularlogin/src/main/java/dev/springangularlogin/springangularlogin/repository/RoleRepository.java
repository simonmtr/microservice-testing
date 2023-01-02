package dev.springangularlogin.springangularlogin.repository;

import org.springframework.data.repository.ListCrudRepository;

import dev.springangularlogin.springangularlogin.domain.Role;

public interface RoleRepository extends ListCrudRepository<Role, Long> {
    Role findByName(String name);
}
