package dev.springangularlogin.springangularlogin.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
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

import dev.springangularlogin.springangularlogin.DTO.JwtDTO;
import dev.springangularlogin.springangularlogin.DTO.MessageDTO;
import dev.springangularlogin.springangularlogin.DTO.SigninDTO;
import dev.springangularlogin.springangularlogin.DTO.SignupDTO;
import dev.springangularlogin.springangularlogin.model.AppUser;
import dev.springangularlogin.springangularlogin.model.ERole;
import dev.springangularlogin.springangularlogin.model.Role;
import dev.springangularlogin.springangularlogin.repository.AppUserRepository;
import dev.springangularlogin.springangularlogin.repository.RoleRepository;
import dev.springangularlogin.springangularlogin.security.jwt.JwtUtils;
import dev.springangularlogin.springangularlogin.security.services.UserDetailsImpl;
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