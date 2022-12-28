package com.rslakra.jwtauthentication.repository;

import com.rslakra.jwtauthentication.domain.Brand;
import com.rslakra.jwtauthentication.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "vehicles", collectionResourceRel = "vehicles", itemResourceRel = "vehicle")
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByBrandIn(List<Brand> brandList);
}
