package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

          setId(id);
          int time=convert(deliveryTime);
          setDeliveryTime(time);

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public String getId()
    {
        return id;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setId(String id) {
        this.id = id;
    }
    public int convert(String deliveryTime){
       String hr=deliveryTime.substring(0,2);
       String min=deliveryTime.substring(3);
       int hour=Integer.parseInt(hr);
       int minute=Integer.parseInt(min);
       return hour*60+minute;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
