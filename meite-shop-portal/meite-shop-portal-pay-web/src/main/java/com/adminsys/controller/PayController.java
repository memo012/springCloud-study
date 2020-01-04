package com.adminsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/4 下午5:15
 **/
@Controller
public class PayController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
