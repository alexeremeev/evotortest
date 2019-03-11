package ru.evotor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.evotor.domain.Good;
import ru.evotor.event.OnGoodAddEvent;

import javax.validation.Valid;

@RestController
public class GoodApiController {


    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<?> proxyGood(@Valid @RequestBody Good good) {
        this.eventPublisher.publishEvent(new OnGoodAddEvent(good));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
