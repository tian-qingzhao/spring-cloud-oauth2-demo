package com.tqz.oauth2.order.entity;

/**
 * <p>订单实体类
 *
 * @author tianqingzhao
 * @since 2022/8/27 12:34
 */
public class Order {
    
    private Integer id;
    
    private String orderNo;
    
    public Order(Integer id, String orderNo) {
        this.id = id;
        this.orderNo = orderNo;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getOrderNo() {
        return orderNo;
    }
    
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    
    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", orderNo='" + orderNo + '\'' + '}';
    }
}
