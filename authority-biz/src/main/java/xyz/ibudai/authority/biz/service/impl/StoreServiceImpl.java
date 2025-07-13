package xyz.ibudai.authority.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.ibudai.authority.biz.dao.StoreDao;
import xyz.ibudai.authority.biz.service.StoreService;
import xyz.ibudai.authority.model.entity.Store;
import org.springframework.stereotype.Service;

/**
 * (TbStore)表服务实现类
 *
 * @author ibudai
 * @since 2025-07-12 22:22:08
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreDao, Store> implements StoreService {

}

