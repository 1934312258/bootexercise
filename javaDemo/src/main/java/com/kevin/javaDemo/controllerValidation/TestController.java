package com.kevin.javaDemo.controllerValidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kevin
 * @date 2020-7-22 13:30
 * @description todo
 **/
@RestController
public class TestController {
    Logger logger= LoggerFactory.getLogger(TestController.class);
    @RequestMapping("/test")
    public void valid(@Validated @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            for(FieldError error: result.getFieldErrors()){
                logger.error("参数：{}校验失败，原因：{}",error.getField(),error.getDefaultMessage());
            }
        }
    }
    //分组校验，我们写业务时通常会存在一种情况：新增操作时通常不需要校验参数Id，而在修改或删除操作时我们又需要校验参数Id
    //首先我们新建一个更新分组，只需要一个普普通通的接口类就行：
    @RequestMapping("/update")
    public void valid1(@Validated(Update.class) @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            for(FieldError error: result.getFieldErrors()){
                logger.error("参数：{}校验失败，原因：{}",error.getField(),error.getDefaultMessage());
            }
        }
    }

}
