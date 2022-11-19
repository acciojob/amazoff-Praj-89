package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceLayer {

    @Autowired
    OrderRepository amazonRepository;
    public void addOrder(Order order) {

        amazonRepository.addOrder(order);

    }

    public void addPartner(String partnerId) {

        amazonRepository.addPartner(partnerId);
    }

    public List<String> getAllOrders() {
        return amazonRepository.getAllOrders();
    }

    public Integer getCountOfUnassignedOrders() {
        return amazonRepository.getCountOfUnassignedOrders();
    }


    public void partnerPairOrder(String orderId, String partnerId) {

        amazonRepository.partnerPairOrder(orderId, partnerId);
    }

    public Order getOrderById(String orderId) {

        return amazonRepository.getOrderById(orderId);

    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return amazonRepository.getPartnerById(partnerId);
    }

    public List<String> getAllOrdersAssignedToPartner(String partnerId) {
        return amazonRepository.getAllOrdersAssignedToPartner(partnerId);
    }
    public int getNumberOfOrderAssignedToPartner(String partnerId) {
        return amazonRepository.getNumberOfOrderAssignedToPartner(partnerId);
    }


    public void deleteOrderById(String orderId) {
        amazonRepository.deleteOrderById(orderId);
    }


    public void deletePartnerById(String partnerId) {
        amazonRepository.deletePartnerById(partnerId);
    }



}
