package simplerestapp.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.SerializationFeature;

import simplerestapp.util.HibernateAwareObjectMapper;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "simplerestapp")
public class WebMvcConfiguration extends WebMvcConfigurerAdapter{
	
	
	
	@Bean 
    public MappingJackson2HttpMessageConverter converter() { 
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(); 
        HibernateAwareObjectMapper haom = new HibernateAwareObjectMapper();

        converter.setObjectMapper(haom);
        
        return converter; 
    } 
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Override 
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) { 
        converters.add(converter()); 
        super.configureMessageConverters(converters);
    }
}
