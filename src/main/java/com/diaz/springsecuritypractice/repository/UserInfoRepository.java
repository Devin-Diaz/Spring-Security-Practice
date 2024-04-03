package com.diaz.springsecuritypractice.repository;

import com.diaz.springsecuritypractice.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/*
    Spring Data JPA repositories abstract away boilerplate CRUD operations that would need to be implemented manually.
    By extending JpaRepository<Type, Type>, we automatically inherit functions for saving, finding, updating, and
    deleting entities. This means we can perform these operations without writing SQL or JPA queries ourselves unless we
    wanted very specific queries. We can @Autowire this interface into our business / controller logic to
    begin manipulating data in our database.

    Optional<Type> - is a container object used to contain not-null objects. Using this is preferable to returning
    'null' for not found values, as it avoids the risk of NullPointerException and makes the code that calls the
    method deal explicitly with the possibility that there might not be a user with the given username
*/






public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    // Custom JPA method that will be used in our custom UserDetailsService interface to load user from database
    Optional<UserInfo> findByName(String username);
}
