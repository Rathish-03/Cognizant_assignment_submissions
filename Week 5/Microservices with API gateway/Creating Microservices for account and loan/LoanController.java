package com.cognizant.loan;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @GetMapping("/{number}")
    public Loan getLoan(@PathVariable String number) {
        // Return dummy loan account details
        return new Loan(number, "car", 400000L, 3258, 18);
    }

    static class Loan {
        public String number;
        public String type;
        public long loan;
        public int emi;
        public int tenure;

        public Loan(String number, String type, long loan, int emi, int tenure) {
            this.number = number;
            this.type = type;
            this.loan = loan;
            this.emi = emi;
            this.tenure = tenure;
        }
    }
}
