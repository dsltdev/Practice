package com.practice.pizzeria.service;

import com.practice.pizzeria.persistence.entity.UserEntity;
import com.practice.pizzeria.persistence.entity.UserRoleEntity;
import com.practice.pizzeria.persistence.repository.UserRepository;
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
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;


    @Autowired
    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findById(username).orElseThrow(()->new UsernameNotFoundException("User: " + username));

        String [] roles = userEntity.getRoles().stream().map(UserRoleEntity::getRole).toArray(String[]::new);



        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(grantedAuthorities(roles))
                .accountLocked(userEntity.getLocked())
                .disabled(userEntity.getDisable())
                .build();
    }

    private String[] getAuthorities(String role) {
        if("ADMIN".equals(role) || "CUSTOMER".equals(role)){
            return new String[] {"ramdom_order"};
        }
        return new String[] {};
    }

    private List<GrantedAuthority> grantedAuthorities (String[] roles){
        List<GrantedAuthority> autorities = new ArrayList<>(roles.length);
        for(String role : roles){
            autorities.add(new SimpleGrantedAuthority("ROLE_" + role));

            for (String authority : this.getAuthorities(role)) {
                autorities.add(new SimpleGrantedAuthority(authority));
            }
        }
        return autorities;
    }
}
