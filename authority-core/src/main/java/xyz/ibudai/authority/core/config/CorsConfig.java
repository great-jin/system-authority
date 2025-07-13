package xyz.ibudai.authority.core.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.ibudai.authority.model.props.TokenProperties;

import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
public class CorsConfig implements WebMvcConfigurer {

    private final TokenProperties tokenProperties;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] hosts = tokenProperties.getHosts().split(",");
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns(hosts)
                // 是否允许cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(TimeUnit.SECONDS.toMillis(5));
    }
}
