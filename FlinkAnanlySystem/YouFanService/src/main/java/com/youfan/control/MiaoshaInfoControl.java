package com.youfan.control;

import com.alibaba.fastjson.JSONObject;
import com.youfan.service.MiaoShaService;
import com.youfan.service.OrderinfoService;
import com.youfan.viewResult.MiaoshaResult;
import com.youfan.viewResult.OrderInfoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2020/3/2.
 */
@RestController
@RequestMapping("miaoshaInfo")
@CrossOrigin
public class MiaoshaInfoControl {

    @Autowired
    private MiaoShaService miaoShaService;

    @RequestMapping(value = "orderPayNumsHour",method = RequestMethod.POST)
    public String orderPayNumsHour(){
        MiaoshaResult miaoshaResult = miaoShaService.orderPayNumsHour();
        String finalString = JSONObject.toJSONString(miaoshaResult);
        return finalString;
    }
    @RequestMapping(value = "orderPayNumsMinute",method = RequestMethod.POST)
        public String orderPayNumsMinute(){
            MiaoshaResult miaoshaResult = miaoShaService.orderPayNumsMinute();
            String finalString = JSONObject.toJSONString(miaoshaResult);
            return finalString;
        }
    @RequestMapping(value = "payNumsHour",method = RequestMethod.POST)
    public String payNumsHour(){
        MiaoshaResult miaoshaResult = miaoShaService.payNumsHour();
        String finalString = JSONObject.toJSONString(miaoshaResult);
        return finalString;
    }
    @RequestMapping(value = "payNumsMinute",method = RequestMethod.POST)
    public String payNumsMinute(){
        MiaoshaResult miaoshaResult = miaoShaService.payNumsMinute();
        String finalString = JSONObject.toJSONString(miaoshaResult);
        return finalString;
    }

}