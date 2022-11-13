package com.jpachalle.jpachallenge.controller;

import com.jpachalle.jpachallenge.dto.AuthenticationRequest;
import com.jpachalle.jpachallenge.dto.AuthenticationResponse;
import com.jpachalle.jpachallenge.entity.Role;
import com.jpachalle.jpachallenge.enums.RoleName;
import com.jpachalle.jpachallenge.filter.provider.JwtTokenProvider;
import com.jpachalle.jpachallenge.service.impl.SecurityUserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/me")
@RestController
public class UserController {

    private AuthenticationManager authenticationManagerBean;
    private JwtTokenProvider jwtTokenProvider;
    private SecurityUserServiceImpl securityUserService;

    public UserController(AuthenticationManager authenticationManagerBean, SecurityUserServiceImpl securityUserService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManagerBean = authenticationManagerBean;
        this.securityUserService = securityUserService;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
     // 로그인했으면, 아이디 존재 여부, 비밀번호 일치 여부 확인 이후  통과 하면 토큰 만들어주는 로직?


        List<String> roles = new ArrayList<>();
        for (RoleName type : RoleName.values()) {
            roles.add(type.getLabel());
        }
        // 아 이제 여기서 토큰 만들면 될것임.
        // 지금 상황에선 사용자 PK 가져와야 됨.
        final UserDetails userDetails = securityUserService.loadUserByUsername("1");
        final String jwt = jwtTokenProvider.createToken("1", roles);

        String username = userDetails.getUsername();
        String password = userDetails.getPassword();
        Collection<? extends GrantedAuthority> auth = userDetails.getAuthorities();
        log.info("username={}", username);
        log.info("password={}", password);
        log.info("auth={}", auth);
        Authentication test = jwtTokenProvider.getAuthentication(jwt);
        String test2 = jwtTokenProvider.getUserPk(jwt);
        AuthenticationResponse res = new AuthenticationResponse(jwt);

        log.info("응답입니다={}", res);
        log.info("토큰입니다={}", jwt);
        log.info("응답에서 토큰꺼내오기={}",res.getJwt());
        log.info("test={}", test);
        log.info("test2={}", test2);
        return new AuthenticationResponse(jwt);
    }

    @GetMapping("/test")
    public String testdd(){
        return "왜 안되는거?";
    }


}
