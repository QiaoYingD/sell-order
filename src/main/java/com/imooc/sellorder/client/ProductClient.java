package com.imooc.sellorder.client;

import com.imooc.sellorder.dto.CartDto;
import com.imooc.sellorder.model.ProductInfo;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product", fallback = ProductClient.ProductClientFallBack.class)
@Component
public interface ProductClient {

    @PostMapping("/product/getProductForOrder")
    List<ProductInfo> getProductForOrder(@RequestBody List<String> productId);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<CartDto> cartDtoList);


    @Component
    public static class ProductClientFallBack implements ProductClient {

        @Override
        public List<ProductInfo> getProductForOrder(List<String> productId) {
            return null;
        }

        @Override
        public void decreaseStock(List<CartDto> cartDtoList) {

        }
    }


}
