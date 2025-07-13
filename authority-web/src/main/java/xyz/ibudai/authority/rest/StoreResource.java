package xyz.ibudai.authority.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ibudai.authority.biz.service.StoreService;
import xyz.ibudai.authority.manage.annotation.StorePermit;
import xyz.ibudai.authority.model.base.ResultData;
import xyz.ibudai.authority.model.entity.Order;
import xyz.ibudai.authority.model.entity.Store;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreResource {

    private final StoreService storeService;


    @GetMapping("{storeId}")
    @PreAuthorize("@pm.hasPermit('store.query')")
    public ResultData<Store> query(@StorePermit @PathVariable String storeId) {
        Store store = storeService.lambdaQuery()
                .eq(Store::getStoreId, storeId)
                .one();
        return ResultData.success(store);
    }
}
