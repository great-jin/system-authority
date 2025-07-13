package xyz.ibudai.authority.model.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "token")
public class TokenProperties {

    private String hosts;

    private String secret;

    private String issuer;

    private String[] whitelist;

}
