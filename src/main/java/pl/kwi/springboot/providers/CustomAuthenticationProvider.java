package pl.kwi.springboot.providers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private static final String USERNAME = "user";
	private static final String PASSWORD = "password";
	private static final String ROLE = "ROLE_USER";

	@Override
    public Authentication authenticate(Authentication auth) 
      throws AuthenticationException {
    	
        String username = auth.getName();
        String password = auth.getCredentials().toString();
 
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
        	List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority(ROLE));
            return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
        } else {
            throw new
              BadCredentialsException("External system authentication failed");
        }
        
    }
 
    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
