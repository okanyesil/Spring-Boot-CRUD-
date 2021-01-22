package com.okanyesil.springBootmysql.model;



import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NewTodoDTO {
    private String title;
    private String description;
    private Boolean status;
}
