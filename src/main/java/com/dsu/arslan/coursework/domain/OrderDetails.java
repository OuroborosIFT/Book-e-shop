package com.dsu.arslan.coursework.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "orders_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Column
    private Long amount;
    @Column
    private Double price;

    public OrderDetails(Order order, Book book, Long amount) {
        this.order = order;
        this.book = book;
        this.amount = amount;
        this.price = book.getPrice();
    }
}
