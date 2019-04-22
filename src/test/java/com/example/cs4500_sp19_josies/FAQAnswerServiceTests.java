package com.example.cs4500_sp19_josies;


import com.example.cs4500_sp19_josies.models.*;
import com.example.cs4500_sp19_josies.repositories.FAQAnswerRepository;
import com.example.cs4500_sp19_josies.services.FAQAnswerService;
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
@WebMvcTest(FAQAnswerService.class)
public class FAQAnswerServiceTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FAQAnswerService service;
    @Mock
    private FAQAnswerRepository repository;

    FrequentlyAskedQuestion mockQuestion;
    FrequentlyAskedAnswer mockAnswer;
    User mockUser;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new FAQAnswerService())
                .build();
    }

    // Re-initialize the question every time, since we may mutate or delete it.
    @BeforeEach
    public void setUpQuestion() {
        mockQuestion= new FrequentlyAskedQuestion();
        mockQuestion.setQuestion("Food Question");
        mockQuestion.setTitle("What food do you like?");
        mockQuestion.setId(1);
        mockUser = new User();
        mockUser.setUsername("syun");
        mockUser.setId(1);
        mockUser.setPassword("syun");
        mockUser.setFirstName("Seokju");
        mockUser.setLastName("Yun");
        mockUser.setRole("provider");
        mockAnswer = new FrequentlyAskedAnswer();
        mockAnswer.setAnswer("apple");
        mockAnswer.setFrequentlyAskedQuestion(mockQuestion);
        mockAnswer.setId(1);
        mockAnswer.setUser(mockUser);
    }

    @Test
    public void testCreateFAQAnswer() throws Exception {
        List<FrequentlyAskedAnswer> frequentlyAskedAnswers = new ArrayList<>();

        Assertions.assertEquals(0, frequentlyAskedAnswers.size());

        ObjectMapper mapper = new ObjectMapper();
        String stringified = mapper.writeValueAsString(mockAnswer);

        Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
            frequentlyAskedAnswers.add(mockAnswer);
            return null;
        }).when(service).createFrequentlyAskedAnswer(any(FrequentlyAskedAnswer.class));

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/faq-answers")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(stringified)
                        .characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Assertions.assertEquals(mockAnswer, frequentlyAskedAnswers.get(0));
    }

    @Test
    public void testFindFAQAnswerByID() throws Exception {
        // When the service gets the question with ID "1", return the
        // mock question.
        Mockito.when(service.findFrequentlyAskedAnswerById(1)).thenReturn(mockAnswer);

        // When performing a get on /api/service-questions/1, it should return
        // the mocked service question.
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/faq-answers/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.answer").value("apple"));


    }

    @Test
    public void testUpdateFAQAnswer() throws Exception {
        List<FrequentlyAskedAnswer> frequentlyAskedAnswers = new ArrayList<>();
        frequentlyAskedAnswers.add(mockAnswer);

        FrequentlyAskedAnswer newAnswer = new FrequentlyAskedAnswer();
        newAnswer.setId(1);
        newAnswer.setFrequentlyAskedQuestion(mockQuestion);
        newAnswer.setUser(mockUser);
        newAnswer.setAnswer("No");

        Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
            frequentlyAskedAnswers.get(0).setFrequentlyAskedQuestion(newAnswer.getFrequentlyAskedQuestion());
            frequentlyAskedAnswers.get(0).setUser(newAnswer.getUser());
            frequentlyAskedAnswers.get(0).setAnswer(newAnswer.getAnswer());
            return null;
        }).when(service).updateFrequentlyAskedAnswer(any(Integer.class), any(FrequentlyAskedAnswer.class));

        ObjectMapper mapper = new ObjectMapper();
        String stringified = mapper.writeValueAsString(newAnswer);

        // Update the service question.
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/faq-answers/1/title")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(stringified)
                        .characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Assertions.assertEquals(1, frequentlyAskedAnswers.get(0).getId().intValue());
        Assertions.assertEquals(mockQuestion, frequentlyAskedAnswers.get(0).getFrequentlyAskedQuestion());
        Assertions.assertEquals(mockUser, frequentlyAskedAnswers.get(0).getUser());
        Assertions.assertEquals("No", frequentlyAskedAnswers.get(0).getAnswer());
    }

    @Test
    public void testDeleteFrequentlyAskedAnswer() throws Exception {
        List<FrequentlyAskedAnswer> frequentlyAskedAnswers = new ArrayList<>();
        frequentlyAskedAnswers.add(mockAnswer);

        Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
            frequentlyAskedAnswers.remove(mockAnswer);
            return null;
        }).when(service).deleteFrequentlyAskedAnswer(1);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/faq-answers/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        Assertions.assertEquals(frequentlyAskedAnswers, new ArrayList<FrequentlyAskedAnswer>());
    }
}