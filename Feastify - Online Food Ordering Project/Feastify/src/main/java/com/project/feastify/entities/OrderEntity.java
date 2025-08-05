package com.project.feastify.entities;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity extends BaseEntity {

   // private Long userId; //
	@ManyToOne
	@JoinColumn(name = "user_id")
    private UserEntity user;
    private String userAddress;
    private String phoneNumber;
    private String email;

    @ElementCollection
    @CollectionTable(name = "ordered_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> orderedItems;

    private double amount;
    private String paymentStatus;
    private String razorpayOrderId;
    private String razorpaySignature;
    private String razorpayPaymentId;
    private String orderStatus;
}
