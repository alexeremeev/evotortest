package ru.evotor.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.evotor.domain.Good;
import ru.evotor.domain.Unit;
import ru.evotor.repository.EndpointRepository;
import ru.evotor.repository.GoodAddFailedRepository;
import ru.evotor.repository.UnitRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GoodApiController.class)
public class GoodApiControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UnitRepository unitRepository;
    @MockBean
    private EndpointRepository endpointRepository;
    @MockBean
    private GoodAddFailedRepository goodAddFailedRepository;


    @Test
    public void whenPostGoodThenGetOkResponse() throws Exception {
        final ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Good good = new Good("test", new Unit("test"));
        mockMvc.perform(
                post("/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(mapper.writeValueAsString(good))
                )
                .andExpect(
                        status().is2xxSuccessful()
                );
    }

    @Test
    public void whenPostNonValidGoodThenBadRequestResponse() throws Exception {
        final ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Good good = new Good();
        mockMvc.perform(
                post("/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(mapper.writeValueAsString(good))
        )
                .andExpect(
                        status().is4xxClientError()
                );
    }
}
