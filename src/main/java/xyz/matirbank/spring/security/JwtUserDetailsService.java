package xyz.matirbank.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.matirbank.spring.models.entities.StandardUsers;
import xyz.matirbank.spring.repositories.StandardUserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    StandardUserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        StandardUsers user = userRepository.findUserByHash(string);
        if(user != null) {
            return user.toUserDetails();
        }else{
            throw new UsernameNotFoundException("");
        }
    }
    
}
