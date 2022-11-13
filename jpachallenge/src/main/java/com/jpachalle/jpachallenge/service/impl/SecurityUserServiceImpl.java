package com.jpachalle.jpachallenge.service.impl;

import com.jpachalle.jpachallenge.entity.User;
import com.jpachalle.jpachallenge.filter.provider.JwtTokenProvider;
import com.jpachalle.jpachallenge.model.SecurityUser;
import com.jpachalle.jpachallenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class SecurityUserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.print(username);
        User user = userRepository.findById(new BigInteger(username))
                .orElseThrow(() -> new Exception());

        return SecurityUser.builder().id(user.getId()).password(user.getPassword())
                .roles(Arrays.asList(user.getRole().getName().toString())).build();
    }
}
