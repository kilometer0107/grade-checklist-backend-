package com.team.grade_checklist.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers
                        // H2 콘솔을 위한 X-Frame-Options 비활성화
                        .frameOptions(frame -> frame.sameOrigin())
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/auth/register").permitAll()           // 회원가입
                        .requestMatchers("/api/auth/login").permitAll()              // 로그인
                        .requestMatchers("/api/auth/validate").permitAll()           // 토큰 검증
                        .requestMatchers("/h2-console/**").permitAll()               // H2 콘솔
                        .requestMatchers("/error").permitAll()                      // 에러 페이지
                        .anyRequest().authenticated()                                // 나머지는 인증 필요 (logout, me 포함)
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 현재는 모든 도메인 허용
        if (isProduction()) {
            // 운영환경에서는 실제 프론트엔드 도메인만 허용할 것
            // configuration.setAllowedOrigins(Arrays.asList(
            //     "https://frontend-domain.com",
            //     "https://www.frontend-domain.com"
            // ));

            configuration.setAllowedOriginPatterns(Arrays.asList("*")); // -> 지워야됨
        } else {
            // 개발환경 -> 로컬 개발서버 허용
            configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        }

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private boolean isProduction() {
        // 환경변수나 프로파일로 운영환경 판단
        String profile = System.getProperty("spring.profiles.active", "dev");
        return "prod".equals(profile);
    }
}