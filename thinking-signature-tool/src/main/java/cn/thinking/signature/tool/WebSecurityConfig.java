package cn.thinking.signature.tool;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * username:user
     * password:password(bcrypt加密)
     *
     * @return
     */
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{bcrypt}$2a$10$WwlKxTjkgZdb7OmlB4vyjewvvsJCkV.H72iu3NDgJry2wx8jgKram")
                .roles("USER")
                .build();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(user);
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/**", "/login")
                .permitAll()
                .and()
                .formLogin()
                //.loginPage("/login")
                .defaultSuccessUrl("/main")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/health-check.html")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/signature")
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}

