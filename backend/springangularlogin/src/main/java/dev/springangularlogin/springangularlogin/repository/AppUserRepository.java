package dev.springangularlogin.springangularlogin.repository;

import org.springframework.data.repository.ListCrudRepository;

import dev.springangularlogin.springangularlogin.domain.AppUser;

public interface AppUserRepository extends ListCrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
