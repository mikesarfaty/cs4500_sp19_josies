package com.example.cs4500_sp19_josies;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import com.example.cs4500_sp19_josies.models.Service;
import com.example.cs4500_sp19_josies.models.ServiceCategory;
import com.example.cs4500_sp19_josies.repositories.ServiceCategoryRepository;
import com.example.cs4500_sp19_josies.services.ServiceCategoryService;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ServiceCategoryService.class)
public class ServiceCategoryTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ServiceCategoryService service;
    @Mock
    private ServiceCategoryRepository repository;

    ServiceCategory mockServiceCategory;
    Service mockService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ServiceCategoryService())
                .build();
    }

    // Re-initialize the Category every time, since we may mutate or delete it.
    @BeforeEach
    public void setUpCategory() {
        mockServiceCategory = new ServiceCategory();
        mockServiceCategory.setId(1);
        mockServiceCategory.setTitle("Housing");
        mockServiceCategory.setPopularity(1);
        mockServiceCategory.setIcon("icon1");
        List<Service> services0 = new ArrayList<>();
        mockServiceCategory.setServices(services0);
    }

    @Test
    public void testCreateServiceCategory() throws Exception {
        List<ServiceCategory> serviceCategories = new ArrayList<>();

        Assertions.assertEquals(0, serviceCategories.size());

        ObjectMapper mapper = new ObjectMapper();
        String stringified = mapper.writeValueAsString(mockServiceCategory);

        Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
            serviceCategories.add(mockServiceCategory);
            return null;
        }).when(service).createServiceCategory(any(ServiceCategory.class));

        this.mockMvc
                .perform(MockMvcRequestBuilders
                    .post("/api/categories")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(stringified)
                    .characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Assertions.assertEquals(mockServiceCategory, serviceCategories.get(0));
    }

    @Test
    public void testUpdateServiceCategory() throws Exception {
        List<ServiceCategory> serviceCategories = new ArrayList<>();
        serviceCategories.add(mockServiceCategory);
        List<Service> services = new ArrayList<>();
        services.add(mockService);

        ServiceCategory newServiceCategory = new ServiceCategory();
        newServiceCategory.setId(1);
        newServiceCategory.setTitle("Plumbing");
        newServiceCategory.setIcon("Icon2");
        newServiceCategory.setPopularity(2);
        newServiceCategory.setServices(services);

        Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
            serviceCategories.get(0).setTitle(newServiceCategory.getTitle());
            serviceCategories.get(0).setPopularity(newServiceCategory.getPopularity());
            return null;
        }).when(service).updateServiceCategory(any(Integer.class), any(ServiceCategory.class));

        ObjectMapper mapper = new ObjectMapper();
        String stringified = mapper.writeValueAsString(newServiceCategory);

        //update service category
        this.mockMvc
                .perform(MockMvcRequestBuilders
                    .put("/api/categories/1")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(stringified)
                    .characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Assertions.assertEquals(1, serviceCategories.get(0).getId().intValue());
        Assertions.assertEquals("icon1", serviceCategories.get(0).getIcon());
        Assertions.assertEquals(2, serviceCategories.get(0).getPopularity().intValue());
        Assertions.assertEquals("Plumbing", serviceCategories.get(0).getTitle());
        Assertions.assertEquals(new ArrayList<>(), serviceCategories.get(0).getServices());

    }

    @Test
    public void testDeleteServiceCategory() throws Exception {
        List<ServiceCategory> serviceCategories = new ArrayList<>();
        serviceCategories.add(mockServiceCategory);
        List<Service> services = new ArrayList<>();
        services.add(mockService);

        Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
            serviceCategories.remove(mockServiceCategory);
            return null;
        }).when(service).deleteServiceCategory(1);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                    .delete("/api/categories/1")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Assertions.assertEquals(serviceCategories, new ArrayList<ServiceCategory>());


    }



}
