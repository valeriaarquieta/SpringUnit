package org.generation.ecomerce;

import org.generation.ecomerce.config.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcomerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomerceApplication.class, args);
	}//main
@Bean
public FilterRegistrationBean<JwtFilter>jwtFilter(){
	FilterRegistrationBean<JwtFilter> registrationBean=
			new FilterRegistrationBean<>();
	registrationBean.setFilter(new JwtFilter());
	registrationBean.addUrlPatterns("/api/productos/*");
	registrationBean.addUrlPatterns("/api/usuarios/*");
	return registrationBean;
}
}//ecomerceapp
