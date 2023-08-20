package com.angel.datetime.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angel.datetime.service.DateTimeService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class DateTimeController {

    private final DateTimeService basicService;

    @GetMapping(value="/currentDateTime")
    public String getCurrentDateTime() {
        return basicService.getCurrentDateTime();
    }
    
}