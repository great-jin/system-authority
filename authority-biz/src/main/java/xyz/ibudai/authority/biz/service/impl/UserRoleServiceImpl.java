package xyz.ibudai.authority.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import xyz.ibudai.authority.biz.dao.UserRoleDao;
import xyz.ibudai.authority.biz.service.UserRoleService;
import xyz.ibudai.authority.common.cache.MenuCache;
import xyz.ibudai.authority.common.cache.UserRoleCache;
import xyz.ibudai.authority.model.entity.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * (TbUserRole)表服务实现类
 *
 * @author ibudai
 * @since 2025-07-12 22:22:07
 */
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

    private final MenuCache menuCache;
    private final UserRoleCache userRoleCache;


    @Override
    public Set<String> getUserMenus(Long userId) {
        Set<Long> roleIds = userRoleCache.read(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            // 查询用户角色
            List<UserRole> userRoles = this.lambdaQuery()
                    .eq(UserRole::getUserId, userId)
                    .list();
            roleIds = userRoles.stream()
                    .map(UserRole::getRoleId)
                    .collect(Collectors.toSet());
            userRoleCache.add(userId, roleIds);
        }

        return menuCache.read(roleIds);
    }
}

