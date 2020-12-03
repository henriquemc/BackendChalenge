package itau.iti.backend.credentialsservice.adapters.web.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .anonymous()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/validate").permitAll()
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

}