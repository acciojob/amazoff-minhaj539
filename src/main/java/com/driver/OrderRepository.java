package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {
        Map<String,Order> orderDb=new HashMap<>();
        Map<String,DeliveryPartner> partnerDb=new HashMap<>();
        Map<String,List<String>> pairDb=new HashMap<>();

    public void addOrderToDb(Order order) {
        String key=order.getId();
        if(!orderDb.containsKey(key)){
            orderDb.put(key,order);
        }

    }

    public Order getOrderById(String orderId) {
        if(orderDb.containsKey(orderId)) return orderDb.get(orderId);
        return null;
    }

    public void addPartner(String partnerId) {
        if(!partnerDb.containsKey(partnerId)){
       partnerDb.put(partnerId,new DeliveryPartner(partnerId));
        }

    }

    public void addPairToDb(String orderId, String partnerId) {
        if(orderDb.containsKey(orderId)&&partnerDb.containsKey(partnerId)) {
            if (pairDb.containsKey(partnerId)) {
                List<String> l = pairDb.get(partnerId);
                l.add(orderId);
                pairDb.put(partnerId, l);
            } else {
                List<String> l = new ArrayList<>();
                l.add(orderId);
                pairDb.put(partnerId, l);
            }
        }

    }

    public DeliveryPartner getPartnerById(String partnerId) {
        if(partnerDb.containsKey(partnerId)) return partnerDb.get(partnerId);
        return null;
    }

    public Integer getCount(String partnerId) {
        if(pairDb.containsKey(partnerId)) return pairDb.get(partnerId).size();
        return 0;
    }

    public List<String> getOrderByPartnerId(String partnerId) {
        if(pairDb.containsKey(partnerId)) return pairDb.get(partnerId);
        return null;
    }

    public List<String> getAllOrders() {
        List<String> l=new ArrayList<>();
        for(String orders:orderDb.keySet()){
            l.add(orders);
        }
        return l;
    }

    public Integer countOfUnsignedOrders() {
        Integer cnt=0;

        for(String orders:orderDb.keySet()){
            boolean flag=false;
            for(String partner:pairDb.keySet()){
                List<String> ordersInPair=pairDb.get(partner);
                for(String orderFromList:ordersInPair){
                    if(orders.equals(orderFromList)){
                        flag=true;
                    }
                }
            }
            if(flag==false) cnt++;
        }
        return cnt;
    }

    public void deleteOrder(String orderId) {
        if(orderDb.containsKey(orderId)){
            for(String partners:pairDb.keySet()){
                List<String> l=pairDb.get(partners);
                for(String order:l){

                    if(order.equals(orderId)) l.remove(orderId);
                    pairDb.put(partners,l);
                }
            }
            orderDb.remove(orderId);
        }
    }

    public void deletePartner(String partnerId) {
        if(partnerDb.containsKey(partnerId)){
            if(pairDb.containsKey(partnerId)){
                pairDb.remove(partnerId);
            }
            partnerDb.remove(partnerId);
        }

    }
}
