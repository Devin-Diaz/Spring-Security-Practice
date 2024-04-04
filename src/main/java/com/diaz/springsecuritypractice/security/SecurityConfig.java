package com.diaz.springsecuritypractice.security;
import com.diaz.springsecuritypractice.service.UserInfoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
    The Spring IoC (inversion of control) container is a core component of Spring Framework. It
    provides a mechanism for dependency injection, which is a design pattern that allows for loose
    coupling between classes. The objects a bean depends on to perform its functions are automatically
    provided by the container, based on the configuration.

    Dependency Injection - Take the following analogy, you could manufacture hardware parts of a computer like
    CPU, RAM, hard drive etc, but it's much more efficient to select and assemble pre-made components.

    @Configuration - indicates that the class can be used by the Spring IoC container as a source of bean
    definitions. This means you can define beans related to security configs such as AuthenticationManager,
    UserDetailsService..

    @EnablesWebSecurity - enables Spring Security features in Spring application. Without this annotation all,
    custom configuration is ignored. This annotation is crucial for taking full control of application's security
    configuration in a declarative and programmable way using Spring Security.

    PasswordEncoder - an interface in Spring Security used for hashing passwords. Rather than storing plain text
    passwords, passwords are hashed, and the hashed value is stored

    BCryptPasswordEncoder - Specific impl of PasswordEncoder uses strong hashing function to hash passwords. The
    @Bean notation allows the Spring container manage the BCryptPasswordEncoder object.

    UserDetails - interface that provides core user information that spring Security needs to
    perform authentication and authorization processes. Implementations of this interface are used
    throughout the Spring Security framework to represent and manage user information.

    UserDetailsService - interface that loads user-specific data during the authentication process

    InMemoryUserDetailsManager - manages user details in memory. This makes it a straightforward and convenient option
    for managing user information without the need for external storage systems like databases.

    CSRF - Cross-Site-Request-Forgery - CSRF is an attack that tricks an authenticated user into executing unwanted
    actions on a web application. On by default in Spring Boot, however we may want to disable this for specific
    endpoints. Some examples would be for endpoints with safe HTTP requests such as GET, HEAD, OPTIONS, TRACE. These
    HTTP requests don't change the state of the application like a PUT request would where data is changed or updated
    which are more vulnerable to CSRF attacks.

    @EnableMethodSecurity - Allows us to use the @PreAuthorize annotation in our controller class on functions that
    handle endpoints. This allows us to restrict which users are allowed at certain endpoints via authentication.
    For when testing head back to the /login page to ensure you change to correct user because in memory the endpoint
    will recognize a failed login and stay at that point unless a new user manually logs in.

    DaoAuthenticationProvider - This is a specific type of AuthenticationProvider that uses a Data Access Object (DAO)
    to retrieve user details from your database (or another persistence mechanism).

*/
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    //Password hashing
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    //Custom implementation of authentication with UserInfo entities
    public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }

    @Bean
    //Chain of responsibility
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Configuration to specify security rules for URL access within the application

                        /* Grants public access to the welcome page, allowing anyone to access it
                           without needing to log in */
                        .requestMatchers("/products/welcome", "/products/new").permitAll()

                        /* Requires that users must be authenticated to access any other endpoints under the
                          '/products' path, except for the welcome page This ensures that for URLs matching
                          '/products/**', a user must have the appropriate authentication to access those resources */
                        .requestMatchers("/products/**").authenticated()
                )
                // will display default spring login page for users that need to be authenticated for certain endpoints
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    /* When a user tries to log in, this configuration ensures that, the application will use your UserDetailsService
    to fetch user details from the specified source (like a database).  this method is your way of telling Spring
    Security exactly how to authenticate users, including where to get user details and how to handle passwords. */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}

/*

    Hard coded implementation of user authentication and roles
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("Devin")
                .password(encoder.encode("passwd1"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("Isagi")
                .password(encoder.encode("passwd2"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

*/
