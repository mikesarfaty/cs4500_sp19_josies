package com.example.cs4500_sp19_josies;


import com.example.cs4500_sp19_josies.models.QuestionType;
import com.example.cs4500_sp19_josies.models.ServiceQuestion;
import com.example.cs4500_sp19_josies.repositories.ServiceQuestionRepository;
import com.example.cs4500_sp19_josies.services.ServiceQuestionService;
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
@WebMvcTest(ServiceQuestionService.class)
public class ServiceQuestionServiceTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ServiceQuestionService service;
    @Mock
    private ServiceQuestionRepository repository;

    ServiceQuestion mockQuestion;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ServiceQuestionService())
                .build();
    }

    // Re-initialize the question every time, since we may mutate or delete it.
    @BeforeEach
    public void setUpQuestion() {
        mockQuestion = new ServiceQuestion();
        mockQuestion.setType(QuestionType.Range);
        mockQuestion.setChoices("0,100000");
        mockQuestion.setId(1);
        mockQuestion.setQuestion("How much are you willing to spend?");
    }

    @Test
    public void testCreateServiceQuestion() throws Exception {
        List<ServiceQuestion> serviceQuestions = new ArrayList<>();

        Assertions.assertEquals(0, serviceQuestions.size());

        ObjectMapper mapper = new ObjectMapper();
        String stringified = mapper.writeValueAsString(mockQuestion);

        Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
            serviceQuestions.add(mockQuestion);
            return null;
        }).when(service).createQuestion(any(ServiceQuestion.class));

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/service-questions")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(stringified)
                        .characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Assertions.assertEquals(mockQuestion, serviceQuestions.get(0));
    }

    @Test
    public void testFindServiceQuestionByID() throws Exception {
        // When the service gets the question with ID "1", return the
        // mock question.
        Mockito.when(service.findQuestionById(1)).thenReturn(mockQuestion);

        // When performing a get on /api/service-questions/1, it should return
        // the mocked service question.
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/service-questions/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.type").value("Range"))
                .andExpect(jsonPath("$.choices").value("0,100000"))
                .andExpect(jsonPath("$.question").value("How much are you willing to spend?"));
    }

    @Test
    public void testUpdateServiceQuestion() throws Exception {
        List<ServiceQuestion> serviceQuestions = new ArrayList<>();
        serviceQuestions.add(mockQuestion);

        ServiceQuestion newQuestion = new ServiceQuestion();
        newQuestion.setId(1);
        newQuestion.setType(QuestionType.TrueFalse);
        newQuestion.setChoices("True,False");
        newQuestion.setQuestion("Are you a home owner?");

        Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
                serviceQuestions.get(0).setType(newQuestion.getType());
                serviceQuestions.get(0).setQuestion(newQuestion.getQuestion());
                serviceQuestions.get(0).setChoices(newQuestion.getChoices());
                return null;
        }).when(service).updateQuestion(any(Integer.class), any(ServiceQuestion.class));

        ObjectMapper mapper = new ObjectMapper();
        String stringified = mapper.writeValueAsString(newQuestion);

        // Update the service question.
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/service-questions/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(stringified)
                        .characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Assertions.assertEquals(1, serviceQuestions.get(0).getId().intValue());
        Assertions.assertEquals(QuestionType.TrueFalse, serviceQuestions.get(0).getType());
        Assertions.assertEquals("True,False", serviceQuestions.get(0).getChoices());
        Assertions.assertEquals("Are you a home owner?", serviceQuestions.get(0).getQuestion());
    }

    @Test
    public void testDeleteServiceQuestion() throws Exception {
        List<ServiceQuestion> serviceQuestions = new ArrayList<>();
        serviceQuestions.add(mockQuestion);

        Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
                serviceQuestions.remove(mockQuestion);
                return null;
        }).when(service).deleteQuestion(1);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/service-questions/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Assertions.assertEquals(serviceQuestions, new ArrayList<ServiceQuestion>());
    }
}
