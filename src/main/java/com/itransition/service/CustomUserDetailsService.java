//package com.itransition.service;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.itransition.model.User;
//import com.itransition.repository.UserRepository;
//
//public class CustomUserDetailsService implements UserDetailsService {
//
//	private final UserRepository userRepository;
//
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//    
//	@Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
//
//        if (Boolean.TRUE.equals(user.getBlocked())) {
//            throw new UsernameNotFoundException("User is blocked: " + email);
//        }
//
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getEmail())
//                .password(user.getPassword())
//                .roles("USER")
//                .build();
//    }
//
//}
