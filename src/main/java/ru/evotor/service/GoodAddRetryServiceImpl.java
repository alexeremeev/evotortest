package ru.evotor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ru.evotor.domain.Good;
import ru.evotor.repository.GoodAddFailedRepository;

import java.sql.Timestamp;

@Service
public class GoodAddRetryServiceImpl implements GoodAddRetryService {
    @Autowired
    private GoodAddFailedRepository goodAddFailedRepository;
    @Autowired
    private ConsumerService consumerService;
    @Override
    public void retryGoodAddAll() {
        this.goodAddFailedRepository.findAll().forEach(entity -> {
            ResponseEntity result = this.consumerService.consumeGood(new Good(entity.getName(), entity.getUnit()), entity.getEndpoint());
            if (result.getStatusCodeValue() < 200 || result.getStatusCodeValue() >= 300) {
                entity.setLastAttempt(new Timestamp(System.currentTimeMillis()));
                this.goodAddFailedRepository.save(entity);
            } else {
                this.goodAddFailedRepository.delete(entity);
            }
        });
    }
}
