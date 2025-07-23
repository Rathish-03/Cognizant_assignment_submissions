package com.cognizant.account;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @GetMapping("/{number}")
    public Account getAccount(@PathVariable String number) {
        // Return dummy account details
        return new Account(number, "savings", 234343L);
    }

    static class Account {
        public String number;
        public String type;
        public long balance;

        public Account(String number, String type, long balance) {
            this.number = number;
            this.type = type;
            this.balance = balance;
        }
    }
}
