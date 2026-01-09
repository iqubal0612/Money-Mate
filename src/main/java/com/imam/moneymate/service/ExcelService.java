package com.imam.moneymate.service;

import com.imam.moneymate.dto.ExpenseDTO;
import com.imam.moneymate.dto.IncomeDTO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface ExcelService {
    public void writeIncomeToExcel(OutputStream outputStream, List<IncomeDTO> incomeDTOS) throws IOException;

    public void writeExpenseToExcel(OutputStream outputStream, List<ExpenseDTO> expenseDTOS) throws IOException;
}
