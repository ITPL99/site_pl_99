package com.example.site_pl_99.configuration;

import com.example.site_pl_99.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    private final JwtAuthenticationFilter authenticationFilter;

    public SecurityConfiguration(JwtAuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.cors(cors -> cors.configurationSource(request -> {
                            CorsConfiguration corsConfiguration = new CorsConfiguration(); // Зараза надо запомнить что всегда нужно создавать новый экземпляр конфигурации
                            corsConfiguration.setAllowedOriginPatterns(List.of("*"));
                            corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                            corsConfiguration.setAllowedHeaders(List.of("*"));
                            corsConfiguration.setAllowCredentials(true);
                            return corsConfiguration;
                        }
                ));

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // Allow H2 console to be displayed in frame (required for H2 web interface)
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        // TODO: Разобраться с настройками Секьюрити почему то выдает 403 или 401 ошибку на открытые эндпоинты
        http
//                .httpBasic(Customizer.withDefaults())
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers("/h2-console").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/api/auth/current").permitAll()
                        .requestMatchers("/api/auth/password-restoration").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/auth/update-password/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/course/get-by-title").permitAll()
                        .requestMatchers("/api/course/get-all-by-type").permitAll()
                        .requestMatchers("/api/course/get-all-by-price").permitAll()
                        .requestMatchers("/api/course/get-all-by-date-start").permitAll()
                        .requestMatchers("/api/course/get-all-by-date-end").permitAll()
                        .requestMatchers("/api/course/get-by-id/**").permitAll()
                        .requestMatchers("/api/course/save").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/course/get-all").permitAll()
                        .requestMatchers("/api/course/get-all-full").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/course/delete-by-id/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/employee/get-by-full-name").permitAll()
                        .requestMatchers("/api/employee/search-by-Name").permitAll()
                        .requestMatchers("/api/employee/get-by-date-date-berth").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/employee/get-by-date-status").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/employee/get-by-date-department").permitAll()
                        .requestMatchers("/api/employee/get-by-date-dismissal").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/employee/get-by-date-employment").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/employee/get-by-id/**").permitAll()
                        .requestMatchers("/api/employee/get-all").permitAll()
                        .requestMatchers("/api/employee/create").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/employee/update").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/employee/delete-by-id/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/images/save").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/images/get-file-by-id/**").permitAll()
                        .requestMatchers("/api/images/get-file-by-name/**").permitAll()
                        .requestMatchers("/api/images/get-images-by-id/**").permitAll()
                        .requestMatchers("/api/images/get-images-by-fileName/**").permitAll()
                        .requestMatchers("/api/mail/send-message").permitAll()
                        .requestMatchers("/api/mail/get-all").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/mail/get-by-id/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/mail/get-by-title").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/mail/get-by-status").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/mail/update-status-by-id").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/master/get-by-full-name").permitAll()
                        .requestMatchers("/api/master/get-search-by-name").permitAll()
                        .requestMatchers("/api/master/get-by-active-status").permitAll()
                        .requestMatchers("/api/master/get-all-by-date-berth").permitAll()
                        .requestMatchers("/api/master/get-all-by-profession").permitAll()
                        .requestMatchers("/api/master/get-all-by-date-employment").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/master/get-all-by-date-dismissal").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/master/get-by-id/**").permitAll()
                        .requestMatchers("/api/master/get-all").permitAll()
                        .requestMatchers("/api/master/save").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/master/update-master").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/master/delete-by-id/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/news/get-by-title").permitAll()
                        .requestMatchers("/api/news/get-all-by-content-title").permitAll()
                        .requestMatchers("/api/news/get-all").permitAll()
                        .requestMatchers("/api/news/get-all-full").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/news/get-all-by-content-sub-title").permitAll()
                        .requestMatchers("/api/news/get-by-id/**").permitAll()
                        .requestMatchers("/api/news/save").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/news/delete-by-id").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/news/get-by-status").permitAll()
                        .requestMatchers("/api/teacher/get-by-full-name").permitAll()
                        .requestMatchers("/api/teacher/get-all-by-date-berth").permitAll()
                        .requestMatchers("/api/teacher/get-search-by-name").permitAll()
                        .requestMatchers("/api/teacher/get-by-acive-status").permitAll()
                        .requestMatchers("/api/teacher/get-all-by-portfolio").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/teacher/get-all-by-date-employment").permitAll()
                        .requestMatchers("/api/teacher/get-all-by-date-dismissal").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/teacher/get-all").permitAll()
                        .requestMatchers("/api/teacher/get-by-id/**").permitAll()
                        .requestMatchers("/api/teacher/update-by-id").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/teacher/save").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/teacher/delete-by-id/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/user/add").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/user/all").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/user/get-login").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/video/save").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/video/stream-file-by-id/**").permitAll()
                        .requestMatchers("/api/video/get-file-by-name/**").permitAll()
                        .requestMatchers("/api/video/get-video-by-id/**").permitAll()
                        .requestMatchers("/api/video/get-video-by-fileName/**").permitAll()
                        .requestMatchers("/api/main-menu/add-main-menu").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/main-menu/get-by-id/**").permitAll()
                        .requestMatchers("/api/main-menu/update-by-id/**").permitAll()
                        .requestMatchers("/api/main-menu/delete-by-id/**").hasAnyAuthority("ADMIN")

                        .requestMatchers("/api/employee/get-all").permitAll()
                        .requestMatchers("/api/auth/password-restoration").permitAll()
                        .requestMatchers("/api/auth/update-password/").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .requestMatchers("/api/test/open-all").permitAll()
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/swagger-ui.html",
                                "/webjars/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                ).sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }



}
