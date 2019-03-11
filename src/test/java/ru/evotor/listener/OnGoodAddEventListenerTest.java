package ru.evotor.listener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;
import ru.evotor.domain.Good;
import ru.evotor.domain.Unit;
import ru.evotor.event.OnGoodAddEvent;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnGoodAddEventListenerTest {
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private OnGoodAddEventListener eventListener;

    @Test
    public void test() {
        eventPublisher.publishEvent(new OnGoodAddEvent(new Good("test", new Unit("test"))));
    }
}
