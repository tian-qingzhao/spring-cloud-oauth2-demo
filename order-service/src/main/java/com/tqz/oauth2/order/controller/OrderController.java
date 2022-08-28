package com.tqz.oauth2.order.controller;

import com.tqz.oauth2.order.entity.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>订单控制器
 *
 * @author tianqingzhao
 * @since 2022/8/27 12:35
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    
    private Map<Integer, Order> orderMap = new ConcurrentHashMap<>();
    
    @RequestMapping("/selectById")
    public Order selectById(Integer id) {
        System.out.println("id:" + id);
        return orderMap.get(id);
    }
    
    @RequestMapping("saveOrder")
    public Order saveOrder(Order order) {
        System.out.println(order.toString());
        
        orderMap.put(order.getId(), order);
        
        return order;
    }
    
    @PostConstruct
    public void init() {
        orderMap.put(1, new Order(1, "order-001"));
    }
}
