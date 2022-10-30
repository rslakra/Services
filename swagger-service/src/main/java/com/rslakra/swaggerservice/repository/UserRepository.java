package com.rslakra.swaggerservice.repository;

import com.rslakra.swaggerservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 8/20/21 7:21 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * @param email
     * @return
     */
    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User getByEmail(@Param("email") String email);


    /**
     * Returns the list of users by userName.
     *
     * @param userName
     * @return
     */
    List<User> getByUserName(String userName);

    /**
     * Returns the list of users by firstName.
     *
     * @param firstName
     * @return
     */
    List<User> getByFirstName(String firstName);

    /**
     * Returns the list of users by lastName.
     *
     * @param lastName
     * @return
     */
    List<User> getByLastName(String lastName);

//    /**
//     * Returns the list of users by email.
//     *
//     * @param email
//     * @return
//     */
//    List<User> getByEmail(String email);

}
