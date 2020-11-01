package com.youfan.control;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.LiuLiangInfo;
import com.youfan.service.LiuLiangService;
import com.youfan.utils.ClickHouseUtils;
import com.youfan.utils.DateUtils;
import com.youfan.viewResult.LiuLiangResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2020/3/2.
 */
@RestController
@RequestMapping("liuliang")
@CrossOrigin
public class LiuLiangControl {

    @Autowired
    private LiuLiangService liuLiangService;

    @RequestMapping(value = "newusersHour",method = RequestMethod.POST)
    public String newusersHour(){

//        List<String> xlist = new ArrayList<String>();
//        xlist.add("2018090501");
//        xlist.add("2018090502");
//        xlist.add("2018090503");
//        xlist.add("2018090504");
//        xlist.add("2018090505");
//
//        List<Map<String,Object>> newUserHourlist = new ArrayList<Map<String,Object>>();
//        List<Long> dataList = new ArrayList<Long>();
//        dataList.add(45l);
//        dataList.add(65l);
//        dataList.add(25l);
//        dataList.add(35l);
//        dataList.add(15l);
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("name","pc端");
//        map.put("data",dataList);
//        newUserHourlist.add(map);
//        dataList = new ArrayList<Long>();
//        dataList.add(75l);
//        dataList.add(165l);
//        dataList.add(5l);
//        dataList.add(25l);
//        dataList.add(85l);
//        map = new HashMap<String,Object>();
//        map.put("name","小程序端");
//        map.put("data",dataList);
//        newUserHourlist.add(map);
//        LiuLiangResult liuLiangResult = new LiuLiangResult();
//        liuLiangResult.setXlist(xlist);
//        liuLiangResult.setNewuserslist(newUserHourlist);
//        String finalResult = JSONObject.toJSONString(liuLiangResult);

        LiuLiangResult liuLiangResult = liuLiangService.newusersHour();
        String finalResult = JSONObject.toJSONString(liuLiangResult);

        return finalResult;
    }

    @RequestMapping(value = "newusersMinute",method = RequestMethod.POST)
    public String newusersMinute(){
//        List<String> xlist = new ArrayList<String>();
//        xlist.add("201809050100");
//        xlist.add("201809050105");
//        xlist.add("201809050110");
//        xlist.add("201809050115");
//        xlist.add("201809050120");
//
//        List<Map<String,Object>> newUserMinutelist = new ArrayList<Map<String,Object>>();
//        List<Long> dataList = new ArrayList<Long>();
//        dataList.add(45l);
//        dataList.add(65l);
//        dataList.add(25l);
//        dataList.add(35l);
//        dataList.add(15l);
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("name","pc端");
//        map.put("data",dataList);
//        newUserMinutelist.add(map);
//        dataList = new ArrayList<Long>();
//        dataList.add(75l);
//        dataList.add(165l);
//        dataList.add(5l);
//        dataList.add(25l);
//        dataList.add(85l);
//        map = new HashMap<String,Object>();
//        map.put("name","小程序端");
//        map.put("data",dataList);
//        newUserMinutelist.add(map);
//        LiuLiangResult liuLiangResult = new LiuLiangResult();
//        liuLiangResult.setXlist(xlist);
//        liuLiangResult.setNewuserslist(newUserMinutelist);
//        String finalResult = JSONObject.toJSONString(liuLiangResult);


        LiuLiangResult liuLiangResult = liuLiangService.newusersMinute();
        String finalResult = JSONObject.toJSONString(liuLiangResult);

        return finalResult;
    }


    @RequestMapping(value = "timesHour",method = RequestMethod.POST)
    public String timesHour(){
        LiuLiangResult liuLiangResult =liuLiangService.timesHour();
        String finalResult = JSONObject.toJSONString(liuLiangResult);
        return finalResult;
    }

    @RequestMapping(value = "timesMinute",method = RequestMethod.POST)
    public String timesMinute(){
        LiuLiangResult liuLiangResult =liuLiangService.timesMinute();
        String finalResult = JSONObject.toJSONString(liuLiangResult);
        return finalResult;
    }

    @RequestMapping(value = "usernumsMinute",method = RequestMethod.POST)
    public String usernumsMinute(){
        LiuLiangResult liuLiangResult =liuLiangService.timesMinute();
        String finalResult = JSONObject.toJSONString(liuLiangResult);
        return finalResult;
    }

    @RequestMapping(value = "usernumsHour",method = RequestMethod.POST)
    public String usernumsHour(){
        LiuLiangResult liuLiangResult =liuLiangService.timesMinute();
        String finalResult = JSONObject.toJSONString(liuLiangResult);
        return finalResult;
    }

    @RequestMapping(value = "hourActivenumsMinute",method = RequestMethod.POST)
    public String hourActivenumsMinute(){
        LiuLiangResult liuLiangResult =liuLiangService.hourActivenumsMinute();
        String finalResult = JSONObject.toJSONString(liuLiangResult);
        return finalResult;
    }
    @RequestMapping(value = "hourActivenumsHour",method = RequestMethod.POST)
    public String hourActivenumsHour(){
        LiuLiangResult liuLiangResult =liuLiangService.hourActivenumsHour();
        String finalResult = JSONObject.toJSONString(liuLiangResult);
        return finalResult;
    }

    @RequestMapping(value = "dayActivenumsMinute",method = RequestMethod.POST)
    public String dayActivenumsMinute(){
        LiuLiangResult liuLiangResult =liuLiangService.dayActivenumsMinute();
        String finalResult = JSONObject.toJSONString(liuLiangResult);
        return finalResult;
    }

    @RequestMapping(value = "dayActivenumsHour",method = RequestMethod.POST)
    public String dayActivenumsHour(){
        LiuLiangResult liuLiangResult =liuLiangService.dayActivenumsHour();
        String finalResult = JSONObject.toJSONString(liuLiangResult);
        return finalResult;
    }

    @RequestMapping(value = "monthActivenumsMinute",method = RequestMethod.POST)
    public String monthActivenumsMinute(){
        LiuLiangResult liuLiangResult =liuLiangService.monthActivenumsMinute();
        String finalResult = JSONObject.toJSONString(liuLiangResult);
        return finalResult;
    }

    @RequestMapping(value = "monthActivenumsHour",method = RequestMethod.POST)
    public String monthActivenumsHour(){
        LiuLiangResult liuLiangResult =liuLiangService.monthActivenumsHour();
        String finalResult = JSONObject.toJSONString(liuLiangResult);
        return finalResult;
    }

    @RequestMapping(value = "weekActivenumsMinute",method = RequestMethod.POST)
    public String weekActivenumsMinute(){
        LiuLiangResult liuLiangResult =liuLiangService.weekActivenumsMinute();
        String finalResult = JSONObject.toJSONString(liuLiangResult);
        return finalResult;
    }

    @RequestMapping(value = "weekActivenumsHour",method = RequestMethod.POST)
    public String weekActivenumsHour(){
        LiuLiangResult liuLiangResult =liuLiangService.weekActivenumsHour();
        String finalResult = JSONObject.toJSONString(liuLiangResult);
        return finalResult;
    }

    @RequestMapping(value = "usersDetailHour",method = RequestMethod.POST)
    public String usersDetailHour(String timeinfo){
//        System.out.println(timeinfo);
//        LiuLiangResult liuLiangResult = new LiuLiangResult();
//        List<LiuLiangInfo> userDetailList = new ArrayList<LiuLiangInfo>();
//        LiuLiangInfo liuLiangInfo = new LiuLiangInfo();
//        liuLiangInfo.setTimeinfo("2018090501");
//        liuLiangInfo.setUserName("小白");
//        liuLiangInfo.setDeviceName("pc端");
//        userDetailList.add(liuLiangInfo);
//        liuLiangInfo = new LiuLiangInfo();
//        liuLiangInfo.setTimeinfo("2018090501");
//        liuLiangInfo.setUserName("小花");
//        liuLiangInfo.setDeviceName("小程序端");
//        userDetailList.add(liuLiangInfo);
//        liuLiangResult.setUserDetailList(userDetailList);
//        String finalResult = JSONObject.toJSONString(liuLiangResult);
        List<LiuLiangInfo> userDetailList = liuLiangService.usersDetailHour(timeinfo);
        String userDetail = JSONObject.toJSONString(userDetailList);
        return userDetail;
    }


}
