package com.ticketbooking.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ticketbooking.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByCode(String code);
    
    @Query("SELECT c FROM Coupon c WHERE c.isActive = true AND c.validFrom <= :today AND c.validUntil >= :today")
    List<Coupon> findActiveCoupons(@Param("today") LocalDate today);
    
    @Query("SELECT c FROM Coupon c WHERE c.isActive = true AND c.usedCount < c.maxUsage AND c.validFrom <= :today AND c.validUntil >= :today")
    List<Coupon> findValidCoupons(@Param("today") LocalDate today);
}