package com.imooc.sellorder.service.impl;

import java.math.BigDecimal;

import com.imooc.sellorder.dto.OrderDto;
import com.imooc.sellorder.enums.OrderStatus;
import com.imooc.sellorder.enums.PayStatusEnum;
import com.imooc.sellorder.mapper.OrderDetailMapper;
import com.imooc.sellorder.mapper.OrderMasterMapper;
import com.imooc.sellorder.model.OrderMaster;
import com.imooc.sellorder.service.OrderMasterService;
import com.imooc.sellorder.utils.SerialNumber;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderMasterServiceImpl implements OrderMasterService {


    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;


    @Override
    public void create(OrderDto orderDto) {
        String orderId = SerialNumber.getInstance().getFinalNumber();
        // TODO 查询商品信息（调用商品服务）
        // TODO 计算总价
        // TODO 扣库存 （调用商品服务）
        // TODO 订单入库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal("5"));
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WRIT.getCode());
        orderMaster.setOrderId(orderId);
        orderMasterMapper.insert(orderMaster);
    }
}
