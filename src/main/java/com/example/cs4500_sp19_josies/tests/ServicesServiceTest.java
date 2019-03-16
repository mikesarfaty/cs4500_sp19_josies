package com.example.cs4500_sp19_josies.tests;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.cs4500_sp19_josies.models.Service;
import com.example.cs4500_sp19_josies.repositories.ServiceRepository;
import com.example.cs4500_sp19_josies.services.ServiceService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(ServiceService.class)
public class ServicesServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ServiceService service;
    @Mock
    private ServiceRepository repository;

    private Service mockService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ServiceService())
                .build();
    }

    @BeforeEach
    public void setUpService() {
        mockService = new Service();
        mockService.setId(1);
        mockService.setTitle("Housekeeping");
    }

    @Test
    public void testCreateService() throws Exception {
        List<Service> services = new ArrayList<>();

        Assertions.assertEquals(0, services.size());

        ObjectMapper mapper = new ObjectMapper();
        String stringified = mapper.writeValueAsString(mockService);

        Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
            services.add(mockService);
            return null;
        }).when(service).createService(any(Service.class));

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/services")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(stringified)
                        .characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Assertions.assertEquals(mockService, services.get(0));
    }

    @Test
    public void testFindServiceByID() throws Exception {
        Mockito.when(service.findServiceById(1)).thenReturn(mockService);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/services/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("Housekeeping"));

    }

    @Test
    public void testUpdateService() throws Exception {
        List<Service> services = new ArrayList<>();
        services.add(mockService);

        Service newService = new Service();
        newService.setId(1);
        newService.setTitle("Dog Walking");

        Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
            services.get(0).setId(newService.getId());
            services.get(0).setTitle(newService.getTitle());
            return null;
        }).when(service).updateService(any(Integer.class), any(Service.class));

        ObjectMapper mapper = new ObjectMapper();
        String stringified = mapper.writeValueAsString(newService);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/services/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(stringified)
                        .characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Assertions.assertEquals(1, services.get(0).getId().intValue());
        Assertions.assertEquals("Dog Walking", services.get(0).getTitle());

    }

    @Test
    public void testDeleteService() throws Exception {
        List<Service> services = new ArrayList<>();
        services.add(mockService);

        Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
            services.remove(mockService);
            return null;
        }).when(service).deleteService(1);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/services/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Assertions.assertEquals(services, new ArrayList<Service>());
    }
}
