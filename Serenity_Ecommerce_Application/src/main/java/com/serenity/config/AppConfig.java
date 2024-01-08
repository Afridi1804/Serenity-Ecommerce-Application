package com.serenity.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.serenity.config.CsrfCookieFilter;
import com.serenity.config.JwtTokenGeneratorFilter;
import com.serenity.config.JwtTokenValidatorFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class AppConfig {



	@Autowired
	public CustomAuthSuccessHandler successHandler;
	
	
	@Bean
	public UserDetailsService getDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
//		http.csrf(csrf -> csrf.disable())
//		.authorizeHttpRequests(auth -> 
//         auth.requestMatchers("/","/register","/signin","/saveUser","/home").permitAll()
//		.requestMatchers("/user/**").authenticated())
//		.formLogin(login -> login
//              .loginPage("/signin")
//              .loginProcessingUrl("/userLogin")
//             .defaultSuccessUrl("/user/profile"));
//		return http.build();
		
		
		CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
		
//		http.sessionManagement(
//				sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//
//				.cors(cors -> {
//
//					cors.configurationSource(new CorsConfigurationSource() {
//
//						@Override
//						public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//
//							CorsConfiguration cfg = new CorsConfiguration();
//
//							cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
//							cfg.setAllowedMethods(Collections.singletonList("*"));
//							cfg.setAllowCredentials(true);
//							cfg.setAllowedHeaders(Collections.singletonList("*"));
//							cfg.setExposedHeaders(Arrays.asList("Authorization"));
//							return cfg;
//
//						}
//					});
//
//				})
		http.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth.requestMatchers("/user/**").hasRole("USER")
		.requestMatchers("/admin/**").hasRole("ADMIN")
		.requestMatchers("/**").permitAll())
//		.csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/notice","/contact","/customers")
//				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//		.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class) // adding csrf filter from the CsrfCookieFilter class
		.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
		.formLogin(login -> login
              .loginPage("/signin")
              .loginProcessingUrl("/userLogin")
              .successHandler(successHandler)
              .permitAll()
				);
              
				
				
		return http.build();		
		
		
	}
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
