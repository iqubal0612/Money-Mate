package com.imam.moneymate.controller;

import com.imam.moneymate.entity.Profile;
import com.imam.moneymate.service.*;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
    private final ExcelService excelService;
    private final IncomeService incomeService;
    private final ExpenseService expenseService;
    private final EmailService emailService;
    private final ProfileService profileService;

    @GetMapping("/income-excel")
    public ResponseEntity<Void> getEmailIncomeExcel() throws IOException, MessagingException {
        Profile profile = profileService.getcurrentProfile();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        excelService.writeIncomeToExcel(outputStream, incomeService.getCurrentMonthIncome());
        emailService.sendEmailWithAttachment(profile.getEmail(),
                "Your Income Excel Report",
                "Please find your attached income report",
                outputStream.toByteArray(),
                "income.xlsx"
        );

        return ResponseEntity.ok(null);
    }

    @GetMapping("/expense-excel")
    public ResponseEntity<Void> getEmailExpenseExcel() throws IOException, MessagingException {
        Profile profile = profileService.getcurrentProfile();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        excelService.writeExpenseToExcel(outputStream, expenseService.getCurrentMonthExpense());
        emailService.sendEmailWithAttachment(profile.getEmail(),
                "Your Expense Excel Report",
                "Please find your attached expense report",
                outputStream.toByteArray(),
                "expense.xlsx"
        );

        return ResponseEntity.ok(null);
    }
}
