package com.rslakra.jwtauthentication;

import com.rslakra.jwtauthentication.domain.User;
import com.rslakra.jwtauthentication.domain.Vehicle;
import com.rslakra.jwtauthentication.repository.UserRepository;
import com.rslakra.jwtauthentication.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    VehicleRepository vehicles;

    @Autowired
    UserRepository users;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        logger.debug("initializing vehicles data...");
        Arrays.asList("moto", "car").forEach(v -> this.vehicles.saveAndFlush(Vehicle.builder().name(v).build()));

        logger.debug("printing all vehicles...");
        this.vehicles.findAll().forEach(v -> logger.debug(" Vehicle :" + v.toString()));

        this.users.save(User.builder()
                            .username("user")
                            .password(this.passwordEncoder.encode("password"))
                            .roles(Arrays.asList("ROLE_USER"))
                            .build()
        );

        this.users.save(User.builder()
                            .username("admin")
                            .password(this.passwordEncoder.encode("password"))
                            .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                            .build()
        );

        logger.debug("printing all users...");
        this.users.findAll().forEach(v -> logger.debug(" User :" + v.toString()));
    }
}
