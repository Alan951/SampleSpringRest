package simplerestapp.authapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import static simplerestapp.authapi.security.SecurityConstants.EXPIRATION_TIME;
import static simplerestapp.authapi.security.SecurityConstants.HEADER_STRING;
import static simplerestapp.authapi.security.SecurityConstants.SECRET;
import static simplerestapp.authapi.security.SecurityConstants.SIGN_UP_URL;
import static simplerestapp.authapi.security.SecurityConstants.TOKEN_PREFIX;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authManager){
		this.authenticationManager = authManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) 
			throws AuthenticationException{
		try{
			simplerestapp.models.User user = new ObjectMapper().readValue(req.getInputStream(), simplerestapp.models.User.class);
			
			return authenticationManager.authenticate( 
					new UsernamePasswordAuthenticationToken(user.getUsername(), 
							user.getPassword(), new ArrayList<>())
				);
		}catch (IOException e){
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req, 
											HttpServletResponse res,
											FilterChain chain,
											Authentication auth) throws IOException, ServletException{
		
		String token = Jwts.builder()
				.setSubject(((User) auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
				.compact();
		
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}
	
}
