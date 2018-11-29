package pl.kwi.springboot.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.x509.X509AuthenticationFilter;

import pl.kwi.springboot.filters.X509Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private X509Filter filter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
   	
    	http.authorizeRequests()
    		.antMatchers("/**").hasRole("USER")
        .and()
        	.x509()
        .and()
        	.addFilterBefore(filter, X509AuthenticationFilter.class)
        	.csrf().disable();
    	
    }

}
