package com.diaz.springsecuritypractice.dto;
import lombok.*;

/*
@Data - this annotation from lombok provides us with encapsulation, that being getters, setters, toString, equals(),
and hashcode() methods.

equals() and hashcode() - equals() is a boolean method that returns whether and object is equal to another. hashcode()
returns an integer (hash) that helps us find an object faster. hashing is utilized by HashMaps thus the O(1) TC.

@Builder allows you to make instances of a class within the class. An example would be the following:

Person person = Person.builder()
                      .name("John Doe")
                      .age(30)
                      .build()

Additionally comes with setter methods as well. @Builder simplifies the readability of method chaining, in this
case with setter methods. We set the values of Person w/ name: John Doe and age: 30. Usually another example of
method chaining is on our Spring Security Config class for future reference.
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
