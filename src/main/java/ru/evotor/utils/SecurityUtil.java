package ru.evotor.utils;

import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    private final static String CRON_KEY = "80769144e05b4db38a167bf9aff1d23b";

    public boolean isCronKeyValid(String key) {
        return key != null && CRON_KEY.equals(key);
    }
}
