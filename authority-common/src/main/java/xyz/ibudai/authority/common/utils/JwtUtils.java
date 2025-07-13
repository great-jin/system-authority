package xyz.ibudai.authority.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.ibudai.authority.model.props.TokenProperties;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final TokenProperties tokenProperties;


    /**
     * SHA-256 加密
     *
     * @param data 数据
     */
    public String sha256(String data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = digest.digest(data.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b & 0xff));
        }
        return builder.toString();
    }

    /**
     * 生成 JWT Token
     *
     * @param data 明文
     */
    public String create(String data) {
        return getJwtBuilder(data, TimeUnit.HOURS.toMillis(2)).compact();
    }

    /**
     * 解析 JWT Token
     *
     * @param token 密文
     */
    public Claims parse(String token) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }


    private JwtBuilder getJwtBuilder(String subject, Long ttlMillis) {
        Date nowadays = new Date();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                // 内容
                .setSubject(subject)
                // 签发者
                .setIssuer("budai")
                // 签发时间
                .setIssuedAt(nowadays)
                // 加密算法签名
                .signWith(SignatureAlgorithm.HS256, generalKey())
                // 过期事件
                .setExpiration(new Date(nowadays.getTime() + ttlMillis));
    }

    /**
     * 生成加密后的秘钥
     */
    private SecretKey generalKey() {
        byte[] bytes = tokenProperties.getSecret().getBytes();
        byte[] encodedKey = Base64.getEncoder().encode(bytes);
        return new SecretKeySpec(
                encodedKey,
                0,
                encodedKey.length,
                "AES"
        );
    }
}
