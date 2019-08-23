package com.imooc.sellorder.execption;

import com.imooc.sellorder.enums.ResultEnum;

public class OrderExecption extends RuntimeException {

    private Integer code;

    public OrderExecption(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderExecption(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
