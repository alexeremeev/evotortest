package ru.evotor.event;

import org.springframework.context.ApplicationEvent;
import ru.evotor.domain.Good;

public class OnGoodAddEvent extends ApplicationEvent {

    private final Good good;

    public OnGoodAddEvent(final Good good) {
        super(good);
        this.good = good;
    }

    public Good getGood() {
        return good;
    }
}
