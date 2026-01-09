package com.imam.moneymate.controller;

import com.imam.moneymate.dto.IncomeDTO;
import com.imam.moneymate.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/incomes")
public class IncomeController {
    private final IncomeService incomeService;

    @PostMapping
    public ResponseEntity<IncomeDTO> addExpense(@RequestBody IncomeDTO expenseDTO) {
        IncomeDTO saved = incomeService.addIncome(expenseDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @GetMapping
    public ResponseEntity<List<IncomeDTO>> getIncome() {
        List<IncomeDTO> incomes = incomeService.getCurrentMonthIncome();

        return ResponseEntity.ok(incomes);
    }

    @DeleteMapping("/{incomeId}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long incomeId) {
        incomeService.deleteIncome(incomeId);

        return ResponseEntity.noContent().build();
    }
}
