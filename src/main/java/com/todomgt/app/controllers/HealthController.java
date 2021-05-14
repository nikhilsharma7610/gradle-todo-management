package com.todomgt.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class HealthController {

    @RequestMapping("/health")
    public String allOkay() {
        return "OK";
    }

}
