package ru.evotor.service;

import org.springframework.http.ResponseEntity;
import ru.evotor.domain.Endpoint;
import ru.evotor.domain.Good;

public interface ConsumerService {
    ResponseEntity<?> consumeGood(Good good, Endpoint endpoint);
}
