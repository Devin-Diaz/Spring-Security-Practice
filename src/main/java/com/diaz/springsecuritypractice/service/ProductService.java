package com.diaz.springsecuritypractice.service;
import com.diaz.springsecuritypractice.dto.Product;
import com.diaz.springsecuritypractice.entity.UserInfo;
import com.diaz.springsecuritypractice.repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/*
    The @PostConstruct annotation essentially is the bridge from a powered on machine, to a powered on machine with
    all features available. @PostConstruct initializes logic before a bean is fully constructed. If we use a coffee
    machine analogy, once the machine is powered on, we must initialize default settings before we begin brewing
    coffee. In this case @PostConstruct is the initialization of settings before brewing coffee. A function
    annotated with @PostConstruct will execute after a bean's construction and dependency injection are complete.

    With a refreshed brain @PostConstruct ensures that the spring application is completely built and running
    before it executes the orders in the function it's annotated with. We want all features of the application
    ready for use before doing the action.
*/

@Service
public class ProductService {

    List<Product> product_list = null;

    //injected dependencies to persist data and hash passwords
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder encoder;

    //Create 99 instances of product object with ordered numbering and random qty and prices
    //We used Java streaming to do this however it can be done with a traditional for loop as well.
    @PostConstruct
    public void loadProductsFromDb() {
        product_list = IntStream.range(1, 100)
                .mapToObj( i -> Product.builder()
                        .productId(i)
                        .productName("product " + i)
                        .quantity(new Random().nextInt(10))
                        .price(new Random().nextInt(5000))
                        .build()
                ).collect(Collectors.toList());
    }

    // returns list of products
    public List<Product> getAllProducts() {
        return product_list;
    }

    // returns specific product given id
    public Product getProduct(int id) {
        return product_list.stream()
                .filter(product -> product.getProductId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Product with id: " + id + " not found"));
    }

    // method that hashes and persists data to our DB
    public String addUserInfo(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "user added to the system";
    }

}
