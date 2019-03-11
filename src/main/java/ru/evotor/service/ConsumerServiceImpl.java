package ru.evotor.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.evotor.domain.Endpoint;
import ru.evotor.domain.Good;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Override
    public ResponseEntity<?> consumeGood(Good good, Endpoint endpoint) {
        return new ResponseEntity<Object>(HttpStatus.valueOf(endpoint.getStatus()));
    }
}
