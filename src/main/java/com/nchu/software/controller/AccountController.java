package com.nchu.software.controller;

import com.nchu.software.service.AccountService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author JayHrn
 * @Date 2023-06-13 17:09
 * @Description
 */
@RestController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


}
