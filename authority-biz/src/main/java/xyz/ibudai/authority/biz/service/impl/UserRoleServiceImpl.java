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

import java.util.*;
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

    @Override
    public void addUserRole(List<UserRole> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        // 查询历史数据
        Set<String> recordKeys = new HashSet<>();
        List<UserRole> histories = this.listHistory(list);
        for (UserRole item : histories) {
            recordKeys.add(String.valueOf(item.getUserId()) + item.getRoleId());
        }

        // 过滤保存
        List<UserRole> addList = new ArrayList<>();
        for (UserRole item : list) {
            String key = String.valueOf(item.getUserId()) + item.getRoleId();
            if (recordKeys.contains(key)) {
                // 跳过已存在
                continue;
            }

            addList.add(item);
        }

        this.saveBatch(addList);
        addList.forEach(it -> userRoleCache.add(it.getUserId(), it.getRoleId()));
    }

    @Override
    public void deleteUserRole(List<UserRole> list) {
        for (UserRole item : list) {
            this.lambdaUpdate()
                    .eq(UserRole::getUserId, item.getUserId())
                    .eq(UserRole::getRoleId, item.getRoleId())
                    .remove();
            userRoleCache.delete(item.getUserId(), item.getRoleId());
        }
    }


    /**
     * 查询历史记录
     */
    private List<UserRole> listHistory(List<UserRole> list) {
        Set<Long> userIds = new HashSet<>();
        Set<Long> roleIds = new HashSet<>();
        for (UserRole item : list) {
            userIds.add(item.getUserId());
            roleIds.add(item.getRoleId());
        }
        return this.lambdaQuery()
                .in(UserRole::getUserId, userIds)
                .in(UserRole::getRoleId, roleIds)
                .list();
    }
}

