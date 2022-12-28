package com.example.springselectshop.repository;

import com.example.springselectshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

//    List<Product> findAllByUserId(Long userId);
    Page<Product> findAllByUserId(Long userId, Pageable pageable);
    Page<Product> findAll(Pageable pageable);
    Optional<Product> findByIdAndUserId(Long id, Long userId);
}
