package com.imooc.sellorder.controller;

import com.imooc.sellorder.service.OrderMasterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Resource
    private OrderMasterService orderMasterService;


    /**
     * 1.参数检验
     * 2.查询商品信息（调用商品服务）
     * 3.计算总价
     * 4.扣库存（调用商品服务）
     * 5.订单入库
     */
    public void create() {

    }


}
