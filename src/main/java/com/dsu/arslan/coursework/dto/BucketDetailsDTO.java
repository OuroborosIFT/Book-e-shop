/**
 * Содержит информацию о книгах которую выводят на старанице bucket.html
 * */

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

    private Long bookId;
    private String title;
    private Double price;
    private Integer amount;
    private Double sum;

    public BucketDetailsDTO(Book book) {
        this.bookId = book.getId();
        this.title = book.getTitle();
        this.price = book.getPrice();
        this.amount = 1;
        this.sum = book.getPrice();
    }

}
