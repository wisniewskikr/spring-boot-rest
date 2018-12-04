package pl.kwi.springboot.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.x509.X509AuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import pl.kwi.springboot.filters.BasicFilter;
import pl.kwi.springboot.filters.X509Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private X509Filter x509Filter;
	
	@Autowired
	private BasicFilter basicFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
   	
    	http.authorizeRequests()
    		.antMatchers("/**").hasRole("USER")
        .and()
        	.httpBasic()
        .and()
        	.x509()
        .and()
        	.addFilterBefore(basicFilter, BasicAuthenticationFilter.class)
        	.addFilterBefore(x509Filter, X509AuthenticationFilter.class)
        	.csrf().disable();
    	
    }

}
