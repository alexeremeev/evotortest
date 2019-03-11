package ru.evotor.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.evotor.domain.Endpoint;
import ru.evotor.domain.Good;
import ru.evotor.domain.GoodAddFailed;
import ru.evotor.event.OnGoodAddEvent;
import ru.evotor.repository.EndpointRepository;
import ru.evotor.repository.GoodAddFailedRepository;
import ru.evotor.service.ConsumerService;

import java.sql.Timestamp;

@Component
public class OnGoodAddEventListener implements ApplicationListener<OnGoodAddEvent> {

    @Autowired
    private EndpointRepository endpointRepository;
    @Autowired
    private ConsumerService consumerService;
    @Autowired
    private GoodAddFailedRepository goodAddFailedRepository;

    @Override
    public void onApplicationEvent(OnGoodAddEvent event) {
        Good good = event.getGood();
        this.endpointRepository.findAll().forEach(endpoint -> {
            ResponseEntity result = this.consumerService.consumeGood(good, endpoint);
            if (result.getStatusCodeValue() < 200 || result.getStatusCodeValue() >= 300) {
                this.goodAddFailedRepository.save(new GoodAddFailed(good.getName(), good.getUnit(), endpoint, new Timestamp(System.currentTimeMillis())));
            }
        });
    }
}
