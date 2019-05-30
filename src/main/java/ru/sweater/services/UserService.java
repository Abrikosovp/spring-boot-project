package ru.sweater.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sweater.repository.UserRepos;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepos userRepos;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepos.findByUsername(name);
    }
}
