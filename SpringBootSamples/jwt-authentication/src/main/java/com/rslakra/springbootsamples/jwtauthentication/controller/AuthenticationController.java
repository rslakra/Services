package com.rslakra.springbootsamples.jwtauthentication.controller;

import com.rslakra.springbootsamples.jwtauthentication.model.IdentityDO;
import com.rslakra.springbootsamples.jwtauthentication.model.RoleDO;
import com.rslakra.springbootsamples.jwtauthentication.model.RoleType;
import com.rslakra.springbootsamples.jwtauthentication.payload.request.LoginRequest;
import com.rslakra.springbootsamples.jwtauthentication.payload.request.SignUpRequest;
import com.rslakra.springbootsamples.jwtauthentication.payload.response.JwtResponse;
import com.rslakra.springbootsamples.jwtauthentication.repository.IdentityRepository;
import com.rslakra.springbootsamples.jwtauthentication.repository.RoleRepository;
import com.rslakra.springbootsamples.jwtauthentication.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import java.util.Set;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("${apiPrefix}/auth")
//@Tag(name = "Authentication Service")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IdentityRepository identityRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtProvider jwtProvider;

    /**
     * @param loginRequest
     * @return
     */
    @PostMapping("/sign-in")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication
            authentication =
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.createJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    /**
     * @param signUpRequest
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (identityRepository.existsByUserName(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                                              HttpStatus.BAD_REQUEST);
        }

        if (identityRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("Fail -> Email is already in use!",
                                              HttpStatus.BAD_REQUEST);
        }

        // Creating identity's account
        IdentityDO identity = new IdentityDO(signUpRequest.getName(), signUpRequest.getUsername(),
                                             encoder.encode(signUpRequest.getPassword()), signUpRequest.getEmail());

        Set<String> strRoles = signUpRequest.getRole();
        Set<RoleDO> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    RoleDO adminRole = roleRepository.findByName(RoleType.ADMIN)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: IdentityDO RoleDO not find."));
                    roles.add(adminRole);

                    break;
                case "pm":
                    RoleDO pmRole = roleRepository.findByName(RoleType.MANAGER)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: IdentityDO RoleDO not find."));
                    roles.add(pmRole);

                    break;
                default:
                    RoleDO userRole = roleRepository.findByName(RoleType.USER)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: IdentityDO RoleDO not find."));
                    roles.add(userRole);
            }
        });

        identity.setRoles(roles);
        identityRepository.save(identity);

        return ResponseEntity.ok().body("IdentityDO registered successfully!");
    }
}
