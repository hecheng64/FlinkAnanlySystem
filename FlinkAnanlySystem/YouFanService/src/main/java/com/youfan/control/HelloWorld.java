package com.youfan.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2020/3/1.
 */
@RestController
@RequestMapping("helloWorld")
public class HelloWorld {

    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public String hello(String name){
        return "hello "+name + " !";
    }

}
