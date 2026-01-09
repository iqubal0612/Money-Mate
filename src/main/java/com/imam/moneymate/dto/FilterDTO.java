package com.imam.moneymate.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FilterDTO {
    private String type; //income or expense

    private LocalDate startDate;

    private LocalDate endDate;

    private String keyword;

    private String sortField; //date, amount, time

    private String sortOrder; //asc or desc
}
