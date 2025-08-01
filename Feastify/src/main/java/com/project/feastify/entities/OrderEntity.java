package com.project.feastify.entities;

import java.util.List;

import com.project.feastify.dto.OrderItem;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
@Builder
public class OrderEntity extends BaseEntity 
{
	private Long userId;
	private String  userAddress;
	private String phoneNumber;
	private String email;
	private List<OrderItem> orderItems;
	private double amount;
	private String paymentStatus;
	private String razorpayOrderId;
	private String razorpaySignature;
	private String orderStatus;
}
