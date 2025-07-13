package xyz.ibudai.authority.core.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import xyz.ibudai.authority.model.dto.UserDTO;

public class SecurityManager {

    public static Long getUserId() {
        UserDTO user = (UserDTO) getAuthentication().getPrincipal();
        return user.getUserId();
    }

    /**
     * 读取上下文认证信息
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 设置认证上下文信息
     *
     * @param user 登录用户
     */
    public static void setAuthentication(UserDTO user) {
        Authentication authentic = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(authentic);
    }
}
