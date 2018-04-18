package pl.kwi.springboot.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CustomFilter extends OncePerRequestFilter {
	
	private static final String USERNAME = "user";
	private static final String PASSWORD = "password";
	private static final String ROLE = "ROLE_USER";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    	
    	String header = request.getHeader("Authorization");
    	if(header == null) {
    		filterChain.doFilter(request, response);
    		return;
    	}
    	
    	String[] tokens = extractAndDecodeHeader(header, request);
    	assert tokens.length == 2;
    	
    	String username = tokens[0];
    	String password = tokens[1];
    	
    	if (USERNAME.equals(username) && PASSWORD.equals(password)) {
        	List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority(ROLE));
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);
        } else {
            throw new
              BadCredentialsException("External system authentication failed");
        }
        
    }
    
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request)
			throws IOException {

		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		}
		catch (IllegalArgumentException e) {
			throw new BadCredentialsException(
					"Failed to decode basic authentication token");
		}

		String token = new String(decoded, "UTF-8");

		int delim = token.indexOf(":");

		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		return new String[] { token.substring(0, delim), token.substring(delim + 1) };
	}

}
