package com.project.coupon_service.controller;

import com.project.coupon_service.entity.Coupon;
import com.project.coupon_service.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    // POST /coupons — create a coupon
    @PostMapping
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
        return ResponseEntity.ok(couponService.createCoupon(coupon));
    }

    // GET /coupons/{code} — fetch coupon details
    @GetMapping("/{code}")
    public ResponseEntity<Coupon> getCoupon(@PathVariable String code) {
        return couponService.getCoupon(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /coupons/{code} — activate/deactivate or adjust dates
    @PutMapping("/{code}")
    public ResponseEntity<Coupon> updateCoupon(@PathVariable String code, @RequestBody Coupon updatedCoupon) {
        return couponService.updateCoupon(code, updatedCoupon)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /coupons/{code} — remove a coupon
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable String code) {
        couponService.deleteCoupon(code);
        return ResponseEntity.noContent().build();
    }

    // POST /coupons/{code}/validate — check if coupon is valid
    @PostMapping("/{code}/validate")
    public ResponseEntity<Boolean> validateCoupon(@PathVariable String code) {
        boolean isValid = couponService.validateCoupon(code);
        return ResponseEntity.ok(isValid);
    }
}
