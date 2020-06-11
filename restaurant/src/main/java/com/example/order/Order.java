package com.example.order;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder(toBuilder = true)
public class Order implements Serializable {
    
    private static final long serialVersionUID = -5684868898790491130L;
    
    public enum Status {
        CREATED, TO_PAY, PAYING, PAID, COOKING, COOKED, TO_DELIVER, DELIVERING, DELIVERED
    }

    private Long id;
    private String description; 
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Status status;
}