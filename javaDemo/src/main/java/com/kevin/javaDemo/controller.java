package com.kevin.javaDemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kevin
 * @date 2020-7-9 10:57
 * @description todo
 **/
@RestController
public class controller {


    @RequestMapping("/test")
    public String test() {
        return "kevin";
    }
}
