package com.kevin.service.impl;

import com.kevin.bo.MessageBo;
import com.kevin.exception.DefineException;
import com.kevin.mapper.ProductInfoMapper;
import com.kevin.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kevin
 * @date 2019-11-12 14:10
 * @description todo
 **/
@Service
@Slf4j
public class ProductServiceimpl implements ProductService {
    @Autowired
    private ProductInfoMapper productInfo;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateProductStore(MessageBo message) {
        boolean updateFlg=true;
        try{
        productInfo.updateProductStoreById(message.getProductNo());
        //System.out.println(1/0);
        }catch (Exception e){
            log.error("更新数据库失败：{}",e);
            throw new DefineException(0,"更新数据库异常");
        }
        return updateFlg;
    }
}
