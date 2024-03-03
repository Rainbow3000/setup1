package com.example.EcommerceSpringBootProject.security;

import com.example.EcommerceSpringBootProject.entity.User;
import com.example.EcommerceSpringBootProject.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class WebSecurityConfig {

    @Autowired
    private IUserService userService;
    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.headers().frameOptions().disable().and().cors().and().csrf().disable();
            httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            httpSecurity.authorizeHttpRequests(request->{
                request.requestMatchers(
                                new AntPathRequestMatcher("/api/v1/product/**",HttpMethod.GET.toString()),
                                new AntPathRequestMatcher("/api/v1/order/**",HttpMethod.GET.toString()),
                                new AntPathRequestMatcher("/api/v1/category/**",HttpMethod.GET.toString()),
                                new AntPathRequestMatcher("/api/v1/product/comment/**",HttpMethod.GET.toString()),
                                new AntPathRequestMatcher("/api/v1/auth/**")
                        ).permitAll()
                        .anyRequest().authenticated();
            });

            httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
            return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                        User user = userService.findByEmail(username);
                        if(user == null){
                            throw new UsernameNotFoundException("user not exists!");
                        }
                        return user;
            }
        };
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
