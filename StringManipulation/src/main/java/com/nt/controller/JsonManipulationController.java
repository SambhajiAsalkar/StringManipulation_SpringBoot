package com.nt.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.service.IManipulationService;

@RestController
@RequestMapping("/api/json")
public class JsonManipulationController {

    @Autowired
    private IManipulationService manipulationService;

    @PostMapping("/manipulate")
    public ResponseEntity<String> manipulateJson(
            @RequestParam("replacements") String replacements) {
        try {
            // Process the replacements
            String result = manipulationService.manipulateJson(replacements);
            // Return modified JSON model
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
