package xyz.ibudai.authority.core.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.ibudai.authority.common.utils.JwtUtils;
import xyz.ibudai.authority.core.security.SecurityManager;
import xyz.ibudai.authority.model.base.ResultData;
import xyz.ibudai.authority.model.dto.UserDTO;
import xyz.ibudai.authority.model.props.TokenProperties;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {

    private final TokenProperties tokenProperties;

    private final JwtUtils jwtUtils;
    private final ObjectMapper objectMapper;


    /**
     * 配置过滤器白名单
     *
     * @param request 请求
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String target = request.getRequestURI();
        String[] whitelist = tokenProperties.getWhitelist();
        for (String url : whitelist) {
            if (url.startsWith(target)) {
                // 白名单不做处理
                return true;
            }
        }
        return false;
    }

    /**
     * 每次请求读取请求头 Token 验证是否登录
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Token");
        if (StringUtils.isBlank(token)) {
            this.processNotPermit("Please login first.", response);
            return;
        }

        UserDTO user;
        try {
            // 校验 JWT
            Claims claims = jwtUtils.parse(token);
            String content = claims.get("sub").toString();
            user = objectMapper.readValue(content, UserDTO.class);
        } catch (Exception e) {
            log.error("Read jwt error", e);
            this.processNotPermit("Login expired.", response);
            return;
        }
        if (Objects.isNull(user)) {
            this.processNotPermit("Invalid token.", response);
        }

        // 校验通过
        SecurityManager.setAuthentication(user);
        filterChain.doFilter(request, response);
    }


    private void processNotPermit(String msg, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(203);
        response.getWriter().write(objectMapper.writeValueAsString(ResultData.reject(msg)));
    }
}
