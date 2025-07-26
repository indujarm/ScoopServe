package com.project.coupon_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {

    @Id
    @Column(length = 50, unique = true, nullable = false)
    private String code;

    private Integer discountPercent;

    private LocalDate validFrom;

    private LocalDate validUntil;

    private Boolean active;
}
