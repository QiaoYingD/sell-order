package com.imooc.sellorder.dto;

import lombok.Data;


@Data
public class CartDto {

    private String productId;

    private Integer productStock;

    public CartDto() {
    }

    public CartDto(String productId, Integer productStock) {
        this.productId = productId;
        this.productStock = productStock;
    }
}
