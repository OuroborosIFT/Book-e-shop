package com.dsu.arslan.coursework.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private final String SEQ_NAME = "order_seq";

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    @Column
    @CreationTimestamp
    private LocalDateTime created;
    @Column
    @CreationTimestamp
    private LocalDateTime updated;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private Double sum;
    @Column
    private String address;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderDetails> detailsList;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
