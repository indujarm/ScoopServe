package com.project.order_service.service;

import com.project.order_service.entity.Order;
import com.project.order_service.entity.OrderItem;
import com.project.order_service.feign.CouponClient;
import com.project.order_service.model.Coupon;
import com.project.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CouponClient couponClient;

    public Order placeOrder(Order order) {
        order.setOrderDate(Instant.now());
        order.setStatus(Order.Status.CREATED);

        for (OrderItem item : order.getItems()) {
            item.setOrder(order);
        }

        if (order.getCouponCode() != null) {
            boolean valid = couponClient.validateCoupon(order.getCouponCode());
            if (!valid) {
                throw new RuntimeException("Invalid coupon code");
            }

            Coupon coupon = couponClient.getCoupon(order.getCouponCode());
            applyDiscount(order, coupon.getDiscountPercent());
        }

        return orderRepository.save(order);
    }

    private void applyDiscount(Order order, double discountPercent) {
        for (OrderItem item : order.getItems()) {
            double originalPrice = item.getUnitPrice().doubleValue();
            double discountedPrice = originalPrice * (1 - discountPercent / 100.0);
            item.setUnitPrice(BigDecimal.valueOf(discountedPrice));
        }
    }

    // ✅ Get order by ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // ✅ Update order status
    public Optional<Order> updateStatus(Long id, Order.Status status) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        optionalOrder.ifPresent(order -> order.setStatus(status));
        return optionalOrder;
    }

    // ✅ Cancel order (mark as CANCELED)
    public void cancelOrder(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        optionalOrder.ifPresent(order -> {
            order.setStatus(Order.Status.CANCELED);
            orderRepository.save(order);
        });
    }

    // ✅ Get orders by customerId
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}
