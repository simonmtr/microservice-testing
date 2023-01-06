package dev.microservicetesting.usermanagement.service;

import java.util.Optional;

import dev.microservicetesting.usermanagement.model.UserProfile;

public interface UserProfileService {
    UserProfile saveUserProfile(UserProfile userProfile);

    Optional<UserProfile> getUserProfile(String username);
}
