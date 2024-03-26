package com.diaz.springsecuritypractice.dto;
import lombok.*;

/*
@Data - this annotation from lombok provides us with encapsulation, that being getters, setters, toString, equals(),
and hashcode() methods.

equals() and hashcode() - equals() is a boolean method that returns whether and object is equal to another. hashcode()
returns an integer (hash) that helps us find an object faster. hashing is utilized by HashMaps thus the O(1) TC.


*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private int productId;
    private String productName;
    private int quantity;
    private double price;


}
