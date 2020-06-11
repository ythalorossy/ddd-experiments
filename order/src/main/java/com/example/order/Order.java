package com.example.order;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder(toBuilder = true)
@SequenceGenerator(name = "orders_seq_gen", allocationSize = 1, sequenceName = "orders_seq")
public class Order implements Serializable {
    
    private static final long serialVersionUID = -5684868898790491130L;
    
    public enum Status {
        CREATED, TO_PAY, PAYING, PAID, COOKING, COOKED, TO_DELIVER, DELIVERING, DELIVERED
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "order_seq_gen")
    private Long id;
    @Column(name = "description", nullable = false)
    private String description; 
    @Column(name = "create_at")
    private LocalDate createdAt;
    @Column(name = "updated_at")
    private LocalDate updatedAt;
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;
}