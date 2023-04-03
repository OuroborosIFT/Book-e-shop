package com.dsu.arslan.coursework.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "buckets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bucket {
    /*    private final String SEQ_NAME = "bucket_seq";

        @Id
        @Column
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
        @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)*/
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany
    @JoinTable(name = "bucket_books",
            joinColumns = @JoinColumn(name = "bucket_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

}
