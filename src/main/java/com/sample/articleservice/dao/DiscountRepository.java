package com.sample.articleservice.dao;

import com.sample.articleservice.entities.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<DiscountEntity, Long> {
}
