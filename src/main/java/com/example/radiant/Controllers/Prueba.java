package com.example.radiant.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inicio")
public class Prueba {

    @GetMapping("/test")
    public String testEndpoint() {
        return "xd";
    }

}
