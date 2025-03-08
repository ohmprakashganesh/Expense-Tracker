package internship.intern.Authentication.Configuration;

import java.nio.file.DirectoryStream.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import internship.intern.Authentication.AuthServices.AuthFilterService;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class FilterConfig {

    private final AuthenticationProvider authenticationProvider;
    private final AuthFilterService authFilterService;

    public FilterConfig(AuthenticationProvider authenticationProvider, AuthFilterService authFilterService){
        this.authFilterService=authFilterService;
        this.authenticationProvider=authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

       return http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth-> auth
        .requestMatchers("/user/**","/budget/**", "/category/**", "/expense/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        )
        .sessionManagement(session-> session
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(authFilterService, UsernamePasswordAuthenticationFilter.class)
        .build();

        
    }

    
}
