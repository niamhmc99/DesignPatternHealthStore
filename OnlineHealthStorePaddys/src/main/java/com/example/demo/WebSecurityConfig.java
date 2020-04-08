package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Order(1)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/resources/**", "/registration").permitAll()
                .antMatchers("/").permitAll()
        		.antMatchers("/admin").hasAnyRole("USER,ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }
    
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.antMatchers("/register", "/homepage", "/about", "/login", "/css/**", "/webjars/**").permitAll()
//		.antMatchers("/Admin").hasAnyRole("USER,ADMIN")
//		//.antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated()
////		.antMatchers("/css/**").permitAll()
////		.antMatchers("/Admin").hasAuthority("ADMIN").anyRequest().authenticated()
//		.and().csrf().disable()
//		.formLogin().loginPage("/login").failureUrl("/login?error=true")
//		.defaultSuccessUrl("/homepage")
//		.usernameParameter("email")
//		.passwordParameter("password");
////		.and().logout()
////		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////		.logoutSuccessUrl("/")
////		.and().rememberMe()
////		.tokenRepository(persistentTokenRepository())
////		.tokenValiditySeconds(60 * 60)
////		.and().exceptionHandling().accessDeniedPage("/access_denied");
//	}

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.
//        jdbcAuthentication()
//        .usersByUsernameQuery(usersQuery)
//        .authoritiesByUsernameQuery(rolesQuery)
//        .dataSource(dataSource)
//        .passwordEncoder(bCryptPasswordEncoder());
//
//// In memory authentication
//auth.inMemoryAuthentication()
//        .withUser(adminUsername).password(adminPassword).roles("ADMIN");
//        //auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//    }
}
