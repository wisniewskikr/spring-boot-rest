package pl.kwi.springboot.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class X509Filter extends OncePerRequestFilter {
	
	private static final String USERNAME = "user";
	private static final String PASSWORD = "password";
	private static final String ROLE = "ROLE_USER";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    	
    	X509Certificate remoteCert = getRemoteCertificate(request);
    	X509Certificate localCert = getLocalCertificate();
    	
    	System.out.println(remoteCert.equals(localCert));
    	
    	if (remoteCert.equals(localCert)) {
        	List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority(ROLE));
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(USERNAME, PASSWORD, grantedAuths);
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);
        } else {
            throw new
              BadCredentialsException("External system authentication failed");
        }
        
    }
    
    private X509Certificate getRemoteCertificate(HttpServletRequest request) {
    	
    	X509Certificate[] certs = (X509Certificate[])request.getAttribute("javax.servlet.request.X509Certificate");
    	return certs[0];
    	
    }
    
    private X509Certificate getLocalCertificate() {
    	
    	X509Certificate certificate = null;
    	
    	try {
			
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStore.load(this.getClass().getClassLoader().getResourceAsStream("jks/servertruststore.jks"), "password".toCharArray());
			certificate = (X509Certificate) keyStore.getCertificate("client");
			
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return certificate;   	
    	
    }


}
