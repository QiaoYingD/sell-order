package com.imooc.sellorder.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.imooc.sellorder.dto.OrderDto;
import com.imooc.sellorder.enums.ResultEnum;
import com.imooc.sellorder.execption.OrderExecption;
import com.imooc.sellorder.form.OrderFrom;
import com.imooc.sellorder.model.OrderDetail;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderFromToOrderDto {


    public static OrderDto convert(OrderFrom orderFrom) {
        Gson gson = new Gson();
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerAddress(orderFrom.getAddress());
        orderDto.setBuyerName(orderFrom.getName());
        orderDto.setBuyerOpenId(orderFrom.getOpenid());
        orderDto.setBuyerPhone(orderFrom.getPhone());
        List<OrderDetail> orderDetails = new ArrayList<>();
        try {
            orderDetails = gson.fromJson(orderFrom.getItems(), new TypeToken<OrderDetail>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.error("【json转换】错误，String={}", orderFrom.getItems());
            throw new OrderExecption(ResultEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetails);
        return orderDto;
    }


}
