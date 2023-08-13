package com.angel.web.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

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
        return dateFormatter.format(utc);
    }

}