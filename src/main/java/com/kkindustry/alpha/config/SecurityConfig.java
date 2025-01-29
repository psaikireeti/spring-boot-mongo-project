package com.kkindustry.alpha.config;

import com.kkindustry.alpha.security.CustomAuthenticationFailureHandler;
import com.kkindustry.alpha.security.CustomAuthenticationSuccessHandler;
import com.kkindustry.alpha.security.MongodbAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  private final MongodbAuthenticationProvider mongodbAuthenticationProvider;
  private final CustomAuthenticationSuccessHandler successHandler;
  private final CustomAuthenticationFailureHandler failureHandler;

  @Autowired
  public SecurityConfig(
      MongodbAuthenticationProvider mongodbAuthenticationProvider,
      CustomAuthenticationSuccessHandler successHandler,
      CustomAuthenticationFailureHandler failureHandler) {
    this.mongodbAuthenticationProvider = mongodbAuthenticationProvider;
    this.successHandler = successHandler;
    this.failureHandler = failureHandler;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers("/login.html")
                    .permitAll() // Allow public access to login page
                    .anyRequest()
                    .authenticated() // Allow all authenticated users to access any page
            )
        .formLogin(
            login ->
                login
                    .loginPage("/login.html") // Custom login page
                    .loginProcessingUrl("/spring_security_check") // This processes the login form
                    .successHandler(successHandler)
                    .failureHandler(failureHandler)
                    .permitAll() // Allow access to the login page
            )
        .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login.html"))
        .httpBasic(Customizer.withDefaults()); // Enable HTTP Basic authentication for APIs

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder());
    provider.setUserDetailsService(mongodbAuthenticationProvider);
    return provider;
  }
}
