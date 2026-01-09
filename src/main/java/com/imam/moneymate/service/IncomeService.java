package com.imam.moneymate.service;

import com.imam.moneymate.dto.IncomeDTO;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IncomeService {
    public IncomeDTO addIncome(IncomeDTO dto);

    public List<IncomeDTO> getCurrentMonthIncome();

    public void deleteIncome(Long incomeId);

    public List<IncomeDTO> getLatest5Incomes();

    public BigDecimal getTotalIncomes();

    public List<IncomeDTO> filterIncomes(LocalDate startDate, LocalDate endDate, String keyword, Sort sort);
}
