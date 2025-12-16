package com.spam.controller;

import com.spam.repository.master.AccountMasterRepository;
import com.spam.domain.master.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/master")
@RequiredArgsConstructor
public class MasterController {
    private final AccountMasterRepository accountMasterRepository;

    @GetMapping("")
    public ResponseEntity<?> getMaster(@RequestParam("username") String username,
                                       @RequestParam("password") String password){
        Account account = Account.builder().username(username).password(password).build();
        accountMasterRepository.save(account);
        return ResponseEntity.ok(account);
    }
}
