package com.rslakra.jwtauthentication4;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rslakra.jwtauthentication4.domain.Brand;
import com.rslakra.jwtauthentication4.domain.Vehicle;
import com.rslakra.jwtauthentication4.repository.VehicleRepository;
import com.rslakra.jwtauthentication4.web.VehicleController;
import com.rslakra.jwtauthentication4.web.VehicleForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(controllers = VehicleController.class, secure = false)
@RunWith(SpringRunner.class)
public class VehicleControllerTest {

    @MockBean
    VehicleRepository vehicles;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() {
        given(this.vehicles.findById(1L))
            .willReturn(Optional.of(Vehicle.builder().name("test").build()));

        given(this.vehicles.findById(2L))
            .willReturn(Optional.empty());

        given(this.vehicles.save(any(Vehicle.class)))
            .willReturn(Vehicle.builder().name("test").build());

        given(this.vehicles.findByBrandIn(anyList()))
            .willReturn(
                Arrays.asList(
                    Vehicle.builder().name("test").brand(Brand.FORD).build(),
                    Vehicle.builder().name("toyota").brand(Brand.TOYOTA).build()
                )
            );

        doNothing().when(this.vehicles).delete(any(Vehicle.class));
    }

    @Test
    public void testFindByBrandIn() throws Exception {

        this.mockMvc
            .perform(
                get("/v1/vehicles?brand=ford,toyota")
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("test"));

        verify(this.vehicles, times(1)).findByBrandIn(anyList());
        verifyNoMoreInteractions(this.vehicles);
    }

    @Test
    public void testGetById() throws Exception {

        this.mockMvc
            .perform(
                get("/v1/vehicles/{id}", 1L)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("test"));

        verify(this.vehicles, times(1)).findById(any(Long.class));
        verifyNoMoreInteractions(this.vehicles);
    }

    @Test
    public void testGetByIdNotFound() throws Exception {

        this.mockMvc
            .perform(
                get("/v1/vehicles/{id}", 2L)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNotFound());

        verify(this.vehicles, times(1)).findById(any(Long.class));
        verifyNoMoreInteractions(this.vehicles);
    }

    @Test
    public void testSave() throws Exception {

        this.mockMvc
            .perform(
                post("/v1/vehicles")
                    .content(this.objectMapper.writeValueAsBytes(VehicleForm.builder().name("test").build()))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isCreated());

        verify(this.vehicles, times(1)).save(any(Vehicle.class));
        verifyNoMoreInteractions(this.vehicles);
    }

    @Test
    public void testUpdate() throws Exception {

        this.mockMvc
            .perform(
                put("/v1/vehicles/1")
                    .content(this.objectMapper.writeValueAsBytes(VehicleForm.builder().name("test").build()))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        verify(this.vehicles, times(1)).findById(any(Long.class));
        verify(this.vehicles, times(1)).save(any(Vehicle.class));
        verifyNoMoreInteractions(this.vehicles);
    }

    @Test
    public void testDelete() throws Exception {

        this.mockMvc
            .perform(
                delete("/v1/vehicles/1")
            )
            .andExpect(status().isNoContent());

        verify(this.vehicles, times(1)).findById(any(Long.class));
        verify(this.vehicles, times(1)).delete(any(Vehicle.class));
        verifyNoMoreInteractions(this.vehicles);
    }

}
