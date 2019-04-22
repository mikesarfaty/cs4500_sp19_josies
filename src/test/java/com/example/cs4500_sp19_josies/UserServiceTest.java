package com.example.cs4500_sp19_josies;


import com.example.cs4500_sp19_josies.models.User;
import com.example.cs4500_sp19_josies.repositories.ServiceQuestionRepository;
import com.example.cs4500_sp19_josies.services.UserService;
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

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserService.class)
public class UserServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService service;
    @Mock
    private ServiceQuestionRepository repository;

    User mockUser;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new UserService())
                .build();
    }

    // Re-initialize the user every time, since we may mutate or delete it.
    @BeforeEach
    public void setUpUser() {
        mockUser = new User();
        mockUser.setUsername("username");
        mockUser.setRole("provider");
        mockUser.setId(1);
        mockUser.setPassword("password");
        mockUser.setFirstName("john");
        mockUser.setLastName("john");
    }

    @Test
    public void testCreateUser() throws Exception {
        List<User> users = new ArrayList<>();

        Assertions.assertEquals(0, users.size());

        ObjectMapper mapper = new ObjectMapper();
        String stringified = mapper.writeValueAsString(mockUser);

        Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
            users.add(mockUser);
            return null;
        }).when(service).createUser(any(User.class));

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(stringified)
                        .characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Assertions.assertEquals(mockUser, users.get(0));
    }

    @Test
    public void testFindServiceQuestionByID() throws Exception {
        Mockito.when(service.findUserById(1)).thenReturn(mockUser);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/users/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.firstName").value("john"))
                .andExpect(jsonPath("$.lastName").value("john"))
                .andExpect(jsonPath("$.username").value("username"))
                .andExpect(jsonPath("$.password").value("password"))
                .andExpect(jsonPath("$.role").value("provider"));

    }

    @Test
    public void testUpdateServiceQuestion() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(mockUser);

        User newUser = new User();
        newUser.setId(1);
        newUser.setUsername("johnny");
        newUser.setFirstName("john");
        newUser.setLastName("smithy");
        newUser.setPassword("hunter2");
        newUser.setRole("hunter");

        Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
            users.get(0).setId(newUser.getId());
            users.get(0).setUsername(newUser.getUsername());
            users.get(0).setPassword(newUser.getPassword());
            users.get(0).setFirstName(newUser.getFirstName());
            users.get(0).setLastName(newUser.getLastName());
            users.get(0).setRole(newUser.getRole());
            return null;
        }).when(service).updateUser(any(Integer.class), any(User.class));

        ObjectMapper mapper = new ObjectMapper();
        String stringified = mapper.writeValueAsString(newUser);

        // Update the user.
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(stringified)
                        .characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Assertions.assertEquals(1, users.get(0).getId().intValue());
        Assertions.assertEquals("johnny", users.get(0).getUsername());
        Assertions.assertEquals("john", users.get(0).getFirstName());
        Assertions.assertEquals("smithy", users.get(0).getLastName());
        Assertions.assertEquals("hunter", users.get(0).getRole());
        Assertions.assertEquals("hunter2", users.get(0).getPassword());

    }

    @Test
    public void testDeleteServiceQuestion() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(mockUser);

        Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
            users.remove(mockUser);
            return null;
        }).when(service).deleteUser(1);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Assertions.assertEquals(users, new ArrayList<User>());
    }
}