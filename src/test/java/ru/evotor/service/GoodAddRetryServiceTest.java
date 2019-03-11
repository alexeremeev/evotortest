package ru.evotor.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.evotor.domain.Endpoint;
import ru.evotor.domain.Good;
import ru.evotor.domain.GoodAddFailed;
import ru.evotor.domain.Unit;
import ru.evotor.repository.EndpointRepository;
import ru.evotor.repository.GoodAddFailedRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodAddRetryServiceTest {

    @MockBean
    private EndpointRepository endpointRepository;
    @MockBean
    private GoodAddFailedRepository goodAddFailedRepository;
    @Autowired
    private ConsumerService consumerService;

    @Before
    public void setup() {
        Endpoint first = new Endpoint();
        first.setId(1L);
        first.setName("First Endpoint");
        first.setStatus(200);

        Endpoint second = new Endpoint();
        second.setId(2L);
        second.setName("Second Endpoint");
        second.setStatus(502);

        GoodAddFailed failed = new GoodAddFailed("test", new Unit("test"), second, new Timestamp(System.currentTimeMillis()));


        List<Endpoint> endpoints = new ArrayList<>(Arrays.asList(first, second));
        given(endpointRepository.findAll()).willReturn(endpoints);
        given(goodAddFailedRepository.save(any(GoodAddFailed.class))).willReturn(failed);
        given(goodAddFailedRepository.findAll()).willReturn(Collections.singletonList(failed));
    }

    @Test
    public void whenTryToSendGoodToEndpointsThenGetOneFailed() throws Exception {
        Good good = new Good("test", new Unit("test"));
        this.endpointRepository.findAll().forEach(endpoint -> {
            ResponseEntity result = this.consumerService.consumeGood(good, endpoint);
            if (result.getStatusCodeValue() < 200 || result.getStatusCodeValue() >= 300) {
                this.goodAddFailedRepository.save(new GoodAddFailed(good.getName(), good.getUnit(), endpoint, new Timestamp(System.currentTimeMillis())));
            }
        });
        List<GoodAddFailed> goods = (List<GoodAddFailed>) this.goodAddFailedRepository.findAll();
        assertThat(!goods.isEmpty());
    }

}
