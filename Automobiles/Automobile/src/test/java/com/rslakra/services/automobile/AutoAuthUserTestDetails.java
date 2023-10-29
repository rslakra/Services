package com.rslakra.services.automobile;

import com.rslakra.services.automobile.domain.entities.Appointment;
import com.rslakra.services.automobile.domain.entities.AutoUser;
import com.rslakra.services.automobile.domain.entities.Vehicle;
import com.rslakra.services.automobile.domain.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations = "classpath:application-context.xml")
public class AutoAuthUserTestDetails {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoAuthUserTestDetails.class);

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     *
     */
    @Test
    public void insertUser() {
        LOGGER.debug("+insertUser()");
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("Ford");
        vehicle.setModel("F150");
        vehicle.setYear(new Short("2015"));

        AutoUser autoUser = new AutoUser();
        Optional<AutoUser> autoUserOptional = userRepository.findFirstByOrderByIdDesc();
        if (autoUserOptional.isPresent()) {
            autoUser.setId(autoUserOptional.get().getId() + 1);
        } else {
            autoUser.setId(1L);
        }
        autoUser.setEmail("rslakra@gmail.com");
        String password = passwordEncoder.encode("test");
        LOGGER.debug("password (test) encoded: {}", password);
        autoUser.setPassword(password);
        autoUser.setFirstName("Rohtash");
        autoUser.setLastName("Lakra");
        autoUser.setStatus("Test");

        Appointment appointment = new Appointment();
        appointment.setId(autoUser.getId());
        appointment.setAppointmentOn(LocalDate.now());
        appointment.setUser(autoUser);
        appointment.setVehicle(vehicle);
        appointment.setServices(Arrays.asList("Tire Change", "Oil Change"));
        autoUser.getAppointments().add(appointment);

        autoUser = userRepository.save(autoUser);
        LOGGER.debug("-insertUser(), autoUser: {}", autoUser);
    }

}
