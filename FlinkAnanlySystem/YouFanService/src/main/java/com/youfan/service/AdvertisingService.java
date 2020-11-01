package com.youfan.service;

import com.youfan.entity.AdvertisingInfo;
import com.youfan.entity.ChannelInfo;
import com.youfan.entity.LiuLiangInfo;
import com.youfan.utils.ClickHouseUtils;
import com.youfan.utils.DateUtils;
import com.youfan.utils.HbaseUtils2;
import com.youfan.utils.HiveUtils;
import com.youfan.viewResult.AdvertisingResult;
import com.youfan.viewResult.ChannelResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2020/3/3.
 */
@Service
public class AdvertisingService {
    static Map<String,String> deviceMap = new HashMap<String,String>();
    static {
        deviceMap.put("0","app端");
        deviceMap.put("1","pc端");
        deviceMap.put("2","小程序端");
    }

    public AdvertisingResult timesHour(String adId,String productId){
        AdvertisingResult advertisingResult = new AdvertisingResult();
        //todo 时间限定
        String sql = "select timeinfoString,adId,productId,sum(times) as timestotal from AdvertisingInfo where length(toString(timeinfoString)) < 12 " +
                "and length(toString(timeinfoString)) >= 10  and adId =  '"+adId+"' and productId = '"+productId+ "'  group by timeinfoString,adId,productId order by timeinfoString;";
        System.out.println(sql);
        Map<String,Long> mapDiveceMap = new HashMap<String,Long>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                Long timestotal = resultSet.getLong("timestotal");
                dateSet.add(timeinfoString);
                Long data =mapDiveceMap.get(timeinfoString)==null?0l:mapDiveceMap.get(timeinfoString);
                data = data+ timestotal;
                mapDiveceMap.put(timeinfoString,data);
            }
            List<String> sortList = new ArrayList<String>(dateSet);
            Collections.sort(sortList, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int reuslt = 0;
                    try {
                        reuslt = DateUtils.compareDate(o1,o2,"yyyyMMddHH");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return reuslt;
                }
            });
            Map<String,Long> dateMaptemp = new HashMap<String,Long>();
            for(String timeinfoString:sortList){
                dateMaptemp.put(timeinfoString,0l);
            }

            Set<Map.Entry<String,Long>> dataMapSet = mapDiveceMap.entrySet();


            Map<String,Long> deepCopy = new HashMap<String,Long>();
            deepCopy.putAll(dateMaptemp);
            for(Map.Entry<String,Long> entry:dataMapSet){

                String key = entry.getKey();
                Long value = entry.getValue();
                deepCopy.put(key,value);


            }
            List<Long> dataList = new ArrayList<Long>();
            for(String key :sortList){
                Long value = deepCopy.get(key);
                dataList.add(value);
            }
            List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
            Map<String,Object> maptemp = new HashMap<String,Object>();
            maptemp.put("name","广告点击次数");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            advertisingResult.setXlist(sortList);
            advertisingResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  advertisingResult;
    }


    public AdvertisingResult timesMinute(String adId,String productId){
        AdvertisingResult advertisingResult = new AdvertisingResult();
        //todo 时间限定
        String sql = "select timeinfoString,adId,productId,sum(times) as timestotal from AdvertisingInfo where  length(toString(timeinfoString)) >= 12 and adId =  '"+adId+"' and productId = '"+productId+"'"+
        " group by timeinfoString,adId,productId order by timeinfoString;";
        Map<String,Long> mapDiveceMap = new HashMap<String,Long>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                Long timestotal = resultSet.getLong("timestotal");
                dateSet.add(timeinfoString);
                Long data =mapDiveceMap.get(timeinfoString)==null?0l:mapDiveceMap.get(timeinfoString);
                data = data+ timestotal;
                mapDiveceMap.put(timeinfoString,data);
            }
            List<String> sortList = new ArrayList<String>(dateSet);
            Collections.sort(sortList, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int reuslt = 0;
                    try {
                        reuslt = DateUtils.compareDate(o1,o2,"yyyyMMddHHmm");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return reuslt;
                }
            });
            Map<String,Long> dateMaptemp = new HashMap<String,Long>();
            for(String timeinfoString:sortList){
                dateMaptemp.put(timeinfoString,0l);
            }

            Set<Map.Entry<String,Long>> dataMapSet = mapDiveceMap.entrySet();
            Map<String,Long> deepCopy = new HashMap<String,Long>();
            deepCopy.putAll(dateMaptemp);
            for(Map.Entry<String,Long> entry:dataMapSet){

                String key = entry.getKey();
                Long value = entry.getValue();
                deepCopy.put(key,value);


            }
            List<Long> dataList = new ArrayList<Long>();
            for(String key :sortList){
                Long value = deepCopy.get(key);
                dataList.add(value);
            }
            List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
            Map<String,Object> maptemp = new HashMap<String,Object>();
            maptemp.put("name","广告点击次数");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            advertisingResult.setXlist(sortList);
            advertisingResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  advertisingResult;
    }

    public AdvertisingResult userNumsHour(String adId,String productId){
        AdvertisingResult advertisingResult = new AdvertisingResult();
        //todo 时间限定
        String sql = "select timeinfoString,adId,productId,sum(userNums) as userNumstotal from AdvertisingInfo where length(toString(timeinfoString)) < 12 " +
                "and length(toString(timeinfoString)) >= 10  and adId =  '"+adId+   "' and productId = '"+productId+"'  group by timeinfoString,adId,productId order by timeinfoString;";
        Map<String,Long> mapDiveceMap = new HashMap<String,Long>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                Long userNumstotal = resultSet.getLong("userNumstotal");
                dateSet.add(timeinfoString);
                Long data =mapDiveceMap.get(timeinfoString)==null?0l:mapDiveceMap.get(timeinfoString);
                data = data+ userNumstotal;
                mapDiveceMap.put(timeinfoString,data);
            }
            List<String> sortList = new ArrayList<String>(dateSet);
            Collections.sort(sortList, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int reuslt = 0;
                    try {
                        reuslt = DateUtils.compareDate(o1,o2,"yyyyMMddHH");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return reuslt;
                }
            });
            Map<String,Long> dateMaptemp = new HashMap<String,Long>();
            for(String timeinfoString:sortList){
                dateMaptemp.put(timeinfoString,0l);
            }

            Set<Map.Entry<String,Long>> dataMapSet = mapDiveceMap.entrySet();


            Map<String,Long> deepCopy = new HashMap<String,Long>();
            deepCopy.putAll(dateMaptemp);
            for(Map.Entry<String,Long> entry:dataMapSet){

                String key = entry.getKey();
                Long value = entry.getValue();
                deepCopy.put(key,value);


            }
            List<Long> dataList = new ArrayList<Long>();
            for(String key :sortList){
                Long value = deepCopy.get(key);
                dataList.add(value);
            }
            List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
            Map<String,Object> maptemp = new HashMap<String,Object>();
            maptemp.put("name","广告点击用户数");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            advertisingResult.setXlist(sortList);
            advertisingResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  advertisingResult;
    }


    public AdvertisingResult userNumsMinute(String adId,String productId){
        AdvertisingResult advertisingResult = new AdvertisingResult();
        //todo 时间限定
        String sql = "select timeinfoString,adId,productId,sum(userNums) as userNumstotal from AdvertisingInfo where  length(toString(timeinfoString)) >= 12 " +
                "and adId =  '"+adId+   "' and productId = '"+productId+"'  group by timeinfoString,adId,productId order by timeinfoString;";
        Map<String,Long> mapDiveceMap = new HashMap<String,Long>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                Long userNumstotal = resultSet.getLong("userNumstotal");
                dateSet.add(timeinfoString);
                Long data =mapDiveceMap.get(timeinfoString)==null?0l:mapDiveceMap.get(timeinfoString);
                data = data+ userNumstotal;
                mapDiveceMap.put(timeinfoString,data);
            }
            List<String> sortList = new ArrayList<String>(dateSet);
            Collections.sort(sortList, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int reuslt = 0;
                    try {
                        reuslt = DateUtils.compareDate(o1,o2,"yyyyMMddHHmm");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return reuslt;
                }
            });
            Map<String,Long> dateMaptemp = new HashMap<String,Long>();
            for(String timeinfoString:sortList){
                dateMaptemp.put(timeinfoString,0l);
            }

            Set<Map.Entry<String,Long>> dataMapSet = mapDiveceMap.entrySet();
            Map<String,Long> deepCopy = new HashMap<String,Long>();
            deepCopy.putAll(dateMaptemp);
            for(Map.Entry<String,Long> entry:dataMapSet){

                String key = entry.getKey();
                Long value = entry.getValue();
                deepCopy.put(key,value);


            }
            List<Long> dataList = new ArrayList<Long>();
            for(String key :sortList){
                Long value = deepCopy.get(key);
                dataList.add(value);
            }
            List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
            Map<String,Object> maptemp = new HashMap<String,Object>();
            maptemp.put("name","广告点击用户数");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            advertisingResult.setXlist(sortList);
            advertisingResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  advertisingResult;
    }

    public AdvertisingResult userOrderNumsHour(String adId,String productId){
        AdvertisingResult advertisingResult = new AdvertisingResult();
        //todo 时间限定
        String sql = "select timeinfoString,adId,productId,sum(userNums) as userNumstotal," +
                "sum(userOrderNums) as userOrderNumstotal from AdvertisingInfo where  length(toString(timeinfoString)) < 12  and length(toString(timeinfoString)) >= 10 and adId =  '"+adId+"' and productId = '"+productId+"' group by timeinfoString,adId,productId order by timeinfoString;";
        Map<String,Long> mapUserNumsMap = new HashMap<String,Long>();
        Map<String,Long> mapuserOrderNumsMap = new HashMap<String,Long>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                Long userNumstotal = resultSet.getLong("userNumstotal");
                Long userOrderNumstotal = resultSet.getLong("userOrderNumstotal");
                dateSet.add(timeinfoString);
                Long userNumsData = mapUserNumsMap.get(timeinfoString)==null?0l:mapUserNumsMap.get(timeinfoString);
                Long userOrderNumsData = mapuserOrderNumsMap.get(timeinfoString)==null?0l:mapuserOrderNumsMap.get(timeinfoString);
                userNumsData = userNumsData+ userNumstotal;
                userOrderNumsData = userOrderNumsData+ userOrderNumstotal;
                mapUserNumsMap.put(timeinfoString,userNumsData);
                mapuserOrderNumsMap.put(timeinfoString,userOrderNumsData);
            }
            List<String> sortList = new ArrayList<String>(dateSet);
            Collections.sort(sortList, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int reuslt = 0;
                    try {
                        reuslt = DateUtils.compareDate(o1,o2,"yyyyMMddHH");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return reuslt;
                }
            });
            Map<String,Double> dateMaptemp = new HashMap<String,Double>();
            for(String timeinfoString:sortList){
                dateMaptemp.put(timeinfoString,0.0d);
            }

            Set<Map.Entry<String,Long>> dataMapSet = mapUserNumsMap.entrySet();
            Map<String,Double> deepCopy = new HashMap<String,Double>();
            deepCopy.putAll(dateMaptemp);
            for(Map.Entry<String,Long> entry:dataMapSet){

                String key = entry.getKey();
                Long value = entry.getValue();
                Long ordernums = mapuserOrderNumsMap.get(key);
                BigDecimal bigDecimal1 = new BigDecimal(value);
                BigDecimal bigDecimal2 = new BigDecimal(ordernums);
                if(value==0){
                    deepCopy.put(key,0.0);
                }else {
                    BigDecimal bigDecimal3 = bigDecimal2.divide(bigDecimal1, 6, BigDecimal.ROUND_HALF_UP);
                    Double resulttemp = bigDecimal3.doubleValue();
                    deepCopy.put(key, resulttemp);
                }
            }
            List<Double> dataList = new ArrayList<Double>();
            for(String key :sortList){
                Double value = deepCopy.get(key);
                dataList.add(value);
            }
            List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
            Map<String,Object> maptemp = new HashMap<String,Object>();
            maptemp.put("name","广告点击用户数");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            advertisingResult.setXlist(sortList);
            advertisingResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  advertisingResult;
    }
    public List<AdvertisingInfo> userdetailHour(String timeinfoPara,String adId,String productId) {
        List<AdvertisingInfo> userDetailList = new ArrayList<AdvertisingInfo>();
        //2019090801,create "userinfo","info"
        String dayinfo = timeinfoPara.substring(0, 8);
        String hourinfo = timeinfoPara.substring(8, 10);
        String sql = "select deviceType,timeinfo,userId,sum(times) as times from AdvertisingUserInfo " +
                " where dayinfo = " + dayinfo + " and hourinfo = " + hourinfo + " and adId = " + adId + "  and productId = " + productId + " group by deviceType,timeinfo,userId";
        try {
            ResultSet resultSet = HiveUtils.getQueryResult(sql);
            while (resultSet.next()) {
                String deviceType = resultSet.getString("deviceType");
                String timeinfo = resultSet.getString("timeinfo");
                String userId = resultSet.getString("userId");
                String userName = HbaseUtils2.getdata("userinfo", userId, "info", "userName");
                String productName = HbaseUtils2.getdata("product", productId, "info", "productName");
                String deviceName = deviceMap.get(deviceType);
                Long times = resultSet.getLong("times");
                AdvertisingInfo advertisingInfo = new AdvertisingInfo();
                advertisingInfo.setUserName(userName);
                advertisingInfo.setProductName(productName);
                advertisingInfo.setTimeinfo(timeinfo);
                advertisingInfo.setDeviceName(deviceName);
                advertisingInfo.setTimes(times);
                userDetailList.add(advertisingInfo);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDetailList;
    }

}