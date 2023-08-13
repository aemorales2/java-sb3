package com.angel.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angel.web.service.DateTimeService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class DateTimeController {

    private final DateTimeService dateTimeService;

    @GetMapping(value="/present")
    public String getCurrentDateTime() {
        return dateTimeService.getCurrentDateTime();
    }
    
}