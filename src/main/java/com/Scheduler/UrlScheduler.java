package com.Scheduler;

import com.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class UrlScheduler {

    private static final Logger logger = LoggerFactory.getLogger(UrlScheduler.class);
    private final UrlService urlService;

    public UrlScheduler(UrlService urlService) {
        this.urlService = urlService;
    }

    @Scheduled(fixedDelay = 2, timeUnit = TimeUnit.SECONDS)
    private void UrlStatusAnalise(){
        logger.info("Running at {}", LocalDateTime.now());
        urlService.deleteUrlExpired();
    }
}