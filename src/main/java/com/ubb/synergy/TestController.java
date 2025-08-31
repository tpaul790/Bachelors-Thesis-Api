package com.ubb.synergy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ro/mpp")
public class TestController {
    @GetMapping("/say")
    public String hello() {
        return "Synergy is running!";
    }
}