package com.youfan.control;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.ChannelInfo;
import com.youfan.service.ChannelService;
import com.youfan.service.OrderinfoService;
import com.youfan.viewResult.ChannelResult;
import com.youfan.viewResult.OrderInfoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2020/3/2.
 */
@RestController
@RequestMapping("orderInfo")
@CrossOrigin
public class OrderInfoControl {

    @Autowired
    private OrderinfoService orderinfoService;

    @RequestMapping(value = "timesHour",method = RequestMethod.POST)
    public String timesHour(){
        OrderInfoResult orderInfoResult = orderinfoService.timesHour();
        String finalString = JSONObject.toJSONString(orderInfoResult);
        return finalString;
    }
    @RequestMapping(value = "timesMinute",method = RequestMethod.POST)
    public String timesMinute(){
        OrderInfoResult orderInfoResult = orderinfoService.timesHour();
        String finalString = JSONObject.toJSONString(orderInfoResult);
        return finalString;

    }
    @RequestMapping(value = "userNumHour",method = RequestMethod.POST)
    public String userNumHour(){
        OrderInfoResult orderInfoResult = orderinfoService.timesHour();
        String finalString = JSONObject.toJSONString(orderInfoResult);
        return finalString;
    }
    @RequestMapping(value = "userNumMinute",method = RequestMethod.POST)
    public String userNumMinute(){
        OrderInfoResult orderInfoResult = orderinfoService.timesHour();
        String finalString = JSONObject.toJSONString(orderInfoResult);
        return finalString;
    }

    @RequestMapping(value = "keDanJiaHour",method = RequestMethod.POST)
    public String keDanJiaHour(){
        OrderInfoResult orderInfoResult = orderinfoService.keDanJiaHour();
        String finalString = JSONObject.toJSONString(orderInfoResult);
        return finalString;
    }

    @RequestMapping(value = "keDanJiaMinute",method = RequestMethod.POST)
    public String keDanJiaMinute(){
        OrderInfoResult orderInfoResult = orderinfoService.keDanJiaMinute();
        String finalString = JSONObject.toJSONString(orderInfoResult);
        return finalString;
    }


}