package com.project.order_service.feign;

import com.project.order_service.model.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "coupon-service")  // must match spring.application.name in coupon service
public interface CouponClient {

    @GetMapping("/coupons/{code}")
    Coupon getCoupon(@PathVariable("code") String code);

    @PostMapping("/coupons/{code}/validate")
    Boolean validateCoupon(@PathVariable("code") String code);
}

