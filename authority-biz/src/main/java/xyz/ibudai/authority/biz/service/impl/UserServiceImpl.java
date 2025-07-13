package xyz.ibudai.authority.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import xyz.ibudai.authority.biz.dao.UserDao;
import xyz.ibudai.authority.biz.service.UserService;
import xyz.ibudai.authority.model.entity.User;
import org.springframework.stereotype.Service;

/**
 * (TbUser)表服务实现类
 *
 * @author ibudai
 * @since 2025-07-12 22:22:07
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}

