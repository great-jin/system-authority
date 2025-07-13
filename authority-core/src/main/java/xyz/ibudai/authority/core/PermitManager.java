package xyz.ibudai.authority.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.ibudai.authority.biz.service.UserRoleService;
import xyz.ibudai.authority.core.security.SecurityManager;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component("pm")
@RequiredArgsConstructor
public class PermitManager {

    private static final String SEPARATOR = ",";
    private static final String ALL_PERMIT = "*.*";

    private final UserRoleService userRoleService;


    /**
     * 登录用户是否包含指定权限
     *
     * @param permit 菜单权限
     * @return the boolean
     */
    public boolean hasPermit(String permit) {
        Long userId = this.getUserId();
        if (Objects.isNull(userId)) {
            return false;
        }

        // 判断是否拥有权限
        Set<String> menuPermits = userRoleService.getUserMenus(userId);
        if (menuPermits.contains(ALL_PERMIT)) {
            // 拥有所有菜单权限
            return true;
        }
        return menuPermits.contains(permit);
    }

    /**
     * 登录用户是否包含指定任意权限
     *
     * @param permit 菜单权限，逗号分割
     * @return the boolean
     */
    public boolean hasAnyPermit(String permit) {
        Long userId = this.getUserId();
        if (Objects.isNull(userId)) {
            return false;
        }

        Set<String> menuPermits = userRoleService.getUserMenus(userId);
        if (menuPermits.contains(ALL_PERMIT)) {
            // 拥有所有菜单权限
            return true;
        }
        Set<String> inputs = Arrays.stream(permit.split(SEPARATOR))
                .collect(Collectors.toSet());
        for (String input : inputs) {
            if (menuPermits.contains(input)) {
                return true;
            }
        }
        return false;
    }


    private Long getUserId() {
        Long userId = null;
        try {
            userId = SecurityManager.getUserId();
        } catch (Exception e) {
            log.error("Not found user, message: {}", e.getMessage());
        }
        return userId;
    }
}
