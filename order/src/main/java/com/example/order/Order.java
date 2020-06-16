package com.example.order;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@SequenceGenerator(name = "orders_seq_gen", allocationSize = 1, sequenceName = "orders_seq")
public class Order implements Serializable {

    private static final long serialVersionUID = -5684868898790491130L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "order_seq_gen")
    private Long id;

    @Column(name = "plate", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    @NotNull
    private Set<String> plates;

    @Column(name = "drink", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    @NotNull
    private Set<String> drinks;
    
    @Column(name = "create_at")
    private LocalDate createdAt;
    
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "order_type")
    @Enumerated(EnumType.STRING)
    private OrderType type;

    public static Order createOrder(String[] plates, String[] drinks, OrderType orderType) {
        return Order.builder()
                    .plates(new HashSet<String>(Arrays.asList(plates)))
                    .drinks(new HashSet<String>(Arrays.asList(drinks)))
                    .createdAt(LocalDate.now())
                    .updatedAt(LocalDate.now())
                    .type(orderType)
                    .status(OrderStatus.CREATED)
                    .build();
    }

}