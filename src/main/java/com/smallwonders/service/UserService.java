package com.smallwonders.service;

import com.smallwonders.model.auth.Role;
import com.smallwonders.model.auth.SignupForm;
import com.smallwonders.model.auth.User;
import com.smallwonders.repository.RoleRepository;
import com.smallwonders.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rucsac on 15/10/2018.
 */
@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;


    @Autowired
    private RoleRepository roleRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SecurityContextService context;


    @Transactional(propagation = Propagation.REQUIRED)
    public User signup(SignupForm signupForm) {
        Role role = this.roleRepo.findByName(signupForm.getRoleName());

        final User user = new User();
        user.setEmail(signupForm.getEmail());
        user.setUsername(signupForm.getUsername());
        if (signupForm.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
        }

        user.setRole(role);

        repository.save(user);


        return user;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public User save(User user) {
        repository.save(user);
        return user;
    }


    // Will be called after form based authentication to fetch user details
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = repository.findByUsername(email).orElseThrow(() -> new UsernameNotFoundException("No user found with email: " + email));
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority("LOGGED_USER"));
        //new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities)
        return user;


    }


    public User findUserByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    public User findByRole_Name(String role) {
        return repository.findByRole_Name(role).get(0);
    }


}
