package com.example.CMPApplication;

import com.example.CMPApplication.filters.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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

	@GetMapping("/validate-cookie")
	public Integer validateCookie(HttpServletRequest request){
		System.out.println("This is request -> "+request.toString());
		int flag = 0;
		Cookie[] cookies = request.getCookies();
		String cookieName = new String();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				System.out.println("Cookie that exist -> "+cookie.getName());
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
		configuration.addAllowedOrigin("http://localhost:3000");
		configuration.addAllowedHeader("*");
		configuration.setAllowCredentials(true);
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
