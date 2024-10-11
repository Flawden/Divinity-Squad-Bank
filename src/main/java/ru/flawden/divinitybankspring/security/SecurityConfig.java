package ru.flawden.divinitybankspring.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.flawden.divinitybankspring.repository.PeopleRepository;
import ru.flawden.divinitybankspring.service.PersonDetailService;

/**
 * Configuration class for Spring Security.
 * It defines security settings such as authorization rules, login and logout processes.
 *
 * @author Flawden
 * @version 1.0
 */
@EnableWebSecurity
public class SecurityConfig {

    private final PeopleRepository peopleRepository;

    public SecurityConfig(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    /**
     * Configures security filter chain for the application.
     *
     * @param http HttpSecurity to be configured.
     * @return The configured SecurityFilterChain.
     * @throws Exception if an error occurs while configuring the security.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/registration", "/registration/perform", "/resources/**", "/perform-login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform-login")
                        .defaultSuccessUrl("/account", true)
                        .failureUrl("/login?error")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, authException) -> {
                            System.out.println("Authentication failed: " + authException.getMessage());
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
                        })
                );

        return http.build();

    }

    /**
     * Provides a DaoAuthenticationProvider for user authentication.
     *
     * @return Configured DaoAuthenticationProvider.
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Provides a PasswordEncoder for encoding passwords.
     *
     * @return PasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    /**
     * Provides a UserDetailsService for loading user-specific data.
     *
     * @return UserDetailsService implementation.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new PersonDetailService(peopleRepository);
    }

}
