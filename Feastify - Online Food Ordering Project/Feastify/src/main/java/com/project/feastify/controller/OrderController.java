package com.project.feastify.controller;

import com.project.feastify.dto.ApiResponse;
import com.project.feastify.dto.OrderRequest;
import com.project.feastify.dto.OrderResponse;
import com.project.feastify.service.OrderService;
import com.razorpay.RazorpayException;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrderWithPayment(@RequestBody OrderRequest request) {
        try {
            OrderResponse response = orderService.createOrderWithPayment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RazorpayException e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body(new ApiResponse("Payment failed: " + e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse("Error: " + e.getMessage()));
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String, String> paymentData) {
        try {
            orderService.verifyPayment(paymentData, "Paid");
            return ResponseEntity.ok(new ApiResponse("Payment verified successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("Verification failed: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getOrders() {
        List<OrderResponse> list = orderService.getUserOrders();
        if (list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        orderService.removeOrder(orderId);
        return ResponseEntity.ok(new ApiResponse("Order deleted successfully"));
    }

    @GetMapping("/all")
    public List<OrderResponse> getOrdersOfAllUsers() {
        return orderService.getOrdersOfAllUsers();
    }

    @PatchMapping("/status/{orderId}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(new ApiResponse("Order status updated"));
    }
}
