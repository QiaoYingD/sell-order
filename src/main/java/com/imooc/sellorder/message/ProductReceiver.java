package com.imooc.sellorder.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.imooc.sellorder.model.ProductInfo;
import com.imooc.sellorder.utils.JsonUtil;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Component
public class ProductReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * rabbit 接受消息
     * @param message
     */
    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {

        List<ProductInfo> productInfoList = (List<ProductInfo>) JsonUtil.fromJson(message, new TypeReference<List<ProductInfo>>() {
        });
        for (ProductInfo productInfo : productInfoList) {
            //存到redis中
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfo.getProductId()), String.valueOf(productInfo.getProductStock()));
        }

    }
}
