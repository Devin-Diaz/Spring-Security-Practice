package repository;

import com.diaz.springsecuritypractice.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


/*
    Spring Data JPA repositories abstract away boilerplate CRUD operations that would need to be implemented manually.
    By extending JpaRepository<Type, Type>, we automatically inherit functions for saving, finding, updating, and
    deleting entities. This means we can perform these opertions without writing SQL or JPA queries ourselves unless we
    wanted very specific queries. We can @Autowire this interface into our business / controller logic to
    begin manipulating data in our database.
*/



public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

}
