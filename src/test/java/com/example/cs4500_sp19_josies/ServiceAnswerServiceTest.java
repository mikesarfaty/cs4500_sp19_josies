package com.example.cs4500_sp19_josies;

import com.example.cs4500_sp19_josies.models.QuestionType;
import com.example.cs4500_sp19_josies.models.ServiceAnswer;
import com.example.cs4500_sp19_josies.models.ServiceQuestion;
import com.example.cs4500_sp19_josies.models.User;
import com.example.cs4500_sp19_josies.services.ServiceAnswerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
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
@WebMvcTest(ServiceAnswerService.class)
public class ServiceAnswerServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ServiceAnswerService service;

    ServiceAnswer mockAnswer;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ServiceAnswerService())
                .build();
    }

    @BeforeEach
    public void setUpAnswer() {
        mockAnswer = new ServiceAnswer();
        mockAnswer.setId(1);
        mockAnswer.setTrueFalseAnswer(Boolean.TRUE);
    }

    @Test
    public void testCreateServiceAnswer() throws Exception {
        List<ServiceAnswer> answers = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        String objectString = objectMapper.writeValueAsString(mockAnswer);

        Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
            answers.add(mockAnswer);
            return null;
        }).when(service).createAnswer(any(ServiceAnswer.class));

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/service-answers")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectString)
                        .characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Assertions.assertEquals(mockAnswer, answers.get(0));
    }

    @Test
    public void testFindServiceAnswerById() throws Exception {

        Mockito.when(service.findServiceAnswerById(1)).thenReturn(mockAnswer);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/service-answers/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.trueFalseAnswer").value("true"));

    }

    @Test
    public void testUpdateServiceAnswer() throws Exception {
        List<ServiceAnswer> answers = new ArrayList<>();
        answers.add(mockAnswer);

        ServiceAnswer updatedAnswer = new ServiceAnswer();
        updatedAnswer.setId(1);
        updatedAnswer.setTrueFalseAnswer(Boolean.FALSE);

        Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
            answers.get(0).setTrueFalseAnswer(updatedAnswer.getTrueFalseAnswer());
            return null;
        }).when(service).updateAnswer(any(Integer.class), any(ServiceAnswer.class));

        ObjectMapper objectMapper = new ObjectMapper();
        String objectString = objectMapper.writeValueAsString(updatedAnswer);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/service-answers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectString)
                        .characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Assertions.assertEquals(1, answers.get(0).getId().intValue());
        Assertions.assertEquals(Boolean.FALSE, answers.get(0).getTrueFalseAnswer());
    }

    @Test
    public void testDeleteServiceAnswer() throws Exception {
        List<ServiceAnswer> answers = new ArrayList<>();
        answers.add(mockAnswer);

        Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
            answers.remove(mockAnswer);
            return null;
        }).when(service).deleteAnswer(1);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/service-answers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Assertions.assertEquals(new ArrayList<ServiceAnswer>(), answers);
    }

}
