package com.youfan.control;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.AdvertisingInfo;
import com.youfan.entity.ChannelInfo;
import com.youfan.service.AdvertisingService;
import com.youfan.service.ChannelService;
import com.youfan.utils.ClickHouseUtils;
import com.youfan.utils.DateUtils;
import com.youfan.viewResult.AdVertisingListResult;
import com.youfan.viewResult.AdvertisingList;
import com.youfan.viewResult.AdvertisingResult;
import com.youfan.viewResult.ChannelResult;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("advertising")
@CrossOrigin
public class AdvertisingControl {

    @Autowired
    private AdvertisingService advertisingService;

    @RequestMapping(value = "timesHour",method = RequestMethod.POST)
    public String timesHour(String adId, String productId){
        AdvertisingResult advertisingResult = advertisingService.timesHour(adId,productId);
        String resultString = JSONObject.toJSONString(advertisingResult);
        return resultString;
    }

    @RequestMapping(value = "timesMinute",method = RequestMethod.POST)
    public String  timesMinute(String adId,String productId){
        AdvertisingResult advertisingResult = advertisingService.timesHour(adId,productId);
        String resultString = JSONObject.toJSONString(advertisingResult);
        return resultString;
    }
    @RequestMapping(value = "userNumsHour",method = RequestMethod.POST)
    public String  userNumsHour(String adId,String productId){
        AdvertisingResult advertisingResult = advertisingService.timesHour(adId,productId);
        String resultString = JSONObject.toJSONString(advertisingResult);
        return resultString;
    }

    @RequestMapping(value = "userNumsMinute",method = RequestMethod.POST)
    public String  userNumsMinute(String adId,String productId){
        AdvertisingResult advertisingResult = advertisingService.timesHour(adId,productId);
        String resultString = JSONObject.toJSONString(advertisingResult);
        return resultString;

    }

    @RequestMapping(value = "listAdvertisingBy",method = RequestMethod.POST)
    public String  listAdvertisingBy(String productName,String adName){
        //todo 请求第三方内部业务系统接口，查询数据
        System.out.println("productName:"+productName+"=="+"adName:"+adName);
        List<AdvertisingList> resultList = new ArrayList<AdvertisingList>();
        AdvertisingList advertisingList = new AdvertisingList();
        advertisingList.setAdId("1");
        advertisingList.setProductId("1");
        advertisingList.setProductName("牙膏");
        advertisingList.setAdName("牙膏真棒");
        resultList.add(advertisingList);
        advertisingList = new AdvertisingList();
        advertisingList.setAdId("2");
        advertisingList.setProductId("2");
        advertisingList.setProductName("玉米");
        advertisingList.setAdName("超级香的玉米");
        resultList.add(advertisingList);
        AdVertisingListResult adVertisingListResult = new AdVertisingListResult();
        adVertisingListResult.setAdvertisingList(resultList);
        String resultString = JSONObject.toJSONString(adVertisingListResult);
        return resultString;

    }

    @RequestMapping(value = "userOrderNumsHour",method = RequestMethod.POST)
    public String userOrderNumsHour(String adId,String productId){
        AdvertisingResult advertisingResult = advertisingService.userOrderNumsHour(adId,productId);
        String resultString = JSONObject.toJSONString(advertisingResult);
        return resultString;
    }

    @RequestMapping(value = "userdetailHour",method = RequestMethod.POST)
    public String userdetailHour(String timeinfoPara, String adId, String productId) {
        List<AdvertisingInfo> list = advertisingService.userdetailHour(timeinfoPara,adId,productId);
        String resultString = JSONObject.toJSONString(list);
        return  resultString;
    }


}