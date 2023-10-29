package com.rslakra.services.automobile.domain.entities;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.spring.persistence.entity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @since 09-16-2019 1:38:56 PM
 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class AutoUser extends AbstractEntity<Long> implements UserDetails {

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "status")
    private String status;
    @Column(name = "role")
    private String role;

    @Transient
    private String rawPassword;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Appointment> appointments = new ArrayList<Appointment>();

    /**
     * @param role
     */
    public void addRole(String role) {
        setRole(role);
    }

    public void addAppointment(Appointment appointment) {
        if (BeanUtils.isNull(appointments)) {
            appointments = new ArrayList<>();
        }
        getAppointments().add(appointment);
    }

    /**
     * @return
     */
    @Override
    public String getUsername() {
        return getEmail();
    }

    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(this.role);
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
