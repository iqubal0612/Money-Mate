package com.imam.moneymate.service.impl;

import com.imam.moneymate.dto.ExpenseDTO;
import com.imam.moneymate.dto.IncomeDTO;
import com.imam.moneymate.dto.RecentTransactionDTO;
import com.imam.moneymate.entity.Profile;
import com.imam.moneymate.service.DashboardService;
import com.imam.moneymate.service.ExpenseService;
import com.imam.moneymate.service.IncomeService;
import com.imam.moneymate.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Stream.concat;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final ExpenseService expenseService;

    private final IncomeService incomeService;

    private final ProfileService profileService;

    public Map<String, Object> getDashboardDate() {
        Profile profile = profileService.getcurrentProfile();
        Map<String, Object> map = new LinkedHashMap<>();
        List<IncomeDTO> latestIncome = incomeService.getLatest5Incomes();
        List<ExpenseDTO> latestExpense = expenseService.getLatest5Expenses();
        List<RecentTransactionDTO> recentTransaction =  concat(
                                latestIncome.stream().map(income -> RecentTransactionDTO.builder()
                                        .id(income.getId())
                                        .profileId(profile.getId())
                                        .icon(income.getIcon())
                                        .name(income.getName())
                                        .amount(income.getAmount())
                                        .date(income.getDate())
                                        .createdAt(income.getCreatedAt())
                                        .updatedAt(income.getUpdatedAt())
                                        .type("income")
                                        .build()),
                                latestExpense.stream().map(expense -> RecentTransactionDTO.builder()
                                        .id(expense.getId())
                                        .profileId(profile.getId())
                                        .icon(expense.getIcon())
                                        .name(expense.getName())
                                        .amount(expense.getAmount())
                                        .date(expense.getDate())
                                        .createdAt(expense.getCreatedAt())
                                        .updatedAt(expense.getUpdatedAt())
                                        .type("expense")
                                        .build()))
                                .sorted((a, b) -> {
                                    int compare = b.getDate().compareTo(a.getDate());
                                    if (compare == 0 && a.getCreatedAt() != null && b.getCreatedAt() != null) {
                                        return b.getCreatedAt().compareTo(a.getCreatedAt());
                                    }

                                    return compare;
                                }).collect(Collectors.toList());

        map.put("totalBalance", incomeService.getTotalIncomes().subtract(expenseService.getTotalExpense()));
        map.put("totalIncome", incomeService.getTotalIncomes());
        map.put("totalExpense", expenseService.getTotalExpense());
        map.put("recent5Income", latestIncome);
        map.put("recent5Expense", latestExpense);
        map.put("recentTransaction", recentTransaction);

        return map;
    }
}
