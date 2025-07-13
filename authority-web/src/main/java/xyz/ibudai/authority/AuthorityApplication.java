package xyz.ibudai.authority;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@MapperScan("xyz.ibudai.authority.biz.dao")
@SpringBootApplication(scanBasePackages = {
        "xyz.ibudai.authority",
        "xyz.ibudai.authority.core",
        "xyz.ibudai.authority.manage"
})
public class AuthorityApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorityApplication.class, args);
    }
}
