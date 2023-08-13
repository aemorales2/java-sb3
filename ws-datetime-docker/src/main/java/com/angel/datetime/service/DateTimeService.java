package com.angel.datetime.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DateTimeService {
     private DateTimeFormatter dateFormatter;
    private static final String DATE_FORMAT_STRING = "MM/dd/yyy HH:mm:ss z";
    private static final String UTC = "UTC";

    public DateTimeService() {
        dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_STRING);
    }

    public String getCurrentDateTime() {
        Instant now = Instant.now();
        ZonedDateTime utc = now.atZone(ZoneId.of(UTC));

        log.info("returning current date time in utc");
        return dateFormatter.format(utc);
    } 
}
