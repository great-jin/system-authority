package xyz.ibudai.authority.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.ibudai.authority.biz.dao.UserStoreDao;
import xyz.ibudai.authority.biz.service.UserStoreService;
import xyz.ibudai.authority.model.entity.UserStore;
import org.springframework.stereotype.Service;

/**
 * (TbUserStore)表服务实现类
 *
 * @author ibudai
 * @since 2025-07-12 22:22:08
 */
@Service
public class UserStoreServiceImpl extends ServiceImpl<UserStoreDao, UserStore> implements UserStoreService {

}

