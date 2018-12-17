package pl.kwi.springboot.entrypoints;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.AuthenticationException authException)
			throws IOException, ServletException {
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Custom Unauthorized");
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("TEST REALM");
	}
	
	
	
}
