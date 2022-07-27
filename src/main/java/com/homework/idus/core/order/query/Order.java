package com.homework.idus.core.order.query;

import com.homework.idus.core.user.command.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String orderNo;

    @Column(nullable = false, length = 100)
    private String productName;

    @Column(nullable = false, columnDefinition="DATETIME(0) default CURRENT_TIMESTAMP")
    private LocalDateTime orderAt;

    public Order() {}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_no")
    private User user;

    @Builder
    public Order(String orderNo,
                 String productName,
                 LocalDateTime orderAt,
                 User user) {
        this.orderNo = orderNo;
        this.productName = productName;
        this.orderAt = orderAt;
        this.user = user;
    }
}
