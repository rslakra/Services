package com.rslakra.jwtauthentication4.web;

import com.rslakra.jwtauthentication4.domain.Brand;
import com.rslakra.jwtauthentication4.domain.Vehicle;
import com.rslakra.jwtauthentication4.repository.VehicleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/vehicles")
public class VehicleController {

    private VehicleRepository vehicles;

    public VehicleController(VehicleRepository vehicles) {
        this.vehicles = vehicles;
    }


    @GetMapping("")
    public ResponseEntity<List<Vehicle>> all(@RequestParam(name = "brand", required = false) String[] brands) {
        if (brands == null || brands.length == 0) {
            return ok(this.vehicles.findAll());
        } else {
            List<Brand> brandList = new ArrayList<>();
            for (String brand : brands) {
                brandList.add(Brand.valueOf(brand.toUpperCase()));
            }
            return ok(this.vehicles.findByBrandIn(brandList));
        }

    }

    @SuppressWarnings("rawtypes")
    @PostMapping("")
    public ResponseEntity save(@RequestBody VehicleForm form, HttpServletRequest request) {
        Vehicle saved = this.vehicles.save(Vehicle.builder().name(form.getName()).build());
        return created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/v1/vehicles/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> get(@PathVariable("id") Long id) {
        return ok(this.vehicles.findById(id).orElseThrow(() -> new VehicleNotFoundException()));
    }

    @SuppressWarnings("rawtypes")
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody VehicleForm form) {
        Vehicle existed = this.vehicles.findById(id).orElseThrow(() -> new VehicleNotFoundException());
        existed.setName(form.getName());

        this.vehicles.save(existed);
        return noContent().build();
    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Vehicle existed = this.vehicles.findById(id).orElseThrow(() -> new VehicleNotFoundException());
        this.vehicles.delete(existed);
        return noContent().build();
    }
}
