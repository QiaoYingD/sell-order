package com.imooc.sellorder.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import com.imooc.sellorder.client.ProductClient;
import com.imooc.sellorder.dto.CartDto;
import com.imooc.sellorder.dto.OrderDto;
import com.imooc.sellorder.enums.OrderStatus;
import com.imooc.sellorder.enums.PayStatusEnum;
import com.imooc.sellorder.mapper.OrderDetailMapper;
import com.imooc.sellorder.mapper.OrderMasterMapper;
import com.imooc.sellorder.model.OrderDetail;
import com.imooc.sellorder.model.OrderMaster;
import com.imooc.sellorder.model.ProductInfo;
import com.imooc.sellorder.service.OrderMasterService;
import com.imooc.sellorder.utils.KeyUtil;
import com.imooc.sellorder.utils.SerialNumber;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderMasterServiceImpl implements OrderMasterService {


    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public String create(OrderDto orderDto) {
        String orderId = SerialNumber.getInstance().getFinalNumber();
        //  查询商品信息（调用商品服务）
        List<String> productIdList = orderDto.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfo> productForOrder = productClient.getProductForOrder(productIdList);
        //  计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (ProductInfo productInfo : productForOrder) {
            for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    //单价*数量
                    orderAmout = new BigDecimal(orderDetail.getProductQuantity()).multiply(productInfo.getProductPrice()).add(orderAmout);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailMapper.addOrderDetail(orderDetail);
                }
            }
        }
        //  扣库存 （调用商品服务）
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productClient.decreaseStock(cartDtoList);
        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WRIT.getCode());
        orderMaster.setOrderId(orderId);
        orderMasterMapper.insert(orderMaster);
        return orderId;
    }
}
