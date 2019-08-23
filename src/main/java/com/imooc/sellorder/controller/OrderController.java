package com.imooc.sellorder.controller;

import com.imooc.sellorder.converter.OrderFromToOrderDto;
import com.imooc.sellorder.dto.OrderDto;
import com.imooc.sellorder.enums.ResultEnum;
import com.imooc.sellorder.execption.OrderExecption;
import com.imooc.sellorder.form.OrderFrom;
import com.imooc.sellorder.service.OrderMasterService;
import com.imooc.sellorder.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@Slf4j
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
    @PostMapping("/create")
    public ResultVO create(@Valid OrderFrom orderFrom, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确,orderFrom={}", orderFrom);
            throw new OrderExecption(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderFromToOrderDto.convert(orderFrom);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【创建订单】购物车为空");
            throw new OrderExecption(ResultEnum.CARY_ERROR);
        }
        orderMasterService.create(orderDto);
        return null;
    }


}