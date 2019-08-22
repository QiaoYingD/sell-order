package com.imooc.sellorder.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {

    NEW(0, "新订单"),

    FINISHED(1, "已完结"),

    CANCEL(2, "取消");

    private Integer code;

    private String message;

    OrderStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
