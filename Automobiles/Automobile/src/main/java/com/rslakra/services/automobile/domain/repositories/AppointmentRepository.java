package com.rslakra.services.automobile.domain.repositories;

import java.util.List;

import com.rslakra.services.automobile.domain.entities.Appointment;
import com.rslakra.services.automobile.domain.entities.AutoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @since 09-16-2019 1:39:44 PM
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	/**
	 * Returns the appointments of the given user.
	 * 
	 * @param user
	 * @return
	 */
	public List<Appointment> findByUser(AutoUser user);

}
