package com.project.coupon_service.service;

import com.project.coupon_service.entity.Coupon;
import com.project.coupon_service.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public Optional<Coupon> getCoupon(String code) {
        return couponRepository.findByCode(code);
    }

    public Optional<Coupon> updateCoupon(String code, Coupon updated) {
        return couponRepository.findByCode(code).map(existing -> {
            existing.setDiscountPercent(updated.getDiscountPercent());
            existing.setValidFrom(updated.getValidFrom());
            existing.setValidUntil(updated.getValidUntil());
            existing.setActive(updated.getActive());
            return couponRepository.save(existing);
        });
    }

    public void deleteCoupon(String code) {
        couponRepository.deleteById(code);
    }

    public boolean validateCoupon(String code) {
        return couponRepository.findByCode(code)
                .filter(c -> Boolean.TRUE.equals(c.getActive()))
                .filter(c -> !LocalDate.now().isBefore(c.getValidFrom()))
                .filter(c -> !LocalDate.now().isAfter(c.getValidUntil()))
                .isPresent();
    }
}
