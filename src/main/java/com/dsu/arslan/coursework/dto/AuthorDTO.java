package com.dsu.arslan.coursework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorDTO {

    private Long id;
    private String name;
    private String lastName;
    private String patronymic;
    private String portrait;
    private String about;

}
