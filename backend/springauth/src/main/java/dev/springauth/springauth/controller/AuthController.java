package dev.springauth.springauth.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.springauth.springauth.DTO.JwtDTO;
import dev.springauth.springauth.DTO.MessageDTO;
import dev.springauth.springauth.DTO.SigninDTO;
import dev.springauth.springauth.DTO.SignupDTO;
import dev.springauth.springauth.model.AppUser;
import dev.springauth.springauth.model.ERole;
import dev.springauth.springauth.model.Role;
import dev.springauth.springauth.repository.AppUserRepository;
import dev.springauth.springauth.repository.RoleRepository;
import dev.springauth.springauth.security.jwt.JwtUtils;
import dev.springauth.springauth.security.services.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AppUserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody SigninDTO signinDTO) {
        log.info(signinDTO.toString());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinDTO.getUsername(), signinDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtDTO(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@GetMapping("/admin")
	public ResponseEntity<?> registerUser(){
		return ResponseEntity.ok().body("works");
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupDTO signupDTO) {
		if (userRepository.existsByUsername(signupDTO.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageDTO("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signupDTO.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageDTO("Error: Email is already in use!"));
		}
		AppUser user = new AppUser(
                            signupDTO.getUsername(), 
							 signupDTO.getEmail(),
							 encoder.encode(signupDTO.getPassword()));

		Set<String> strRoles = signupDTO.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "superadmin":
					Role superadminRole = roleRepository.findByName(ERole.SUPER_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(superadminRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageDTO("User registered successfully!"));
	}
}