package com.kevin.javaDemo.FilterDemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kevin
 * @date 2019-9-15 21:09
 * @description todo
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/{id}")
    public UserInfo getUserInfo(@PathVariable Long id){
        System.out.println("=====UserController getInfo =======");
        UserInfo userInfo=new UserInfo();
        userInfo.setUserId(id);
        userInfo.setName("kevin");
        userInfo.setAge(18);
        System.out.println("====userController getInfo end=====");
        return userInfo;
    }
    @GetMapping("age/{id}")
    public UserInfo incrUserage(@PathVariable Long id){
        UserInfo userInfo=new UserInfo();
        userInfo.setAge(18);
        userInfo.setUserId(id);
        userInfo.setName("jim");
        return userInfo;
    }

}
