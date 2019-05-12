package com.csci334.TimsHobbyShop.repository;

import com.csci334.TimsHobbyShop.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
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
			User user = new User(p.getUsername(), p.getPassword(),
					getGrantedAuthorities(p.getRole()));

            return user;
        } else {
            User user = new org.springframework.security.core.userdetails.User(
                    "ANONYMOUS", "ANONYMOUS", getGrantedAuthorities("USER"));
            return user;
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
		List<GrantedAuthority> perms = new ArrayList<>();
		perms.add(new SimpleGrantedAuthority(role));
		return perms;
    }
}
