package org.ntu.feign;

import org.ntu.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("product-server") // 远程调用的服务名称
public interface ProductFeignApi {
    @RequestMapping("/product/{pid}")
    Product findByPid(@PathVariable("pid") Long pid);
}
