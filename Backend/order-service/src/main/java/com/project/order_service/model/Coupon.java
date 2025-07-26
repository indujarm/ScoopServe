package com.project.order_service.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Coupon {
    private String code;
    private int discountPercent;
    private LocalDate validFrom;
    private LocalDate validUntil;
    private boolean active;
}
