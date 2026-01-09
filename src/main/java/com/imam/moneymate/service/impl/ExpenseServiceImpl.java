package com.imam.moneymate.service.impl;

import com.imam.moneymate.dto.ExpenseDTO;
import com.imam.moneymate.entity.Category;
import com.imam.moneymate.entity.Expense;
import com.imam.moneymate.entity.Profile;
import com.imam.moneymate.repository.CategoryRepository;
import com.imam.moneymate.repository.ExpenseRepository;
import com.imam.moneymate.service.ExpenseService;
import com.imam.moneymate.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final CategoryRepository categoryRepository;

    private final ExpenseRepository expenseRepository;

    private final ProfileService profileService;

    public ExpenseDTO addExpense(ExpenseDTO dto) {
        Profile profile = profileService.getcurrentProfile();
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Expense newExpense = toEntity(dto, profile,category);
        newExpense = expenseRepository.save(newExpense);

        return toDTO(newExpense);
    }

    public List<ExpenseDTO> getCurrentMonthExpense() {
        Profile profile = profileService.getcurrentProfile();
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = now.withDayOfMonth(now.lengthOfMonth());
        List<Expense> list = expenseRepository.findByProfileIdAndDateBetween(profile.getId(), startDate, endDate);

        return list.stream().map(this::toDTO).toList();
    }

    public void deleteExpense(Long expenseId) {
        Profile profile = profileService.getcurrentProfile();
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow( () -> new RuntimeException("Expense not found"));

        if (!expense.getProfile().getId().equals(profile.getId())) {
            throw new RuntimeException("Unauthorized to delete expense");
        }

        expenseRepository.delete(expense);
    }

    public List<ExpenseDTO> getLatest5Expenses() {
        Profile profile = profileService.getcurrentProfile();
        List<Expense> expenses = expenseRepository.findTop5ByProfileIdOrderByDateDesc(profile.getId());

        return expenses.stream().map(this::toDTO).toList();
    }

    public BigDecimal getTotalExpense() {
        Profile profile = profileService.getcurrentProfile();
        BigDecimal totalExpense = expenseRepository.findTotalExpenseByProfileId(profile.getId());

        return totalExpense != null ? totalExpense : BigDecimal.ZERO;
    }

    public List<ExpenseDTO> filterExpenses(LocalDate startDate, LocalDate endDate, String keyword, Sort sort) {
        Profile profile = profileService.getcurrentProfile();
        List<Expense> list = expenseRepository.findByProfileIdAndDateBetweenAndNameContainingIgnoreCase(profile.getId(), startDate, endDate, keyword, sort);

        return list.stream().map(this::toDTO).toList();
    }

    public List<ExpenseDTO> getExpensesForUserOnDate(Long profileId, LocalDate date) {
        List<Expense> list = expenseRepository.findByProfileIdAndDate(profileId, date);

        return list.stream().map(this::toDTO).toList();
    }

    private Expense toEntity(ExpenseDTO dto, Profile profile, Category category) {

        return Expense.builder()
                .name(dto.getName())
                .icon(dto.getIcon())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .profile(profile)
                .category(category)
                .build();
    }

    private ExpenseDTO toDTO(Expense expense) {

        return ExpenseDTO.builder()
                .id(expense.getId())
                .name(expense.getName())
                .icon(expense.getIcon())
                .categoryId(expense.getCategory() != null ? expense.getCategory().getId() : null)
                .categoryName(expense.getCategory() != null ? expense.getCategory().getName() : "N/A")
                .amount(expense.getAmount())
                .date(expense.getDate())
                .createdAt(expense.getCreatedAt())
                .updatedAt(expense.getUpdatedAt())
                .build();
    }
}
