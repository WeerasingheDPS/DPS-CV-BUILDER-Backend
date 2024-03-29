package dpscvbuilder.com.DPSCV_BUILDER.config.security;

import dpscvbuilder.com.DPSCV_BUILDER.model.User;
import dpscvbuilder.com.DPSCV_BUILDER.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(email);
        return user.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("user not found with email: "+ email));
    }
}
