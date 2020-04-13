package ro.utcluj.assignment1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/user", "/user/patients", "/doctor", "/medication", "/medicationPlan").hasAuthority("Doctor")
                .antMatchers("/user/caregiver/patients/{id}").hasAuthority("Caregiver")
                .antMatchers("/user/all","/user/update","/user/delete").hasAuthority("ADMIN")
                .antMatchers("/patient").authenticated();
    }
}
