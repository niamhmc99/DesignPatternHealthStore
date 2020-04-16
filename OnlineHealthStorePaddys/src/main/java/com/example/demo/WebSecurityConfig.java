package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;
	
	   @Bean
	    public AuthenticationManager customAuthenticationManager() throws Exception {
	        return authenticationManager();
	    }
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/resources/**", "/registration", "/welcome", "/getAllItems" ).permitAll()
        		.antMatchers("/profile").hasAnyRole("ROLE_ADMIN, ROLE_USER")
        		.antMatchers("/orderList","/order", "/accountInfo", "/addItem").access("hasAnyRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .and()
            .exceptionHandling()
            	.accessDeniedPage("/error");       

        // The pages requires login as ADMIN.
        // If no login, it will redirect to /login page.
//        http.authorizeRequests().antMatchers("/orderList","/order", "/accountInfo")//
//                .access("hasAnyRole('ROLE_ADMIN')");
        // .antMatchers("/getAllItems").access("hasAnyRole('ROLE_USER')")

        // For ADMIN only.
      //  http.authorizeRequests().antMatchers("/item").access("hasRole('ROLE_ADMIN')");
  
        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will throw.
       // http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/error");
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
