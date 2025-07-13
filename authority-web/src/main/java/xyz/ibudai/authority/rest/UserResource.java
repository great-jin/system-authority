package xyz.ibudai.authority.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.ibudai.authority.biz.service.UserService;
import xyz.ibudai.authority.common.utils.JwtUtils;
import xyz.ibudai.authority.model.base.ResultData;
import xyz.ibudai.authority.model.entity.User;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserResource {

    private final JwtUtils jwtUtils;
    private final ObjectMapper objectMapper;

    private final UserService userService;


    @PostMapping("login")
    public ResultData<String> login(@Validated @RequestBody User user) throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResultData.failed("username or password is empty");
        }
        User matchUser = userService.lambdaQuery()
                .eq(User::getUsername, username)
                .one();
        if (Objects.isNull(matchUser)) {
            return ResultData.failed("user not exist");
        }

        if (!Objects.equals(matchUser.getPassword(), user.getPassword())) {
            return ResultData.failed("password does not match");
        }

        // 登录成功
        matchUser.setPassword(jwtUtils.sha256(password));
        String token = jwtUtils.create(objectMapper.writeValueAsString(matchUser));
        return ResultData.success(token);
    }
}
