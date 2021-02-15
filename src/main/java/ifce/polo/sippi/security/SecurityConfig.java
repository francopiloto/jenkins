package ifce.polo.sippi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired Environment env;
    @Autowired private UserDetailsService userService;
    @Autowired private JwtAuthenticationEntryPoint unauthorizedHandler;

/* --------------------------------------------------------------------------------------------- */

    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer()
        {
            @Override
            public void addCorsMappings(CorsRegistry registry)
            {
                String[] origins = env.getProperty("cors.allowed.origins").split(",");

                registry.addMapping("/**")
                        .allowedOrigins(origins)
                        .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH");
            }
        };
    }

/* --------------------------------------------------------------------------------------------- */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

/* --------------------------------------------------------------------------------------------- */

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

/* --------------------------------------------------------------------------------------------- */

    /**
     * Making the AuthentiationManager available for other classes
     */
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception
    {
        // Setting up custom user details service and password encoder
        authBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .cors().and()

            // Enabling JWT authentication
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)

            // Disabling CSRF verification token and Spring SESSION, since we're using JTW
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

            .authorizeRequests()
                // Enabling public access for login and register requests
                .antMatchers("/{version:v\\d+}/login/**","/{version:v\\d+}/cadastro/**", "/{version:v\\d+}/download/**").permitAll()

                // All other requests must be authenticated
                .anyRequest().authenticated();
    }

/* --------------------------------------------------------------------------------------------- */

}
