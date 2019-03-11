package ru.evotor.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.evotor.schedule.cron.GoodAddRetryCron;
import ru.evotor.utils.SecurityUtil;

@RestController
public class ESIUpdateController {

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private GoodAddRetryCron goodAddRetryCron;

    @RequestMapping("/goodaddretrycron/reschedule")
    public ResponseEntity<?> reschedule(@RequestHeader String authorization, @RequestParam String cron) {
        ResponseEntity result;
        if (this.securityUtil.isCronKeyValid(authorization.replace("Basic ", ""))) {
            this.goodAddRetryCron.reschedule(cron);
            result = new ResponseEntity(HttpStatus.OK);
        } else {
            result = new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        return result;
    }

    @RequestMapping("/goodaddretrycron/pause")
    public ResponseEntity<?> pause(@RequestHeader String authorization) {
        ResponseEntity result;
        if (this.securityUtil.isCronKeyValid(authorization.replace("Basic ", ""))) {
            this.goodAddRetryCron.pause();
            result = new ResponseEntity(HttpStatus.OK);
        } else {
            result = new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        return result;
    }

    @RequestMapping("/goodaddretrycron/resume")
    public ResponseEntity<?> resume(@RequestHeader String authorization) {
        ResponseEntity result;
        if (this.securityUtil.isCronKeyValid(authorization.replace("Basic ", ""))) {
            this.goodAddRetryCron.resume();
            result = new ResponseEntity(HttpStatus.OK);
        } else {
            result = new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        return result;
    }

}
