package byAJ.Securex.configs;

import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	
	private AuthenticationProvider authenticationProvider;
	
	
	@Autowired 
	@Qualifier("daoAuthenticationProvider")
	
	public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
		this.authenticationProvider = authenticationProvider;
	}
	
	@Bean
	
	public PasswordEncoder passwordEncoder(StrongPasswordEncryptor passwordEncryptor) {
		PasswordEncoder passwordEncoder = new PasswordEncoder();
		passwordEncoder.setPasswordEncryptor(passwordEncryptor);
		return passwordEncoder;
	}
	
	@Bean 
	
	public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder, 
																			UserDetailsService userDetailsService ){
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}
	
	@Autowired
	public void configureAuthManager(AuthenticationManagerBuilder authenticationManagerBuilder) {
		authenticationManagerBuilder.authenticationProvider(authenticationProvider);
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests()
        		.antMatchers("/").permitAll()
        		.antMatchers("/books/edit/**").hasRole("ADMIN")
        		.anyRequest().authenticated()
        	.and()
        		.formLogin().loginPage("/login").permitAll()
        	.and()
        		.logout()
        		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        		.logoutSuccessUrl("/login").permitAll()
        	.and()
        		.httpBasic();

    }
    
    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
    	auth.inMemoryAuthentication().
    	withUser("user").password("password").roles("USER");
    	auth.inMemoryAuthentication().withUser("dave").password("begreat").roles("USER");
    	auth.inMemoryAuthentication().withUser("fi").password("becold").roles("USER");
    }



}
