package com.project.feastify.service;

import java.util.List;
import java.util.Map;

import com.project.feastify.dto.OrderRequest;
import com.project.feastify.dto.OrderResponse;
import com.razorpay.RazorpayException;

public interface OrderService 
{
    OrderResponse createOrderWithPayment(OrderRequest request) throws RazorpayException;

    void verifyPayment(Map<String, String> paymentData, String status);
    
    List<OrderResponse> getUserOrders();
    
    void removeOrder(Long orderId);
    
    List<OrderResponse> getOrdersOfAllUsers();
    
    void updateOrderStatus(Long orderId, String status);

    
}
