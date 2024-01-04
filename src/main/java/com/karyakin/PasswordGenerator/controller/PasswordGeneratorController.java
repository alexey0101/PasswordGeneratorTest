package com.karyakin.PasswordGenerator.controller;

import com.karyakin.PasswordGenerator.response.ErrorResponse;
import com.karyakin.PasswordGenerator.response.PasswordResponse;
import com.karyakin.PasswordGenerator.service.api.RandomPasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/password/")
@RequiredArgsConstructor
public class PasswordGeneratorController {

    private final RandomPasswordGenerator randomPasswordGenerator;

    @GetMapping("/generate")
    public ResponseEntity<?> generatePassword(@RequestParam(required = false, defaultValue = "5") int lower, @RequestParam(required = false, defaultValue = "5") int upper,
                                              @RequestParam(required = false, defaultValue = "3") int digit, @RequestParam(required = false, defaultValue = "3") int special) {
        try {
            return ResponseEntity.ok(new PasswordResponse(randomPasswordGenerator.generate(lower, upper, digit, special)));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
