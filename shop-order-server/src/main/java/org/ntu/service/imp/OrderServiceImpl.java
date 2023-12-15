package org.ntu.service.imp;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.ntu.dao.OrderDao;
import org.ntu.domain.Order;
import org.ntu.domain.Product;
import org.ntu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService{

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient; // 从nacos中获取服务列表（地址）

    @Override
    public Order createOrder(Long productId, Long userId) {
        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", productId);
        //远程调用商品微服务,查询商品信息

        String url = "http://product-service/product/" + productId;
        log.info("服务的地址是:{}", url);
        Product product = restTemplate.getForObject(url, Product.class);

        log.info("查询到{}号商品的信息,内容是:{}", productId,
                JSON.toJSONString(product));
        //创建订单并保存
        Order order = new Order();
        order.setUid(userId);
        order.setUsername("Songwen Hua");
        order.setPid(productId);
        order.setPname(product.getPname()); order.setPprice(product.getPprice());
        order.setNumber(1);
        orderDao.save(order);
        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));
        return order;
    }
}





