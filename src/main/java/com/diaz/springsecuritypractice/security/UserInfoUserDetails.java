package com.diaz.springsecuritypractice.security;
import com.diaz.springsecuritypractice.entity.UserInfo;
import com.diaz.springsecuritypractice.service.UserInfoUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
    This is very correlated with our UserInfoUserDetailsService class. Now we are working on the customization of the
    UserDetails interface. This is the type that we are going to return in our loadUserByUsername() method from the
    UserDetailsService interface, so we can authenticate users and their roles via persistence data from DB.

    SimpleGrantedAuthority -  objects are used because they provide a simple, straightforward way to represent user
    roles in a manner that is fully compatible with Spring Security's authorization mechanisms.
*/


public class UserInfoUserDetails implements UserDetails {

    /*
        Constructor and fields, when we loadUserByUsername, this is the UserDetails objects that we will return,
        the data in this class is what Spring Security will recognize upon retrieving an entity from database
    */
    private String name;
    private String password;
    private List<GrantedAuthority> authorityList;

    public UserInfoUserDetails(UserInfo userInfo) {
        this.name = userInfo.getName();
        this.password = userInfo.getPassword();

        /* converts a comma-separated String of roles into a List of SimpleGrantedAuthority objects,
             which can be used in Spring Security for authorization purposes. Used for users w/ one or
             multiple roles.*/
        this.authorityList = Arrays.stream(userInfo.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
