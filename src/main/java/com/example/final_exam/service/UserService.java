package com.example.final_exam.service;


import com.example.final_exam.exception.CustomerAlreadyRegisteredException;
import com.example.final_exam.model.Role;
import com.example.final_exam.model.User;
import com.example.final_exam.repository.RoleRepository;
import com.example.final_exam.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

   private final PasswordEncoder encoder ;

   private final UserRepository userRepository;
   private final RoleRepository roleRepository;



    public void register(User user) throws CustomerAlreadyRegisteredException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new CustomerAlreadyRegisteredException();
        }
        Set<Role> roles=new HashSet<>();
        roles.add(roleRepository.findById(1).get());
        user.setRoles(roles);
         user.setPassword(encoder.encode(user.getPassword()));
         user.setEnabled(true);
        userRepository.save(user);
    }
    public User findByEmail(String email){
      return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;    }
}
