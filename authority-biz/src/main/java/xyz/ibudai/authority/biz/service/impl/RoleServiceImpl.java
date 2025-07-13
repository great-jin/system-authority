package xyz.ibudai.authority.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.ibudai.authority.biz.dao.RoleDao;
import xyz.ibudai.authority.biz.service.RoleService;
import xyz.ibudai.authority.model.entity.Role;
import org.springframework.stereotype.Service;

/**
 * (TbRole)表服务实现类
 *
 * @author ibudai
 * @since 2025-07-12 22:22:06
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

}

