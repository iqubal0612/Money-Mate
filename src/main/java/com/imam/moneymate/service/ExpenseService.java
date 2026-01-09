package com.imam.moneymate.service;

import com.imam.moneymate.dto.ExpenseDTO;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {
    public ExpenseDTO addExpense(ExpenseDTO dto);

    public List<ExpenseDTO> getCurrentMonthExpense();

    public void deleteExpense(Long expenseId);

    public List<ExpenseDTO> getLatest5Expenses();

    public BigDecimal getTotalExpense();

    public List<ExpenseDTO> filterExpenses(LocalDate startDate, LocalDate endDate, String keyword, Sort sort);

    public List<ExpenseDTO> getExpensesForUserOnDate(Long profileId, LocalDate date);
}
