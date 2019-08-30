package com.imooc.sellorder.client;

import com.imooc.sellorder.dto.CartDto;
import com.imooc.sellorder.model.ProductInfo;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product")
@Component
public interface ProductClient {

    @PostMapping("/product/getProductForOrder")
    List<ProductInfo> getProductForOrder(@RequestBody List<String> productId);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<CartDto> cartDtoList);
}
