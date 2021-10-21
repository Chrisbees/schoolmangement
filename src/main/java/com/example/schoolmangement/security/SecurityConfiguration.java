//package com.example.schoolmangement.security;
//
//
//import com.example.schoolmangement.jwt.JwtConfig;
//import com.example.schoolmangement.service.StaffServiceImpl;
//import com.example.schoolmangement.service.ParentServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//
//import javax.crypto.SecretKey;
////
////import static com.example.schoolmangement.security.UserRoles.ADMIN;
//import static com.example.schoolmangement.security.UserRolePermission.STAFF_WRITE;
//import static com.example.schoolmangement.security.UserRolePermission.STUDENT_WRITE;
//import static com.example.schoolmangement.security.UserRoles.*;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    //
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
////                .csrf().disable()
//             .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
//                .authorizeRequests()
////                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                //.and()
////                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
////                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
//                .antMatchers("/", "index", "/css/**", "/js/**", "/home/**", "/parents/**", "/student/**")
//                .permitAll().antMatchers("/school/**").hasRole("ADMIN")
//                .anyRequest()
//                .authenticated();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService());
//    }
//
//}
