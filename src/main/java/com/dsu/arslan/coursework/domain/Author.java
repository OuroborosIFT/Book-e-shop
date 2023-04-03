package com.dsu.arslan.coursework.domain;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
//    private final String SEQ_NAME = "author_seq";
//
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
//    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private String patronymic;
    @Column
    @Type(type = "text")
    private String portrait;
    @Column
    @Type(type = "text")
    private String about;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private List<Book> books;

    /*@ManyToMany (cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "author_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> bookList;*/

}
