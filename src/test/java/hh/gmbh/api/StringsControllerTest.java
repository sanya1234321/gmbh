package hh.gmbh.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import hh.gmbh.db.entities.GmbhStringEntity;
import hh.gmbh.db.repo.GmbhStringRepository;
import hh.gmbh.model.GmbhStringRequest;
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

import static hh.gmbh.TestConstants.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StringsController.class, secure = false)
@RunWith(SpringRunner.class)
public class StringsControllerTest {

    @MockBean
    GmbhStringRepository repository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setUp() {

        given(repository.save(any(GmbhStringEntity.class)))
                .willReturn(GmbhStringEntity.builder().value(TEST_STRING).build());

        given(repository.countStrings())
                .willReturn(Arrays.asList(TEST_COUNT_VALUE));

        given(repository.getAverageLength())
                .willReturn(TEST_INTEGER);

    }

    @Test
    public void testSave() throws Exception {

        mockMvc
            .perform(
                    post("/strings/add")
                            .content(objectMapper.writeValueAsBytes(GmbhStringRequest.builder().value(TEST_STRING).build()))
                            .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk());

        verify(repository, times(1)).save(any(GmbhStringEntity.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void testGetCount() throws Exception {

        this.mockMvc
                .perform(
                        get("/strings/count")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].value").value(TEST_STRING))
                .andExpect(jsonPath("$[0].count").value(TEST_LONG));

        verify(repository, times(1)).countStrings();
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void testGetAverageLength() throws Exception {

        this.mockMvc
                .perform(
                        get("/strings/avg")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(TEST_INTEGER));

        verify(repository, times(1)).getAverageLength();
        verifyNoMoreInteractions(repository);
    }
}
