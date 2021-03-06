package simplerestapp.authapi;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

import static simplerestapp.authapi.security.SecurityConstants.HEADER_STRING;
import static simplerestapp.authapi.security.SecurityConstants.SECRET;
import static simplerestapp.authapi.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	
	public JWTAuthorizationFilter(AuthenticationManager authManager){
		super(authManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req,
									HttpServletResponse res,
									FilterChain chain)throws IOException, ServletException{
		
		String header = req.getHeader(HEADER_STRING);
		
		if(header == null || !header.startsWith(TOKEN_PREFIX)){
			chain.doFilter(req, res);
			return;
		}
		
		UsernamePasswordAuthenticationToken auth = getAuthentication(req);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		chain.doFilter(req, res);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req){
		String token = req.getHeader(HEADER_STRING);
		if(token != null){
			String user = Jwts.parser()
							.setSigningKey(SECRET.getBytes())
							.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
							.getBody()
							.getSubject();
			if(user != null){
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
		}
		
		return null;
	}
}
