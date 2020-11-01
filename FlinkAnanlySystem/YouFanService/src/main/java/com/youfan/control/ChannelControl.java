package com.youfan.control;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.ChannelInfo;
import com.youfan.entity.LiuLiangInfo;
import com.youfan.service.ChannelService;
import com.youfan.viewResult.ChannelResult;
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
@RequestMapping("channel")
@CrossOrigin
public class ChannelControl {

    @Autowired
    private ChannelService channelService;

    @RequestMapping(value = "newusersHour",method = RequestMethod.POST)
    public String newusersHour(){
        ChannelResult channelResult = channelService.newusersHour();
        String finalResult = JSONObject.toJSONString(channelResult);

        return finalResult;
    }

    @RequestMapping(value = "newusersMinute",method = RequestMethod.POST)
    public String newusersMinute(){


        ChannelResult channelResult = channelService.newusersMinute();
        String finalResult = JSONObject.toJSONString(channelResult);

        return finalResult;
    }


    @RequestMapping(value = "timesHour",method = RequestMethod.POST)
    public String timesHour(){
        ChannelResult channelResult =channelService.timesHour();
        String finalResult = JSONObject.toJSONString(channelResult);
        return finalResult;
    }

    @RequestMapping(value = "timesMinute",method = RequestMethod.POST)
    public String timesMinute(){
        ChannelResult channelResult =channelService.timesMinute();
        String finalResult = JSONObject.toJSONString(channelResult);
        return finalResult;
    }

    @RequestMapping(value = "usernumsMinute",method = RequestMethod.POST)
    public String usernumsMinute(){
        ChannelResult channelResult =channelService.timesMinute();
        String finalResult = JSONObject.toJSONString(channelResult);
        return finalResult;
    }

    @RequestMapping(value = "usernumsHour",method = RequestMethod.POST)
    public String usernumsHour(){
        ChannelResult channelResult =channelService.timesMinute();
        String finalResult = JSONObject.toJSONString(channelResult);
        return finalResult;
    }

    @RequestMapping(value = "hourActivenumsMinute",method = RequestMethod.POST)
    public String hourActivenumsMinute(){
        ChannelResult channelResult =channelService.hourActivenumsMinute();
        String finalResult = JSONObject.toJSONString(channelResult);
        return finalResult;
    }
    @RequestMapping(value = "hourActivenumsHour",method = RequestMethod.POST)
    public String hourActivenumsHour(){
        ChannelResult channelResult =channelService.hourActivenumsHour();
        String finalResult = JSONObject.toJSONString(channelResult);
        return finalResult;
    }

    @RequestMapping(value = "dayActivenumsMinute",method = RequestMethod.POST)
    public String dayActivenumsMinute(){
        ChannelResult channelResult =channelService.dayActivenumsMinute();
        String finalResult = JSONObject.toJSONString(channelResult);
        return finalResult;
    }

    @RequestMapping(value = "dayActivenumsHour",method = RequestMethod.POST)
    public String dayActivenumsHour(){
        ChannelResult channelResult =channelService.dayActivenumsHour();
        String finalResult = JSONObject.toJSONString(channelResult);
        return finalResult;
    }

    @RequestMapping(value = "monthActivenumsMinute",method = RequestMethod.POST)
    public String monthActivenumsMinute(){
        ChannelResult channelResult =channelService.monthActivenumsMinute();
        String finalResult = JSONObject.toJSONString(channelResult);
        return finalResult;
    }

    @RequestMapping(value = "monthActivenumsHour",method = RequestMethod.POST)
    public String monthActivenumsHour(){
        ChannelResult channelResult =channelService.monthActivenumsHour();
        String finalResult = JSONObject.toJSONString(channelResult);
        return finalResult;
    }

    @RequestMapping(value = "weekActivenumsMinute",method = RequestMethod.POST)
    public String weekActivenumsMinute(){
        ChannelResult channelResult =channelService.weekActivenumsMinute();
        String finalResult = JSONObject.toJSONString(channelResult);
        return finalResult;
    }

    @RequestMapping(value = "weekActivenumsHour",method = RequestMethod.POST)
    public String weekActivenumsHour(){
        ChannelResult channelResult =channelService.weekActivenumsHour();
        String finalResult = JSONObject.toJSONString(channelResult);
        return finalResult;
    }

    @RequestMapping(value = "usersDetailHour",method = RequestMethod.POST)
    public String usersDetailHour(String timeinfo){
        List<ChannelInfo> userDetailList = channelService.usersDetailHour(timeinfo);
        String userDetail = JSONObject.toJSONString(userDetailList);
        return userDetail;
    }


}