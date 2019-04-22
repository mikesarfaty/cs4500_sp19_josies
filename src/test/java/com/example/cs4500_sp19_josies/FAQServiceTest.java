package com.example.cs4500_sp19_josies;


import com.example.cs4500_sp19_josies.models.FrequentlyAskedAnswer;
import com.example.cs4500_sp19_josies.models.FrequentlyAskedQuestion;
import com.example.cs4500_sp19_josies.repositories.FAQRepository;
import com.example.cs4500_sp19_josies.services.FAQService;
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
@WebMvcTest(FAQService.class)
public class FAQServiceTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private FAQService service;
  @Mock
  private FAQRepository repository;

  FrequentlyAskedQuestion mockQuestion;

  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders
            .standaloneSetup(new FAQService())
            .build();
  }

  // Re-initialize the question every time, since we may mutate or delete it.
  @BeforeEach
  public void setUpQuestion() {
    mockQuestion = new FrequentlyAskedQuestion();
    mockQuestion.setTitle("Food preference");
    mockQuestion.setId(1);
    mockQuestion.setQuestion("What do you like to eat?");
    List<FrequentlyAskedAnswer> ans = new ArrayList<>();
    mockQuestion.setAnswers(ans);
  }

  @Test
  public void testCreateFrequentlyAskedQuestion() throws Exception {
    List<FrequentlyAskedQuestion> frequentlyAskedQuestions = new ArrayList<>();

    Assertions.assertEquals(0, frequentlyAskedQuestions.size());

    ObjectMapper mapper = new ObjectMapper();
    String stringified = mapper.writeValueAsString(mockQuestion);

    Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
      frequentlyAskedQuestions.add(mockQuestion);
      return null;
    }).when(service).createFrequentlyAskedQuestion(any(FrequentlyAskedQuestion.class));

    this.mockMvc
            .perform(MockMvcRequestBuilders
                    .post("/api/faqs")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(stringified)
                    .characterEncoding("UTF-8"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk());

    Assertions.assertEquals(mockQuestion, frequentlyAskedQuestions.get(0));
  }

  @Test
  public void testFindFrequentlyAskedQuestionByID() throws Exception {
    // When the service gets the question with ID "1", return the
    // mock question.
    Mockito.when(service.findFrequentlyAskedQuestionById(1)).thenReturn(mockQuestion);

    // When performing a get on /api/service-questions/1, it should return
    // the mocked service question.
    this.mockMvc
            .perform(MockMvcRequestBuilders.get("/api/faqs/1"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.title").value("Food preference"))
            .andExpect(jsonPath("$.question").value("What do you like to eat?"));
  }

  @Test
  public void testUpdateFrequentlyAskedQuestion() throws Exception {
    List<FrequentlyAskedQuestion> frequentlyAskedQuestions = new ArrayList<>();
    frequentlyAskedQuestions.add(mockQuestion);

    FrequentlyAskedQuestion newQuestion = new FrequentlyAskedQuestion();
    newQuestion.setId(1);
    newQuestion.setTitle("Drink preference");
    newQuestion.setQuestion("What do you like to drink?");

    Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
      frequentlyAskedQuestions.get(0).setTitle(newQuestion.getTitle());
      frequentlyAskedQuestions.get(0).setQuestion(newQuestion.getQuestion());
      return null;
    }).when(service).updateFrequentlyAskedQuestion(any(Integer.class), any(FrequentlyAskedQuestion.class));

    ObjectMapper mapper = new ObjectMapper();
    String stringified = mapper.writeValueAsString(newQuestion);

    // Update the service question.
    this.mockMvc
            .perform(MockMvcRequestBuilders
                    .put("/api/faqs/1")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(stringified)
                    .characterEncoding("UTF-8"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk());

    Assertions.assertEquals(1, frequentlyAskedQuestions.get(0).getId().intValue());
    Assertions.assertEquals("Drink preference", frequentlyAskedQuestions.get(0).getTitle());
    Assertions.assertEquals("What do you like to drink?", frequentlyAskedQuestions.get(0).getQuestion());
  }

  @Test
  public void testDeleteFrequentlyAskedQuestion() throws Exception {
    List<FrequentlyAskedQuestion> frequentlyAskedQuestions = new ArrayList<>();
    frequentlyAskedQuestions.add(mockQuestion);

    Mockito.doAnswer((InvocationOnMock invocationOnMock) -> {
      frequentlyAskedQuestions.remove(mockQuestion);
      return null;
    }).when(service).deleteFrequentlyAskedQuestion(1);

    this.mockMvc
            .perform(MockMvcRequestBuilders
                    .delete("/api/faqs/1")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk());

    Assertions.assertEquals(frequentlyAskedQuestions, new ArrayList<FrequentlyAskedQuestion>());
  }
}
