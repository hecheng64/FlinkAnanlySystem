package com.youfan.control;

import com.alibaba.fastjson.JSONObject;
import com.youfan.service.HuodongInfoService;
import com.youfan.service.TuangouService;
import com.youfan.viewResult.HuodongInfoResult;
import com.youfan.viewResult.TuanGouInfoListResult;
import com.youfan.viewResult.TuanGouInfoResult;
import com.youfan.yewu.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/3/2.
 */
@RestController
@RequestMapping("huodongInfo")
@CrossOrigin
public class HuodongInfoControl {

    @Autowired
    private HuodongInfoService huodongInfoService;

    @RequestMapping(value = "orderNumsHour",method = RequestMethod.POST)
    public String orderNumsHour(){
        HuodongInfoResult huodongInfoResult = huodongInfoService.orderNumsHour();
        String finalString = JSONObject.toJSONString(huodongInfoResult);
        return finalString;
    }
    @RequestMapping(value = "orderNumsMinute",method = RequestMethod.POST)
    public String orderNumsMinute() {
            HuodongInfoResult huodongInfoResult = huodongInfoService.orderNumsMinute();
            String finalString = JSONObject.toJSONString(huodongInfoResult);
            return finalString;
    }
    @RequestMapping(value = "areaNumHour",method = RequestMethod.POST)
    public String areaNumHour() {
        HuodongInfoResult huodongInfoResult = huodongInfoService.areaNumHour();
        String finalString = JSONObject.toJSONString(huodongInfoResult);
        return finalString;
    }
    @RequestMapping(value = "areaNumMinute",method = RequestMethod.POST)
    public String areaNumMinute(){
            HuodongInfoResult huodongInfoResult = huodongInfoService.areaNumMinute();
            String finalString = JSONObject.toJSONString(huodongInfoResult);
            return finalString;
    }
}