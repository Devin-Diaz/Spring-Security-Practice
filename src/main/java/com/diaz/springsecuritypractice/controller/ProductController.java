package com.diaz.springsecuritypractice.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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
*/



@RestController
@RequestMapping("/products")
public class ProductController {


    // localhost:8080/products/welcome -> this in browser will display method GET request
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome, this endpoint isn't secure";
    }


}
