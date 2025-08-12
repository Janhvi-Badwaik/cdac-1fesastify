package com.project.feastify.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

import com.project.feastify.entities.OrderItem;

@Data
@Builder
public class OrderRequest extends BaseDTO {
    private List<OrderItem> orderedItems;
    private String userAddress;
    private double amount;
    private String email;
    private String phoneNumber;
    private String orderStatus;

}
