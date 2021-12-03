package com.example.CMPApplication;

import com.example.CMPApplication.filters.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.file.attribute.FileTime;

@SpringBootApplication
@RestController
public class CmpApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmpApplication.class, args);
	}

	@GetMapping("/")
	public String pingMessage(){
		return "PONG";
	}		// For PINGING THE APPLICATION

	@PostMapping("/validate-cookie")
	public Integer validateCookie(HttpServletRequest request){
		int flag = 0;
		Cookie[] cookies = request.getCookies();
		String cookieName = new String();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("token")) {
					if(cookie.getValue() != null) { flag = 1; }
				}
			}
		}

		return flag;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){

		FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedMethod("*");
		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		source.registerCorsConfiguration("/**",configuration);
		registrationBean.setFilter(new CorsFilter(source));
		registrationBean.setOrder(0);
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<AuthFilter> filterRegistrationBean(){

		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		registrationBean.setFilter(authFilter);

		// Adding URL patterns for restrictions
		registrationBean.addUrlPatterns("/api/v1/chatroom/*");

		return registrationBean;
	}

}
