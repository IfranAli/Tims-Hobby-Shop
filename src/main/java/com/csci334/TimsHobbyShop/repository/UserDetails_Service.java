package com.csci334.TimsHobbyShop.repository;

import com.csci334.TimsHobbyShop.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetails_Service implements UserDetailsService {
    @Autowired
    private Person_Repository personRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person p = personRepository.findByUsername(s);

        if (p != null) {
            User user = new org.springframework.security.core.userdetails.User(
                    p.getUsername(), p.getPassword(), getGrantedAuthorities("ADMIN"));

            return user;
        } else {
            User user = new org.springframework.security.core.userdetails.User(
                    "ANONYMOUS", "ANONYMOUSPASS", getGrantedAuthorities("USER"));
            return user;
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<String> privileges = new ArrayList<>();
        if (role.equals("USER")) {
            privileges.add("USER");
        }
        if (role.equals("CUSTOMER")) {
            privileges.add("CUSTOMER");
        }
        if (role.equals("ADMIN")) {
            privileges.add("ADMIN");
            privileges.add("ADMIN");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
