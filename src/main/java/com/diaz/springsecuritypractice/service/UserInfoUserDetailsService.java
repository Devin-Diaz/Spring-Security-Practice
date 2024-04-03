package com.diaz.springsecuritypractice.service;

import com.diaz.springsecuritypractice.entity.UserInfo;
import com.diaz.springsecuritypractice.repository.UserInfoRepository;
import com.diaz.springsecuritypractice.security.UserInfoUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/*
    @Component - when a class is annotated with this, it becomes a candidate for auto-detection when using annotation
    based configuration and classpath scanning. Spring can automatically discover and register your component as a bean
    making it available for dependency injection and other Spring features. In our case we are injecting our UserInfo
    repository interface for use with UserDetails interface.

    loadUserByUsername() - a method from UserDetailsService interface, this method has a parameter of a String
    "username" and load that user from the database. In our case we will retrieve the user from our database via
    JPA. However, for our model, we don't have a username field, so we can customize this. We will load the user by
    first name, we can do this be defining a custom method in our JPA Repository class for UserInfo that allows us
    to find the user by first name since it's an existing field in our UserInfo entity.

    UserDetails Interface - an interface containing the fields, getPassword(), getUserName(), isAccountNonExpired(),
    isAccountNonLocked(), isCredentialNonExpired(), and isEnabled(). It also contains the roles/authorities upon
    retrieving and analyzing an entity returned.

*/

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByName(username);

        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() ->  new UsernameNotFoundException("USER NOT FOUND!"));
    }
}
