package com.pms.PMS.Config;

import com.pms.PMS.Entity.UserDtls;
import com.pms.PMS.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDtlsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            UserDtls u = repo.findByUsername(username);

            if(u==null)
            {
                throw new UsernameNotFoundException("No User");
            }
            else {
                return new CustomUserDtls(u);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
