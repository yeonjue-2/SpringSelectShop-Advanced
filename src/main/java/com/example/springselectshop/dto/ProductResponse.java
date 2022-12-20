package com.example.springselectshop.dto;

import com.example.springselectshop.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String title;
    private String link;
    private String image;
    private int lprice;
    private int myprice;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.link = product.getLink();
        this.image = product.getImage();
        this.lprice = product.getLprice();
        this.myprice = product.getMyprice();
    }
}
