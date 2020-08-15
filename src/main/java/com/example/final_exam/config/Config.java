package com.example.final_exam.config;

import com.example.final_exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter {

    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
      DataSource dataSource;
    @Autowired
    UserService userService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true");

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .invalidateHttpSession(true);

        http.authorizeRequests()
                .antMatchers("/profile","/createPost","/createComment")
                .authenticated();

        http.authorizeRequests()
                .anyRequest()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String fetchUsersQuery = "select email, password, enabled"
                + " from user"
                + " where email = ?";


        auth.jdbcAuthentication()
                .usersByUsernameQuery(fetchUsersQuery)
               .authoritiesByUsernameQuery("select email,role from user_role inner join\n" +
                       "user on user_role.user_id = user.user_id inner join role on user_role.role_id = role.role_id where email=?")
                .dataSource(dataSource);
    }


}