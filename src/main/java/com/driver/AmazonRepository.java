package com.driver;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class AmazonRepository {
    HashMap<String, Order> ordersmap = new HashMap<>();

    HashMap<String, DeliveryPartner> deliverypartnermap = new HashMap<>();

    HashMap<String, List<Order>> pairmap = new HashMap<>();

    HashMap<String, String> mappingmap = new HashMap<>();


    public void addOrder(Order order) {

        Order orderTobeAdded = new Order(order.getId(), Integer.toString(order.getDeliveryTime()));

        ordersmap.put(order.getId(), orderTobeAdded);

    }

    public void addPartner(String partnerId) {

        DeliveryPartner deliveryPartnerToBeAdded = new DeliveryPartner(partnerId);
        deliverypartnermap.put(partnerId, deliveryPartnerToBeAdded);

    }

    // size = all orders - assigned = ans
    // p1 p2 p3
    public void partnerPairOrder(String orderId, String partnerId) {

        if(!pairmap.containsKey(partnerId)){
            // no contains
            pairmap.put(partnerId, new ArrayList<>());
        }
        Order orderToBePaired = ordersmap.get(orderId);
        pairmap.get(partnerId).add(orderToBePaired);

        DeliveryPartner partner = deliverypartnermap.get(partnerId);
        int currOrder = partner.getNumberOfOrders();
        partner.setNumberOfOrders(currOrder + 1);

        mappingmap.put(orderId, partnerId);

    }

    public Order getOrderById(String orderId) {
        return ordersmap.get(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return deliverypartnermap.get(partnerId);
    }


    public int getNumberOfOrderAssignedToPartner(String partnerId) {
        DeliveryPartner partner = deliverypartnermap.get(partnerId);
        int currOrder = partner.getNumberOfOrders();
        return currOrder;


    }
    public List<String> getAllOrdersAssignedToPartner(String partnerId) {
        List<Order> orders = pairmap.get(partnerId);

        List<String> ordersName = new ArrayList<>();

        for(Order order : orders){
            ordersName.add(order.toString());
        }
        return ordersName;
    }
    public List<String> getAllOrders() {

        List<String> allOrders = new ArrayList<>();

        for(Order order: ordersmap.values()){

            allOrders.add(order.toString());
        }
        return allOrders;

    }
    public Integer getCountOfUnassignedOrders() {
        int allOrders = ordersmap.size();

        int assignedOrders = 0;
        for( String partnerId : pairmap.keySet()){
            assignedOrders += pairmap.get(partnerId).size();
        }
        return (allOrders - assignedOrders);

    }

    public void deletePartnerById(String partnerId) {
        if(deliverypartnermap.containsKey(partnerId)) {
            deliverypartnermap.remove(partnerId);
        }
        if(pairmap.containsKey(partnerId)){
            pairmap.remove(partnerId);
        }
        for(String orderId : mappingmap.keySet()){
            if(mappingmap.get(orderId) == partnerId){
                mappingmap.remove(orderId);
                break;
            }
        }
    }
    public void deleteOrderById(String orderId) {

        if(ordersmap.containsKey(orderId))
            ordersmap.remove(orderId);
        if(mappingmap.containsKey(orderId)){

            String partnerId = mappingmap.get(orderId);
            List<Order> allOrdersAssignedToPartner = pairmap.get(partnerId);

            for(Order order : allOrdersAssignedToPartner){
                if(order.getId() == orderId){
                    allOrdersAssignedToPartner.remove(order);
                    break;
                }
            }

            pairmap.put(partnerId, allOrdersAssignedToPartner);

            mappingmap.remove(orderId);
        }
    }
}
