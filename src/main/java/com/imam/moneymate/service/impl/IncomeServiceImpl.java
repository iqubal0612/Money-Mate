package com.imam.moneymate.service.impl;

import com.imam.moneymate.dto.IncomeDTO;
import com.imam.moneymate.entity.Category;
import com.imam.moneymate.entity.Income;
import com.imam.moneymate.entity.Profile;
import com.imam.moneymate.repository.CategoryRepository;
import com.imam.moneymate.repository.IncomeRepository;
import com.imam.moneymate.service.IncomeService;
import com.imam.moneymate.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final CategoryRepository categoryRepository;

    private final IncomeRepository incomeRepository;

    private final ProfileService profileService;

    public IncomeDTO addIncome(IncomeDTO dto) {
        Profile profile = profileService.getcurrentProfile();
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Income newIncome = toEntity(dto, profile,category);
        newIncome = incomeRepository.save(newIncome);

        return toDTO(newIncome);
    }

    public List<IncomeDTO> getCurrentMonthIncome() {
        Profile profile = profileService.getcurrentProfile();
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = now.withDayOfMonth(now.lengthOfMonth());
        List<Income> list = incomeRepository.findByProfileIdAndDateBetween(profile.getId(), startDate, endDate);

        return list.stream().map(this::toDTO).toList();
    }

    public void deleteIncome(Long incomeId) {
        Profile profile = profileService.getcurrentProfile();
        Income income = incomeRepository.findById(incomeId)
                .orElseThrow( () -> new RuntimeException("Income not found"));

        if (!income.getProfile().getId().equals(profile.getId())) {
            throw new RuntimeException("Unauthorized to delete expense");
        }

        incomeRepository.delete(income);
    }

    public List<IncomeDTO> getLatest5Incomes() {
        Profile profile = profileService.getcurrentProfile();
        List<Income> incomes = incomeRepository.findTop5ByProfileIdOrderByDateDesc(profile.getId());

        return incomes.stream().map(this::toDTO).toList();
    }

    public BigDecimal getTotalIncomes() {
        Profile profile = profileService.getcurrentProfile();
        BigDecimal totalIncomes = incomeRepository.findTotalExpenseByProfileId(profile.getId());

        return totalIncomes != null ? totalIncomes : BigDecimal.ZERO;
    }

    public List<IncomeDTO> filterIncomes(LocalDate startDate, LocalDate endDate, String keyword, Sort sort) {
        Profile profile = profileService.getcurrentProfile();
        List<Income> list = incomeRepository.findByProfileIdAndDateBetweenAndNameContainingIgnoreCase(profile.getId(), startDate, endDate, keyword, sort);

        return list.stream().map(this::toDTO).toList();
    }

    private Income toEntity(IncomeDTO dto, Profile profile, Category category) {

        return Income.builder()
                .name(dto.getName())
                .icon(dto.getIcon())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .profile(profile)
                .category(category)
                .build();
    }

    private IncomeDTO toDTO(Income income) {

        return IncomeDTO.builder()
                .id(income.getId())
                .name(income.getName())
                .icon(income.getIcon())
                .categoryId(income.getCategory() != null ? income.getCategory().getId() : null)
                .categoryName(income.getCategory() != null ? income.getCategory().getName() : "N/A")
                .amount(income.getAmount())
                .date(income.getDate())
                .createdAt(income.getCreatedAt())
                .updatedAt(income.getUpdatedAt())
                .build();
    }
}
