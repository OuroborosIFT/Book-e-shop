package com.dsu.arslan.coursework.dto;

import com.dsu.arslan.coursework.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDetailsDTO {

    private String title;
    private Long bookId;
    private Double price;
    private Integer amount;
    private Double sum;

    public BucketDetailsDTO(Book book) {
        this.title = book.getTitle();
        this.bookId = getBookId();
        this.price = book.getPrice();
        this.amount = Integer.valueOf("1");
        this.sum = Double.valueOf(book.getPrice().toString());
    }

}
