package com.github.karixdev.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order {
    @Id
    @Column(
            name = "id",
            nullable = false,
            updatable = false
    )
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    @Column(
            name = "product_id",
            nullable = false
    )
    private Long productId;

    @Column(
            name = "has_been_sent",
            nullable = false
    )
    private Boolean hasBeenSent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(email, order.email) &&
                Objects.equals(productId, order.productId) &&
                Objects.equals(hasBeenSent, order.hasBeenSent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, productId, hasBeenSent);
    }
}
