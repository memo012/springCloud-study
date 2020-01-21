package com.adminsys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/18 下午9:17
 **/
@RestController
public class IndexController {
    @GetMapping("/index")
    public String index(){
        return "success";
    }
}
