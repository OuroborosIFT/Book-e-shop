package com.dsu.arslan.coursework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDTO {

    private int amountBooks;
    private Double sum;
    private List<BucketDetailsDTO> bucketDetailsDTOS = new ArrayList<>();

    public void aggregate() {
        this.amountBooks = bucketDetailsDTOS.size();
        this.sum = bucketDetailsDTOS.stream()
                .map(BucketDetailsDTO::getSum)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

}
