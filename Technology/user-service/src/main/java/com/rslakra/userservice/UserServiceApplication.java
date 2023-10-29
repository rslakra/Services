package com.rslakra.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <pre>
 *  1. Send request to create a clientId/clientSecret.
 *  2. Get accessToken to create the clientId/clientSecret with assertion = ADMIN.
 *  3. Register client with the received accessToken (Step #2)
 *  4. Register user for received clientId (Step #3) setting <code>sub = clientId</code>.
 *  5. User this user's accessToken to access further resources.
 * </pre>
 */
@SpringBootApplication
public class UserServiceApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
