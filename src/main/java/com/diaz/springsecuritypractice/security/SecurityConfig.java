package com.diaz.springsecuritypractice.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
*/
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    //Password hashing
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    //Authentication
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("Devin")
                .password(encoder.encode("Olivia_rodrigo7!"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("Isagi")
                .password(encoder.encode("Im_simply_better10!"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    //Chain of responsibility
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return null;
    }

}
