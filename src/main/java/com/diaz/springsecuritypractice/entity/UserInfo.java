package com.diaz.springsecuritypractice.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    @Entity - used to indicate that a class is an entity in the context of persistence, derives from JPA. This
    annotation tells JPA provider that the class should be mapped to a table in a database. Additionally, an entity
    class should have an @Id annotation, this is a primary key that maps to a primary key column in DB table.

    ORM - Object-Relational Mapping is a technique used to convert data between incompatible systems. In Spring Boot
    and JPA, ORM allows developers to interact with a relational database using high-level OOP concepts instead of
    direct queries. This abstraction makes it easy to work w/ the data layer of apps and improving developer speed.

    @GeneratedValue - in the context of JPA, it's used to specify the generation strategy for the values of primary
    keys. In the case of GenerationType.IDENTITY, this means that the persistence provider must assign primary keys
    for the entity using a database identity column. This is a type of column that automatically generate unique
    values for each row when new rows are inserted.
*/

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private String roles;

}
