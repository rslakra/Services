package com.rslakra.thymeleaffragments.controller;

import static com.rslakra.thymeleaffragments.controller.CityController.FRAGMENT_FORM;
import static com.rslakra.thymeleaffragments.controller.CityController.MODEL_ATTRIBUTE_CITIES;
import static com.rslakra.thymeleaffragments.controller.CityController.MODEL_ATTRIBUTE_CITY;
import static com.rslakra.thymeleaffragments.controller.CityController.SECTION_CITIES;
import static com.rslakra.thymeleaffragments.controller.CityController.VIEW_CITIES;
import static com.rslakra.thymeleaffragments.controller.CityController.VIEW_CITY_DELETE;
import static com.rslakra.thymeleaffragments.controller.CityController.VIEW_CITY_FORM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.thymeleaffragments.persistence.entity.City;
import com.rslakra.thymeleaffragments.persistence.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CityControllerTest {

    private static final String CITY_ID = "sim";
    @Mock
    private CityRepository cityRepository;
    @InjectMocks
    private CityController controller;

    private ModelMap modelMap;
    private City city;

    @BeforeEach
    void setup() {
        modelMap = new ModelMap();
        city = new City(CITY_ID, "Sim City", 2016, 123_456);
    }

    @Test
    void should_show_overview() {
        List<City> cityList = new ArrayList<>();
        when(cityRepository.getAll()).thenReturn(cityList);

        String view = controller.overview(modelMap);

        assertThat(view).isEqualTo(VIEW_CITIES);
        assertThat(modelMap).containsEntry(MODEL_ATTRIBUTE_CITIES, cityList);
    }

    @Test
    void should_show_city_form_page() {
        mockFindCity();

        String view = controller.showUpdateCityPage(CITY_ID, modelMap);

        assertThat(view).isEqualTo(VIEW_CITY_FORM);
        assertThat(modelMap).containsEntry(MODEL_ATTRIBUTE_CITY, city);
    }

    @Test
    void should_show_city_form_fragment() {
        mockFindCity();

        String view = controller.showUpdateCityForm(CITY_ID, modelMap);

        assertThat(view).isEqualTo(VIEW_CITY_FORM + FRAGMENT_FORM);
        assertThat(modelMap).containsEntry(MODEL_ATTRIBUTE_CITY, city);
    }

    @Test
    void should_update_city() {
        RedirectView view = controller.updateCity(CITY_ID, city);

        assertThat(view.isRedirectView()).isTrue();
        assertThat(view.getUrl()).isEmpty();

        verify(cityRepository).update(city);
    }

    @Test
    void should_update_city_name() {
        mockFindCity();

        controller.partialUpdateCity(CITY_ID, "name", "Almere");

        assertThat(city.getName()).isEqualTo("Almere");

        verify(cityRepository).update(city);
    }

    @Test
    void should_update_city_population() {
        mockFindCity();

        controller.partialUpdateCity(CITY_ID, "population", "987654");

        assertThat(city.getPopulation()).isEqualTo(987654);

        verify(cityRepository).update(city);
    }

    @Test
    void should_update_city_founded_in() {
        mockFindCity();

        controller.partialUpdateCity(CITY_ID, "foundedIn", "2000");

        assertThat(city.getFoundedIn()).isEqualTo(2000);

        verify(cityRepository).update(city);
    }

    @Test
    void should_not_update_city_if_parameter_is_unknown() {
        mockFindCity();

        controller.partialUpdateCity(CITY_ID, "unsupported", "My value");

        verify(cityRepository, never()).update(city);
    }

    @Test
    void should_show_delete_city_page() {
        mockFindCity();

        String view = controller.showDeleteCityPage(CITY_ID, modelMap);

        assertThat(view).isEqualTo(VIEW_CITY_DELETE);
        assertThat(modelMap).containsEntry(MODEL_ATTRIBUTE_CITY, city);
    }

    @Test
    void should_show_delete_city_form_fragment() {
        mockFindCity();

        String view = controller.showDeleteCityForm(CITY_ID, modelMap);

        assertThat(view).isEqualTo(VIEW_CITY_DELETE + FRAGMENT_FORM);
        assertThat(modelMap).containsEntry(MODEL_ATTRIBUTE_CITY, city);
    }

    @Test
    void should_delete_city() {
        RedirectView view = controller.deleteCity(CITY_ID);

        assertThat(view.isRedirectView()).isTrue();
        assertThat(view.getUrl()).isEqualTo("/cities");

        verify(cityRepository).remove(CITY_ID);
    }

    @Test
    void should_set_section_to_modelMap() {
        String section = controller.section();

        assertThat(section).isEqualTo(SECTION_CITIES);
    }

    private void mockFindCity() {
        when(cityRepository.find(CITY_ID)).thenReturn(Optional.of(city));
    }
}