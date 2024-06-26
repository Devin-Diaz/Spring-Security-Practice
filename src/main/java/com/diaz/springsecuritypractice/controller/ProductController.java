package com.diaz.springsecuritypractice.controller;
import com.diaz.springsecuritypractice.dto.Product;
import com.diaz.springsecuritypractice.entity.UserInfo;
import com.diaz.springsecuritypractice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
@RestController - specialized version of @Controller, used at class level and marks the class where every method
returns a domain object (model) instead of view (UI). The domain object contains business logic and usually persists
to a database, meanwhile a view is typically all for show with no business logic. RestController is an essential
annotation for RESTful web services.

RESTful web services - a web-based interface that uses HTTP to allow applications to communicate with each other in a
stateless, reliable, and scalable manner. It operates on resources each uniquely identified via URIs, and uses
standard HTTP methods such as GET, POST, PUT, DELETE to create, read, update and delete these resources. RESTful's
services are designed based on principles of simplicity, performance, scalability, and internet standards making ideal
for API building.

@RequestMapping - used to map web requests to Spring Controller methods. It can be applied at the class or method
level in a controller class. RequestMapping can be used to map HTTP requests to handler methods of MVC and REST
controllers. You can specify the URL pattern that the method or class will handle. You can specify HTTP request.
Allows specifying request parameters and headers that the mapped method accepts.

@PathVariable - allows us to customize the input in our url path. In this case we used it so we could target a
specific products' data. Usually our mapping requests are a set name, however with PathVariable it allows us to
target specific models assuming they exist in our data. Another example would be an account on LinkedIn. Our
profile there has it's designated url with our username at the end. In this case our username would be the path
variable. EG: http://linkedin.com/diazdevin  {diazdevin} -> PathVariable. The data returned would be my profile
specifically.

@PreAuthorize() - specifies what permissions (roles) an authenticated user needs to access the endpoint
associated with the function annotated with PreAuthorize. However, this won't happen automatically, we must
specify to Spring Security what we are trying to do on a method level. Must enable @EnableMethodSecurity annotation
on Security Config class.

@RequestBody - Facilitates the easy creation of RESTful services by enabling automatic mapping request bodies onto
Java objects, steamlining the process of data injection from clients to your server-side application logic. When a
client sends a request to a server, the HTTP request can contain a body. Essentially on our request we would input data
in a (JSON or XML) format, and Spring uses HTTP message converters to convert that body of data into a Java object.
We would use a software like Postman to do this.

E.g:
    JSON {
        name: devin
        passwd: dab
        roles: admin
    }
    ===
    User user = new User("Devin", "dab", admin)

*/

@RestController
@RequestMapping("/products")
public class ProductController {

    // field injection not ideal but since this is practice we won't utilize constructor based injection
    @Autowired
    private ProductService service;

    // localhost:8080/products/welcome -> this in browser will display method GET request
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome, this endpoint isn't secure";
    }

    // POST HTTP request bc/ we are adding new data "ordering from the menu"
    // method that persists new users data to the DB and will store hashed password
    // note that we want ALL users to reach this endpoint, no authentication is needed, so we configure in filter chain
    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUserInfo(userInfo);
    }

    // return all random objects created in service class in JSON format
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public List<Product> getAllTheProducts() {
        return service.getAllProducts();
    }

    // allows us to target a specific project and return it's JSON data
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }

}
