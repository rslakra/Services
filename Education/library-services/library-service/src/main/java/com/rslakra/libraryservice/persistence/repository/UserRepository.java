package com.rslakra.libraryservice.persistence.repository;

import com.rslakra.libraryservice.persistence.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 8/20/21 7:21 PM
 */
@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    /**
     * Returns the list of users by userName.
     *
     * @param userName
     * @return
     */
    Optional<User> getByUserName(String userName);

    /**
     * @param email
     * @return
     */
    @Query("SELECT u FROM User u WHERE u.email = :email")
    public Optional<User> getByEmail(@Param("email") String email);

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

    /**
     * @param status
     * @return
     */
//    @Query("SELECT u FROM User u WHERE u.status = :status")
    public List<User> getByStatus(@Param("status") String status);

    /**
     * Returns the list of users by email.
     *
     * @param emailList
     * @return
     */
    @Query("SELECT u FROM User u WHERE u.email IN (:emailList)")
    public List<User> getByEmails(@Param("emailList") List<String> emailList);

}
