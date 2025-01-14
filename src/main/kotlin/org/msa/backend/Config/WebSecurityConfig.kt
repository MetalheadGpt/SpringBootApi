package org.msa.backend.Config
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()  // Disable CSRF protection
            .authorizeRequests()
            .requestMatchers("/**").permitAll()  // Allow all requests to all URLs
            .anyRequest().permitAll()  // Allow any other request
            .and()
            .httpBasic().disable()  // Disable basic authentication
            .formLogin().disable()  // Disable form login
            .logout().disable()  // Disable logout functionality
        return http.build()
    }
}
