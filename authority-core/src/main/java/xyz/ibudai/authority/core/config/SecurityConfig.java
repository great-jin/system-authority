package xyz.ibudai.authority.core.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import xyz.ibudai.authority.core.filter.TokenFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenFilter tokenFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 关闭 CSRF
                .csrf(AbstractHttpConfigurer::disable)
                // 关闭默认登录表单
                .formLogin(AbstractHttpConfigurer::disable)
                // 关闭 HTTP Basic 登录
                .httpBasic(AbstractHttpConfigurer::disable)
                // 放行所有请求
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                // 后置过滤器
                .addFilterAfter(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

