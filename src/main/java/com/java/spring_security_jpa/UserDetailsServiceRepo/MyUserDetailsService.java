package com.java.spring_security_jpa.UserDetailsServiceRepo;

import com.java.spring_security_jpa.MySqlRepo;
import com.java.spring_security_jpa.MyUserDetails;
import com.java.spring_security_jpa.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private MySqlRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //convert jpa entity object to userdetails object
        Optional<User> user1 = repo.findByUserName(username);
        user1.orElseThrow(()->new UsernameNotFoundException("Not Found" + username));
        return user1.map(MyUserDetails::new).get();
    }
}
