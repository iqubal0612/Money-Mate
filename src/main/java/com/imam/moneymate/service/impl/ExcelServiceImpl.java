package com.imam.moneymate.service.impl;

import com.imam.moneymate.dto.ExpenseDTO;
import com.imam.moneymate.dto.IncomeDTO;
import com.imam.moneymate.service.ExcelService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public void writeIncomeToExcel(OutputStream outputStream, List<IncomeDTO> incomeDTOS) throws IOException {
        try(Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Income");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("S.No");
            header.createCell(1).setCellValue("Name");
            header.createCell(2).setCellValue("Category");
            header.createCell(3).setCellValue("Amount");
            header.createCell(4).setCellValue("Data");

            IntStream.range(0, incomeDTOS.size())
                    .forEach(i -> {
                        IncomeDTO income = incomeDTOS.get(i);
                        Row row = sheet.createRow(i + 1);
                        row.createCell(0).setCellValue(i + 1);
                        row.createCell(1).setCellValue(income.getName() != null ? income.getName() : "N/A");
                        row.createCell(2).setCellValue(income.getCategoryId() != null ? income.getCategoryName() : "N/A");
                        row.createCell(3).setCellValue(income.getAmount() != null ? income.getAmount().doubleValue() : 0);
                        row.createCell(4).setCellValue(income.getDate() != null ? income.getDate().toString() : "N/A");
                    });
            workbook.write(outputStream);
        }
    }

    @Override
    public void writeExpenseToExcel(OutputStream outputStream, List<ExpenseDTO> expenseDTOS) throws IOException {
        try(Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Expense");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("S.No");
            header.createCell(1).setCellValue("Name");
            header.createCell(2).setCellValue("Category");
            header.createCell(3).setCellValue("Amount");
            header.createCell(4).setCellValue("Data");

            IntStream.range(0, expenseDTOS.size())
                    .forEach(i -> {
                        ExpenseDTO expense = expenseDTOS.get(i);
                        Row row = sheet.createRow(i + 1);
                        row.createCell(0).setCellValue(i + 1);
                        row.createCell(1).setCellValue(expense.getName() != null ? expense.getName() : "");
                        row.createCell(2).setCellValue(expense.getCategoryId() != null ? expense.getCategoryName() : "N/A");
                        row.createCell(3).setCellValue(expense.getAmount() != null ? expense.getAmount().doubleValue() : 0);
                        row.createCell(4).setCellValue(expense.getDate() != null ? expense.getDate().toString() : "N/A");
                    });
            workbook.write(outputStream);
        }
    }
}
