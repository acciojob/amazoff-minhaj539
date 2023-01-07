package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public void addOrder(Order order){
        orderRepository.addOrderToDb(order);
    }

    public Order getOrder(String orderId) {
       return orderRepository.getOrderById(orderId);
    }

    public void addPartner(String partnerId) {
        orderRepository.addPartner(partnerId);
    }

    public void addPair(String orderId, String partnerId) {
        orderRepository.addPairToDb(orderId,partnerId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
       return orderRepository.getPartnerById(partnerId);
    }

    public Integer getCount(String partnerId) {
        return orderRepository.getCount(partnerId);
    }


    public List<String> getOrdersByPartnerId(String partnerId) {
        return orderRepository.getOrderByPartnerId(partnerId);
    }

    public List<String> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public Integer countOfUnsignedOrders() {
        return orderRepository.countOfUnsignedOrders();
    }

    public void deleteOrder(String orderId) {
        orderRepository.deleteOrder(orderId);
    }

    public void deletePartner(String partnerId) {
        orderRepository.deletePartner(partnerId);
    }
}
