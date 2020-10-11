package org.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.xml.ws.soap.Addressing;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService(){
//        UserDetails annaSmithUser = User.builder()
//                .username("annasmith")
//                .password("password") // will throw exception because no password encoder here
//                .roles("STUDENT") //ROLE_STUDENT
//                .build();
//        return new InMemoryUserDetailsManager(
//                annaSmithUser
//        );
//    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails annaSmithUser = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("password")) // passwordEncoder done!
                .roles("STUDENT") //ROLE_STUDENT
                .build();
        return new InMemoryUserDetailsManager(
                annaSmithUser
        );
    }
}
