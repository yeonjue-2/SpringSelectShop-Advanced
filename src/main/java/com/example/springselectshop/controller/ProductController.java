package com.example.springselectshop.controller;

import com.example.springselectshop.dto.ProductMypriceRequest;
import com.example.springselectshop.dto.ProductRequest;
import com.example.springselectshop.dto.ProductResponse;
import com.example.springselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 관심 상품 등록하기
    @PostMapping("/products")
    public ProductResponse createProduct(@RequestBody ProductRequest requestDto, HttpServletRequest request) {
        // 응답 보내기
        return productService.createProduct(requestDto, request);
    }


    // 관심 상품 조회하기
    @GetMapping("/products")
    public List<ProductResponse> getProducts(HttpServletRequest request) {
        // 응답 보내기
        return productService.getProducts(request);
    }

    // 관심 상품 최저가 등록하기
    @PutMapping("/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequest requestDto, HttpServletRequest request) {
        // 응답 보내기 (업데이트된 상품 id)
        return productService.updateProduct(id, requestDto, request);
    }

}
