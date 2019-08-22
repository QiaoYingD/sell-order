package com.imooc.sellorder.service.impl;

import com.imooc.sellorder.dto.OrderDto;
import com.imooc.sellorder.service.OrderMasterService;
import org.springframework.stereotype.Service;

@Service
public class OrderMasterServiceImpl implements OrderMasterService {


    @Override
    public void create(OrderDto orderDto) {
        // TODO 查询商品信息（调用商品服务）
        // TODO 计算总价
        // TODO 扣库存 （调用商品服务）
        // TODO 订单入库
    }
}
