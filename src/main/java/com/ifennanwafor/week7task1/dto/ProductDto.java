package com.ifennanwafor.week7task1.dto;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String price;
    private String description;
    private String category;
    private String imageURL;
}
