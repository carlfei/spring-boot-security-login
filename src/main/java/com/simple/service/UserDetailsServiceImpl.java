package com.simple.service;

import com.simple.model.Users;
import com.simple.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    List<Users> usersList;
    @Autowired
    UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        if("user".equals(userName)){
            return this.userBuilder(userName, new BCryptPasswordEncoder().encode("1234"),"USER");
        } else if ("manager".equals(userName)) {
            return this.userBuilder(userName, new BCryptPasswordEncoder().encode("1234"),"MANAGER");
        }else if ("admin".equals(userName)) {
            return this.userBuilder(userName, new BCryptPasswordEncoder().encode("1234"),"USER", "MANAGER","ADMIN");
        }else {
            usersList = usersRepository.findAll();

            for(Users userVar:usersList){
                if(userVar.getName().equals(userName)){
                 return this.userBuilder(userName, new BCryptPasswordEncoder().encode(userVar.getPassword()),"USER");
             }
         }
        }
        throw new UsernameNotFoundException("user does not exist");
    }
    private User userBuilder(String userName, String password, String ... rol){
        boolean accountNonExpired = true;
        boolean enable = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String role: rol){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
        }

        return new User(userName, password, enable, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
    }
}
