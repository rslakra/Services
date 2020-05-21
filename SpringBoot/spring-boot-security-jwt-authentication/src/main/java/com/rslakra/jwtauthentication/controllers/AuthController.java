package com.rslakra.jwtauthentication.controllers;

import com.rslakra.jwtauthentication.models.Role;
import com.rslakra.jwtauthentication.models.Roles;
import com.rslakra.jwtauthentication.models.User;
import com.rslakra.jwtauthentication.payload.request.LoginRequest;
import com.rslakra.jwtauthentication.payload.request.RegistrationRequest;
import com.rslakra.jwtauthentication.payload.response.JwtResponse;
import com.rslakra.jwtauthentication.payload.response.MessageResponse;
import com.rslakra.jwtauthentication.repository.RoleRepository;
import com.rslakra.jwtauthentication.repository.UserRepository;
import com.rslakra.jwtauthentication.security.jwt.JwtUtils;
import com.rslakra.jwtauthentication.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public AuthController() {

    }

    @PostMapping("/signup")
    public ResponseEntity<?> doRegistration(@Valid @RequestBody RegistrationRequest request) {
        logger.debug("+doRegistration({})", request);
        ResponseEntity response;

        if (userRepository.existsByUserName(request.getUserName())) {
            response = ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
            logger.debug("-doRegistration({})", response);
            return response;
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            response = ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
            logger.debug("-doRegistration({})", response);
            return response;
        }

        // Create new user's account
        User user = new User(request.getUserName(), passwordEncoder.encode(request.getPassword()), request.getEmail());

        Set<String> rolesSet = request.getRoles();
        Set<Role> roles = new HashSet<>();
        if (rolesSet == null) {
            Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            rolesSet.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(Roles.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "moderator":
                        Role modRole = roleRepository.findByName(Roles.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        response = ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        logger.debug("-doRegistration({})", response);
        return response;
    }


    @PostMapping("/signin")
    public ResponseEntity<?> doAuthentication(@Valid @RequestBody LoginRequest loginRequest) {
        logger.debug("+doAuthentication({})", loginRequest);
        ResponseEntity response;
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String>
            roles =
            userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        response =
            ResponseEntity
                .ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
                                    roles));
        logger.debug("-doAuthentication(), response: {}", response);
        return response;
    }
}
