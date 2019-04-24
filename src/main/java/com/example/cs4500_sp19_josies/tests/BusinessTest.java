package com.example.cs4500_sp19_josies.tests;


import com.example.cs4500_sp19_josies.models.Business;
import com.example.cs4500_sp19_josies.repositories.BusinessRepository;
import com.example.cs4500_sp19_josies.services.BusinessService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Business.class)
public class BusinessTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private BusinessService service;
  @Mock
  private BusinessRepository repository;
  
  Business mockBusiness;
  
  @Before
  public void setUp() throws Exception {
      mockMvc = MockMvcBuilders
              .standaloneSetup(new BusinessService())
              .build();
  }
  
  // Re-initialize the business every time, since we may mutate or delete it.
  @BeforeEach
  public void setUpBusiness() {
    mockBusiness = new Business();    
    mockBusiness.setId(1);
    mockBusiness.setBusiness_name("busy");
    mockBusiness.setYear_founded(1699);
    mockBusiness.setNumber_of_employees(45);
    mockBusiness.setEmail("busy@bartender.net");
    mockBusiness.setStreet("31 Busy Street");
    mockBusiness.setCity("Boston");
    mockBusiness.setState("MA");
    mockBusiness.setZipcode("01234");
    List<String> pays = new ArrayList<>();
    mockBusiness.setPayments(pays);  
    mockBusiness.setFacebook_url("");
    mockBusiness.setInstagram_url("");
    mockBusiness.setTwitter_url("");
  }

  @Test
  public void testCreateBusiness() throws Exception {
      List<Business> businesses = new ArrayList<>();
      Assertions.assertEquals(0, businesses.size());
      ObjectMapper mapper = new ObjectMapper();
      String stringified = mapper.writeValueAsString(mockBusiness);
      Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
      businesses.add(mockBusiness);
      return null;
    }).when(service).createBusiness(any(Business.class));
    this.mockMvc
            .perform(MockMvcRequestBuilders
                    .post("/api/businesses")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(stringified)
                    .characterEncoding("UTF-8"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk());
    Assertions.assertEquals(mockBusiness, businesses.get(0));
  }


  @Test
  public void testFindBusinessByID() throws Exception {
      Mockito.when(service.findBusinessById(1)).thenReturn(mockBusiness);
      this.mockMvc
            .perform(MockMvcRequestBuilders.get("/api/businesses/1"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.business_name").value("busy"))
            .andExpect(jsonPath("$.email").value("busy@bartender.net"))
            .andExpect(jsonPath("$.street").value("31 Busy Street"))
            .andExpect(jsonPath("$.twitter_url").value(""));
  }


  @Test
  public void testUpdateBusiness() throws Exception {
      List<Business> businesses = new ArrayList<>();
      businesses.add(mockBusiness);

      Business newBusiness = new Business();
      newBusiness.setId(2);
      newBusiness.setBusiness_name("busy2");
      newBusiness.setYear_founded(1799);
      newBusiness.setNumber_of_employees(47);
      newBusiness.setEmail("lazy@bartender.net");
      newBusiness.setStreet("10 Street");
      newBusiness.setCity("New York");
      newBusiness.setState("NY");
      newBusiness.setZipcode("99999");
      List<String> pays = new ArrayList<>();
      newBusiness.setPayments(pays);  
      newBusiness.setFacebook_url("ba");
      newBusiness.setInstagram_url("ca1");
      newBusiness.setTwitter_url("aaaa");

      Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
            businesses.get(0).setBusiness_name(newBusiness.getBusiness_name());
            businesses.get(0).setYear_founded(newBusiness.getYear_founded());
            businesses.get(0).setNumber_of_employees(newBusiness.getNumber_of_employees());
            businesses.get(0).setEmail(newBusiness.getEmail());
            businesses.get(0).setStreet(newBusiness.getStreet());
            businesses.get(0).setCity(newBusiness.getCity());
            businesses.get(0).setState(newBusiness.getState());
            businesses.get(0).setZipcode(newBusiness.getZipcode());
            businesses.get(0).setPayments(newBusiness.getPayments());  
            businesses.get(0).setFacebook_url(newBusiness.getFacebook_url());
            businesses.get(0).setInstagram_url(newBusiness.getInstagram_url());
            businesses.get(0).setTwitter_url(newBusiness.getTwitter_url());
            return null;
      }).when(service).updateBusiness(any(Integer.class), any(Business.class));
      ObjectMapper mapper = new ObjectMapper();
      String stringified = mapper.writeValueAsString(newBusiness);
      
      
      // updates the business
      this.mockMvc
            .perform(MockMvcRequestBuilders
                    .put("/api/businesses/1")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(stringified)
                    .characterEncoding("UTF-8"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk());

      Assertions.assertEquals(1, businesses.get(0).getId().intValue());
      Assertions.assertEquals("busy2", businesses.get(0).getBusiness_name());
      Assertions.assertEquals("NY", businesses.get(0).getState());
      Assertions.assertEquals("ba", businesses.get(0).getFacebook_url());
      Assertions.assertEquals("aaaa", businesses.get(0).getTwitter_url());
  }


  @Test
  public void testDeleteBusiness() throws Exception {
      List<Business> businesses = new ArrayList<>();
      businesses.add(mockBusiness);

      Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
          businesses.remove(mockBusiness);
          return null;
      }).when(service).deleteBusiness(1);

      this.mockMvc
                .perform(MockMvcRequestBuilders
                    .delete("/api/businesses/1")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

      Assertions.assertEquals(businesses, new ArrayList<Business>());
  }
}
