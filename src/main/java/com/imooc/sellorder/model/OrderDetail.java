package com.imooc.sellorder.model;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderDetail {

    /**
     * 主键id
     */
    private String detailId;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 当前价格，单位分
     */
    private BigDecimal productPrice;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    /**
     * 小图
     */
    private String productIcon;


}
