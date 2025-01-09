package com.kkindustry.alpha.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

    @GetMapping(value = "/home")
    public ResponseEntity<String> home(@RequestParam(value = "name") String name){


        return ResponseEntity.ok(String.format("Hello "+name));

    }

}
