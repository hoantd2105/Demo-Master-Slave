package com.spam.controller;

import com.spam.repository.slave.AccountSlaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/slave")
@RequiredArgsConstructor
public class SlaveController {
    private final AccountSlaveRepository accountSlaveRepository;

    @GetMapping("")
    public ResponseEntity<?> getSlave(){
        return new ResponseEntity<>(accountSlaveRepository.findAll(), HttpStatus.OK);
    }
}
