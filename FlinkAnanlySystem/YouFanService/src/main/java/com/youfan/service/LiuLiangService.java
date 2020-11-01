package com.youfan.service;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.LiuLiangInfo;
import com.youfan.utils.ClickHouseUtils;
import com.youfan.utils.DateUtils;
import com.youfan.utils.HbaseUtils2;
import com.youfan.utils.HiveUtils;
import com.youfan.viewResult.LiuLiangResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.ResultSet;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2020/3/3.
 */
@Service
public class LiuLiangService {
    static Map<String,String> deviceMap = new HashMap<String,String>();
        static {
            deviceMap.put("0","app端");
            deviceMap.put("1","pc端");
            deviceMap.put("2","小程序端");
        }

    public LiuLiangResult newusersHour(){
        LiuLiangResult liuLiangResult = new LiuLiangResult();
        //todo 时间限定
        String sql = "select timeinfoString,deviceType,sum(newusers) as newusertotal from LiuLiangInfo where length(toString(timeinfoString)) < 12 " +
                "and length(toString(timeinfoString)) >= 10  group by timeinfoString,deviceType order by timeinfoString,deviceType;";
        Map<String,Map<String,Long>> mapDiveceMap = new HashMap<String,Map<String,Long>>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                String deviceType = resultSet.getString("deviceType");
                String deviceName = deviceMap.get(deviceType);
                Long newusertotal = resultSet.getLong("newusertotal");
                dateSet.add(timeinfoString);
                Map<String,Long> innerMap = mapDiveceMap.get(deviceName);
                if(innerMap == null){
                    innerMap = new HashMap<String,Long>();
                }
                Long data =innerMap.get(timeinfoString)==null?0l:innerMap.get(timeinfoString);
                data = data+ newusertotal;
                innerMap.put(timeinfoString,data);
                mapDiveceMap.put(deviceName,innerMap);
                System.out.println(timeinfoString +"=="+deviceType+"=="+newusertotal);
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

            Set<Map.Entry<String,Map<String,Long>>> dataMapSet = mapDiveceMap.entrySet();

            List<Map<String,Object>> newUserHourlist = new ArrayList<Map<String,Object>>();
            for(Map.Entry<String,Map<String,Long>> entry:dataMapSet){
                String name = entry.getKey();
                Map<String,Long> datamap = entry.getValue();
                Set<Map.Entry<String,Long>> setinner = datamap.entrySet();
                Map<String,Long> deepCopy = new HashMap<String,Long>();
                deepCopy.putAll(dateMaptemp);
                for(Map.Entry<String,Long>  innerMap : setinner){
                    String key = innerMap.getKey();
                    Long value = innerMap.getValue();
                    deepCopy.put(key,value);
                }
                List<Long> dataList = new ArrayList<Long>();
                for(String key :sortList){
                    Long value = deepCopy.get(key);
                    dataList.add(value);
                }
                Map<String,Object> maptemp = new HashMap<String,Object>();
                maptemp.put("name",name);
                maptemp.put("data",dataList);
                newUserHourlist.add(maptemp);
            }



            liuLiangResult.setXlist(sortList);
            liuLiangResult.setNewuserslist(newUserHourlist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  liuLiangResult;
    }

    public LiuLiangResult newusersMinute(){
        LiuLiangResult liuLiangResult = new LiuLiangResult();
        //todo 时间限定
        String sql = "select timeinfoString,deviceType,sum(newusers) as newusertotal from LiuLiangInfo where " +
                " length(toString(timeinfoString)) >= 12  group by timeinfoString,deviceType order by timeinfoString,deviceType;";
        Map<String,Map<String,Long>> mapDiveceMap = new HashMap<String,Map<String,Long>>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                String deviceType = resultSet.getString("deviceType");
                String deviceName = deviceMap.get(deviceType);
                Long newusertotal = resultSet.getLong("newusertotal");
                dateSet.add(timeinfoString);
                Map<String,Long> innerMap = mapDiveceMap.get(deviceName);
                if(innerMap == null){
                    innerMap = new HashMap<String,Long>();
                }
                Long data =innerMap.get(timeinfoString)==null?0l:innerMap.get(timeinfoString);
                data = data+ newusertotal;
                innerMap.put(timeinfoString,data);
                mapDiveceMap.put(deviceName,innerMap);
                System.out.println(timeinfoString +"=="+deviceType+"=="+newusertotal);
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

            Set<Map.Entry<String,Map<String,Long>>> dataMapSet = mapDiveceMap.entrySet();

            List<Map<String,Object>> newUserMinutelist = new ArrayList<Map<String,Object>>();
            for(Map.Entry<String,Map<String,Long>> entry:dataMapSet){
                String name = entry.getKey();
                Map<String,Long> datamap = entry.getValue();
                Set<Map.Entry<String,Long>> setinner = datamap.entrySet();
                Map<String,Long> deepCopy = new HashMap<String,Long>();
                deepCopy.putAll(dateMaptemp);
                for(Map.Entry<String,Long>  innerMap : setinner){
                    String key = innerMap.getKey();
                    Long value = innerMap.getValue();
                    deepCopy.put(key,value);
                }
                List<Long> dataList = new ArrayList<Long>();
                for(String key :sortList){
                    Long value = deepCopy.get(key);
                    dataList.add(value);
                }
                Map<String,Object> maptemp = new HashMap<String,Object>();
                maptemp.put("name",name);
                maptemp.put("data",dataList);
                newUserMinutelist.add(maptemp);
            }



            liuLiangResult.setXlist(sortList);
            liuLiangResult.setNewuserslist(newUserMinutelist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  liuLiangResult;
    }

    public LiuLiangResult timesHour(){
        LiuLiangResult liuLiangResult = new LiuLiangResult();
        //todo 时间限定
        String sql = "select timeinfoString,deviceType,sum(times) as timestotal from LiuLiangInfo where length(toString(timeinfoString)) < 12 " +
                "and length(toString(timeinfoString)) >= 10  group by timeinfoString,deviceType order by timeinfoString,deviceType;";
        Map<String,Map<String,Long>> mapDiveceMap = new HashMap<String,Map<String,Long>>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                String deviceType = resultSet.getString("deviceType");
                String deviceName = deviceMap.get(deviceType);
                Long timestotal = resultSet.getLong("timestotal");
                dateSet.add(timeinfoString);
                Map<String,Long> innerMap = mapDiveceMap.get(deviceName);
                if(innerMap == null){
                    innerMap = new HashMap<String,Long>();
                }
                Long data =innerMap.get(timeinfoString)==null?0l:innerMap.get(timeinfoString);
                data = data+ timestotal;
                innerMap.put(timeinfoString,data);
                mapDiveceMap.put(deviceName,innerMap);
                System.out.println(timeinfoString +"=="+deviceType+"=="+timestotal);
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

            Set<Map.Entry<String,Map<String,Long>>> dataMapSet = mapDiveceMap.entrySet();

            List<Map<String,Object>> newUserHourlist = new ArrayList<Map<String,Object>>();
            for(Map.Entry<String,Map<String,Long>> entry:dataMapSet){
                String name = entry.getKey();
                Map<String,Long> datamap = entry.getValue();
                Set<Map.Entry<String,Long>> setinner = datamap.entrySet();
                Map<String,Long> deepCopy = new HashMap<String,Long>();
                deepCopy.putAll(dateMaptemp);
                for(Map.Entry<String,Long>  innerMap : setinner){
                    String key = innerMap.getKey();
                    Long value = innerMap.getValue();
                    deepCopy.put(key,value);
                }
                List<Long> dataList = new ArrayList<Long>();
                for(String key :sortList){
                    Long value = deepCopy.get(key);
                    dataList.add(value);
                }
                Map<String,Object> maptemp = new HashMap<String,Object>();
                maptemp.put("name",name);
                maptemp.put("data",dataList);
                newUserHourlist.add(maptemp);
            }



            liuLiangResult.setXlist(sortList);
            liuLiangResult.setNewuserslist(newUserHourlist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  liuLiangResult;
    }

    public LiuLiangResult timesMinute(){
        LiuLiangResult liuLiangResult = new LiuLiangResult();
        //todo 时间限定
        String sql = "select timeinfoString,deviceType,sum(times) as timestotal from LiuLiangInfo where " +
                " length(toString(timeinfoString)) >= 12  group by timeinfoString,deviceType order by timeinfoString,deviceType;";
        Map<String,Map<String,Long>> mapDiveceMap = new HashMap<String,Map<String,Long>>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                String deviceType = resultSet.getString("deviceType");
                String deviceName = deviceMap.get(deviceType);
                Long timestotal = resultSet.getLong("timestotal");
                dateSet.add(timeinfoString);
                Map<String,Long> innerMap = mapDiveceMap.get(deviceName);
                if(innerMap == null){
                    innerMap = new HashMap<String,Long>();
                }
                Long data =innerMap.get(timeinfoString)==null?0l:innerMap.get(timeinfoString);
                data = data+ timestotal;
                innerMap.put(timeinfoString,data);
                mapDiveceMap.put(deviceName,innerMap);
                System.out.println(timeinfoString +"=="+deviceType+"=="+timestotal);
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

            Set<Map.Entry<String,Map<String,Long>>> dataMapSet = mapDiveceMap.entrySet();

            List<Map<String,Object>> newUserMinutelist = new ArrayList<Map<String,Object>>();
            for(Map.Entry<String,Map<String,Long>> entry:dataMapSet){
                String name = entry.getKey();
                Map<String,Long> datamap = entry.getValue();
                Set<Map.Entry<String,Long>> setinner = datamap.entrySet();
                Map<String,Long> deepCopy = new HashMap<String,Long>();
                deepCopy.putAll(dateMaptemp);
                for(Map.Entry<String,Long>  innerMap : setinner){
                    String key = innerMap.getKey();
                    Long value = innerMap.getValue();
                    deepCopy.put(key,value);
                }
                List<Long> dataList = new ArrayList<Long>();
                for(String key :sortList){
                    Long value = deepCopy.get(key);
                    dataList.add(value);
                }
                Map<String,Object> maptemp = new HashMap<String,Object>();
                maptemp.put("name",name);
                maptemp.put("data",dataList);
                newUserMinutelist.add(maptemp);
            }



            liuLiangResult.setXlist(sortList);
            liuLiangResult.setNewuserslist(newUserMinutelist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  liuLiangResult;
    }

    public LiuLiangResult usernumsMinute(){
        LiuLiangResult liuLiangResult = new LiuLiangResult();
        //todo 时间限定
        String sql = "select timeinfoString,deviceType,sum(userNums) as userNumstotal from LiuLiangInfo where " +
                " length(toString(timeinfoString)) >= 12  group by timeinfoString,deviceType order by timeinfoString,deviceType;";
        Map<String,Map<String,Long>> mapDiveceMap = new HashMap<String,Map<String,Long>>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                String deviceType = resultSet.getString("deviceType");
                String deviceName = deviceMap.get(deviceType);
                Long userNumstotal = resultSet.getLong("userNumstotal");
                dateSet.add(timeinfoString);
                Map<String,Long> innerMap = mapDiveceMap.get(deviceName);
                if(innerMap == null){
                    innerMap = new HashMap<String,Long>();
                }
                Long data =innerMap.get(timeinfoString)==null?0l:innerMap.get(timeinfoString);
                data = data+ userNumstotal;
                innerMap.put(timeinfoString,data);
                mapDiveceMap.put(deviceName,innerMap);
                System.out.println(timeinfoString +"=="+deviceType+"=="+userNumstotal);
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

            Set<Map.Entry<String,Map<String,Long>>> dataMapSet = mapDiveceMap.entrySet();

            List<Map<String,Object>> newUserMinutelist = new ArrayList<Map<String,Object>>();
            for(Map.Entry<String,Map<String,Long>> entry:dataMapSet){
                String name = entry.getKey();
                Map<String,Long> datamap = entry.getValue();
                Set<Map.Entry<String,Long>> setinner = datamap.entrySet();
                Map<String,Long> deepCopy = new HashMap<String,Long>();
                deepCopy.putAll(dateMaptemp);
                for(Map.Entry<String,Long>  innerMap : setinner){
                    String key = innerMap.getKey();
                    Long value = innerMap.getValue();
                    deepCopy.put(key,value);
                }
                List<Long> dataList = new ArrayList<Long>();
                for(String key :sortList){
                    Long value = deepCopy.get(key);
                    dataList.add(value);
                }
                Map<String,Object> maptemp = new HashMap<String,Object>();
                maptemp.put("name",name);
                maptemp.put("data",dataList);
                newUserMinutelist.add(maptemp);
            }



            liuLiangResult.setXlist(sortList);
            liuLiangResult.setNewuserslist(newUserMinutelist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  liuLiangResult;

    }

    public LiuLiangResult usernumsHour(){
        LiuLiangResult liuLiangResult = new LiuLiangResult();
        //todo 时间限定
        String sql = "select timeinfoString,deviceType,sum(userNums) as userNumstotal from LiuLiangInfo where length(toString(timeinfoString)) < 12 " +
                "and length(toString(timeinfoString)) >= 10  group by timeinfoString,deviceType order by timeinfoString,deviceType;";
        Map<String,Map<String,Long>> mapDiveceMap = new HashMap<String,Map<String,Long>>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                String deviceType = resultSet.getString("deviceType");
                String deviceName = deviceMap.get(deviceType);
                Long userNumstotal = resultSet.getLong("userNumstotal");
                dateSet.add(timeinfoString);
                Map<String,Long> innerMap = mapDiveceMap.get(deviceName);
                if(innerMap == null){
                    innerMap = new HashMap<String,Long>();
                }
                Long data =innerMap.get(timeinfoString)==null?0l:innerMap.get(timeinfoString);
                data = data+ userNumstotal;
                innerMap.put(timeinfoString,data);
                mapDiveceMap.put(deviceName,innerMap);
                System.out.println(timeinfoString +"=="+deviceType+"=="+userNumstotal);
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

            Set<Map.Entry<String,Map<String,Long>>> dataMapSet = mapDiveceMap.entrySet();

            List<Map<String,Object>> newUserHourlist = new ArrayList<Map<String,Object>>();
            for(Map.Entry<String,Map<String,Long>> entry:dataMapSet){
                String name = entry.getKey();
                Map<String,Long> datamap = entry.getValue();
                Set<Map.Entry<String,Long>> setinner = datamap.entrySet();
                Map<String,Long> deepCopy = new HashMap<String,Long>();
                deepCopy.putAll(dateMaptemp);
                for(Map.Entry<String,Long>  innerMap : setinner){
                    String key = innerMap.getKey();
                    Long value = innerMap.getValue();
                    deepCopy.put(key,value);
                }
                List<Long> dataList = new ArrayList<Long>();
                for(String key :sortList){
                    Long value = deepCopy.get(key);
                    dataList.add(value);
                }
                Map<String,Object> maptemp = new HashMap<String,Object>();
                maptemp.put("name",name);
                maptemp.put("data",dataList);
                newUserHourlist.add(maptemp);
            }



            liuLiangResult.setXlist(sortList);
            liuLiangResult.setNewuserslist(newUserHourlist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  liuLiangResult;
    }

    public LiuLiangResult hourActivenumsMinute(){
        LiuLiangResult liuLiangResult = new LiuLiangResult();
        //todo 时间限定
        String sql = "select timeinfoString,deviceType,sum(hourActivenums) as hourActivenumstotal from LiuLiangInfo where " +
                " length(toString(timeinfoString)) >= 12  group by timeinfoString,deviceType order by timeinfoString,deviceType;";
        Map<String,Map<String,Long>> mapDiveceMap = new HashMap<String,Map<String,Long>>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                String deviceType = resultSet.getString("deviceType");
                String deviceName = deviceMap.get(deviceType);
                Long hourActivenumstotal = resultSet.getLong("hourActivenumstotal");
                dateSet.add(timeinfoString);
                Map<String,Long> innerMap = mapDiveceMap.get(deviceName);
                if(innerMap == null){
                    innerMap = new HashMap<String,Long>();
                }
                Long data =innerMap.get(timeinfoString)==null?0l:innerMap.get(timeinfoString);
                data = data+ hourActivenumstotal;
                innerMap.put(timeinfoString,data);
                mapDiveceMap.put(deviceName,innerMap);
                System.out.println(timeinfoString +"=="+deviceType+"=="+hourActivenumstotal);
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

            Set<Map.Entry<String,Map<String,Long>>> dataMapSet = mapDiveceMap.entrySet();

            List<Map<String,Object>> newUserMinutelist = new ArrayList<Map<String,Object>>();
            for(Map.Entry<String,Map<String,Long>> entry:dataMapSet){
                String name = entry.getKey();
                Map<String,Long> datamap = entry.getValue();
                Set<Map.Entry<String,Long>> setinner = datamap.entrySet();
                Map<String,Long> deepCopy = new HashMap<String,Long>();
                deepCopy.putAll(dateMaptemp);
                for(Map.Entry<String,Long>  innerMap : setinner){
                    String key = innerMap.getKey();
                    Long value = innerMap.getValue();
                    deepCopy.put(key,value);
                }
                List<Long> dataList = new ArrayList<Long>();
                for(String key :sortList){
                    Long value = deepCopy.get(key);
                    dataList.add(value);
                }
                Map<String,Object> maptemp = new HashMap<String,Object>();
                maptemp.put("name",name);
                maptemp.put("data",dataList);
                newUserMinutelist.add(maptemp);
            }



            liuLiangResult.setXlist(sortList);
            liuLiangResult.setNewuserslist(newUserMinutelist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  liuLiangResult;
    }
    public LiuLiangResult hourActivenumsHour(){
        LiuLiangResult liuLiangResult = new LiuLiangResult();
        //todo 时间限定
        String sql = "select timeinfoString,deviceType,sum(hourActivenums) as hourActivenumstotal from LiuLiangInfo where length(toString(timeinfoString)) < 12 " +
                "and length(toString(timeinfoString)) >= 10  group by timeinfoString,deviceType order by timeinfoString,deviceType;";
        Map<String,Map<String,Long>> mapDiveceMap = new HashMap<String,Map<String,Long>>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                String deviceType = resultSet.getString("deviceType");
                String deviceName = deviceMap.get(deviceType);
                Long hourActivenumstotal = resultSet.getLong("hourActivenumstotal");
                dateSet.add(timeinfoString);
                Map<String,Long> innerMap = mapDiveceMap.get(deviceName);
                if(innerMap == null){
                    innerMap = new HashMap<String,Long>();
                }
                Long data =innerMap.get(timeinfoString)==null?0l:innerMap.get(timeinfoString);
                data = data+ hourActivenumstotal;
                innerMap.put(timeinfoString,data);
                mapDiveceMap.put(deviceName,innerMap);
                System.out.println(timeinfoString +"=="+deviceType+"=="+hourActivenumstotal);
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

            Set<Map.Entry<String,Map<String,Long>>> dataMapSet = mapDiveceMap.entrySet();

            List<Map<String,Object>> newUserHourlist = new ArrayList<Map<String,Object>>();
            for(Map.Entry<String,Map<String,Long>> entry:dataMapSet){
                String name = entry.getKey();
                Map<String,Long> datamap = entry.getValue();
                Set<Map.Entry<String,Long>> setinner = datamap.entrySet();
                Map<String,Long> deepCopy = new HashMap<String,Long>();
                deepCopy.putAll(dateMaptemp);
                for(Map.Entry<String,Long>  innerMap : setinner){
                    String key = innerMap.getKey();
                    Long value = innerMap.getValue();
                    deepCopy.put(key,value);
                }
                List<Long> dataList = new ArrayList<Long>();
                for(String key :sortList){
                    Long value = deepCopy.get(key);
                    dataList.add(value);
                }
                Map<String,Object> maptemp = new HashMap<String,Object>();
                maptemp.put("name",name);
                maptemp.put("data",dataList);
                newUserHourlist.add(maptemp);
            }



            liuLiangResult.setXlist(sortList);
            liuLiangResult.setNewuserslist(newUserHourlist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  liuLiangResult;
    }

    public LiuLiangResult dayActivenumsMinute(){
        LiuLiangResult liuLiangResult = new LiuLiangResult();
        //todo 时间限定
        String sql = "select timeinfoString,deviceType,sum(dayActivenums) as dayActivenumstotal from LiuLiangInfo where " +
                " length(toString(timeinfoString)) >= 12  group by timeinfoString,deviceType order by timeinfoString,deviceType;";
        Map<String,Map<String,Long>> mapDiveceMap = new HashMap<String,Map<String,Long>>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                String deviceType = resultSet.getString("deviceType");
                String deviceName = deviceMap.get(deviceType);
                Long dayActivenumstotal = resultSet.getLong("dayActivenumstotal");
                dateSet.add(timeinfoString);
                Map<String,Long> innerMap = mapDiveceMap.get(deviceName);
                if(innerMap == null){
                    innerMap = new HashMap<String,Long>();
                }
                Long data =innerMap.get(timeinfoString)==null?0l:innerMap.get(timeinfoString);
                data = data+ dayActivenumstotal;
                innerMap.put(timeinfoString,data);
                mapDiveceMap.put(deviceName,innerMap);
                System.out.println(timeinfoString +"=="+deviceType+"=="+dayActivenumstotal);
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

            Set<Map.Entry<String,Map<String,Long>>> dataMapSet = mapDiveceMap.entrySet();

            List<Map<String,Object>> newUserMinutelist = new ArrayList<Map<String,Object>>();
            for(Map.Entry<String,Map<String,Long>> entry:dataMapSet){
                String name = entry.getKey();
                Map<String,Long> datamap = entry.getValue();
                Set<Map.Entry<String,Long>> setinner = datamap.entrySet();
                Map<String,Long> deepCopy = new HashMap<String,Long>();
                deepCopy.putAll(dateMaptemp);
                for(Map.Entry<String,Long>  innerMap : setinner){
                    String key = innerMap.getKey();
                    Long value = innerMap.getValue();
                    deepCopy.put(key,value);
                }
                List<Long> dataList = new ArrayList<Long>();
                for(String key :sortList){
                    Long value = deepCopy.get(key);
                    dataList.add(value);
                }
                Map<String,Object> maptemp = new HashMap<String,Object>();
                maptemp.put("name",name);
                maptemp.put("data",dataList);
                newUserMinutelist.add(maptemp);
            }



            liuLiangResult.setXlist(sortList);
            liuLiangResult.setNewuserslist(newUserMinutelist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  liuLiangResult;
    }
    public LiuLiangResult dayActivenumsHour(){
        LiuLiangResult liuLiangResult = new LiuLiangResult();
        //todo 时间限定
        String sql = "select timeinfoString,deviceType,sum(dayActivenums) as dayActivenumstotal from LiuLiangInfo where length(toString(timeinfoString)) < 12 " +
                "and length(toString(timeinfoString)) >= 10  group by timeinfoString,deviceType order by timeinfoString,deviceType;";
        Map<String,Map<String,Long>> mapDiveceMap = new HashMap<String,Map<String,Long>>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                String deviceType = resultSet.getString("deviceType");
                String deviceName = deviceMap.get(deviceType);
                Long dayActivenumstotal = resultSet.getLong("dayActivenumstotal");
                dateSet.add(timeinfoString);
                Map<String,Long> innerMap = mapDiveceMap.get(deviceName);
                if(innerMap == null){
                    innerMap = new HashMap<String,Long>();
                }
                Long data =innerMap.get(timeinfoString)==null?0l:innerMap.get(timeinfoString);
                data = data+ dayActivenumstotal;
                innerMap.put(timeinfoString,data);
                mapDiveceMap.put(deviceName,innerMap);
                System.out.println(timeinfoString +"=="+deviceType+"=="+dayActivenumstotal);
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

            Set<Map.Entry<String,Map<String,Long>>> dataMapSet = mapDiveceMap.entrySet();

            List<Map<String,Object>> newUserHourlist = new ArrayList<Map<String,Object>>();
            for(Map.Entry<String,Map<String,Long>> entry:dataMapSet){
                String name = entry.getKey();
                Map<String,Long> datamap = entry.getValue();
                Set<Map.Entry<String,Long>> setinner = datamap.entrySet();
                Map<String,Long> deepCopy = new HashMap<String,Long>();
                deepCopy.putAll(dateMaptemp);
                for(Map.Entry<String,Long>  innerMap : setinner){
                    String key = innerMap.getKey();
                    Long value = innerMap.getValue();
                    deepCopy.put(key,value);
                }
                List<Long> dataList = new ArrayList<Long>();
                for(String key :sortList){
                    Long value = deepCopy.get(key);
                    dataList.add(value);
                }
                Map<String,Object> maptemp = new HashMap<String,Object>();
                maptemp.put("name",name);
                maptemp.put("data",dataList);
                newUserHourlist.add(maptemp);
            }



            liuLiangResult.setXlist(sortList);
            liuLiangResult.setNewuserslist(newUserHourlist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  liuLiangResult;
    }

    public LiuLiangResult monthActivenumsMinute(){
        LiuLiangResult liuLiangResult = new LiuLiangResult();
        //todo 时间限定
        String sql = "select timeinfoString,deviceType,sum(monthActivenums) as monthActivenumstotal from LiuLiangInfo where " +
                " length(toString(timeinfoString)) >= 12  group by timeinfoString,deviceType order by timeinfoString,deviceType;";
        Map<String,Map<String,Long>> mapDiveceMap = new HashMap<String,Map<String,Long>>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                String deviceType = resultSet.getString("deviceType");
                String deviceName = deviceMap.get(deviceType);
                Long monthActivenumstotal = resultSet.getLong("monthActivenumstotal");
                dateSet.add(timeinfoString);
                Map<String,Long> innerMap = mapDiveceMap.get(deviceName);
                if(innerMap == null){
                    innerMap = new HashMap<String,Long>();
                }
                Long data =innerMap.get(timeinfoString)==null?0l:innerMap.get(timeinfoString);
                data = data+ monthActivenumstotal;
                innerMap.put(timeinfoString,data);
                mapDiveceMap.put(deviceName,innerMap);
                System.out.println(timeinfoString +"=="+deviceType+"=="+monthActivenumstotal);
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

            Set<Map.Entry<String,Map<String,Long>>> dataMapSet = mapDiveceMap.entrySet();

            List<Map<String,Object>> newUserMinutelist = new ArrayList<Map<String,Object>>();
            for(Map.Entry<String,Map<String,Long>> entry:dataMapSet){
                String name = entry.getKey();
                Map<String,Long> datamap = entry.getValue();
                Set<Map.Entry<String,Long>> setinner = datamap.entrySet();
                Map<String,Long> deepCopy = new HashMap<String,Long>();
                deepCopy.putAll(dateMaptemp);
                for(Map.Entry<String,Long>  innerMap : setinner){
                    String key = innerMap.getKey();
                    Long value = innerMap.getValue();
                    deepCopy.put(key,value);
                }
                List<Long> dataList = new ArrayList<Long>();
                for(String key :sortList){
                    Long value = deepCopy.get(key);
                    dataList.add(value);
                }
                Map<String,Object> maptemp = new HashMap<String,Object>();
                maptemp.put("name",name);
                maptemp.put("data",dataList);
                newUserMinutelist.add(maptemp);
            }



            liuLiangResult.setXlist(sortList);
            liuLiangResult.setNewuserslist(newUserMinutelist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  liuLiangResult;
    }
    public LiuLiangResult monthActivenumsHour(){
        LiuLiangResult liuLiangResult = new LiuLiangResult();
        //todo 时间限定
        String sql = "select timeinfoString,deviceType,sum(monthActivenums) as monthActivenumstotal from LiuLiangInfo where length(toString(timeinfoString)) < 12 " +
                "and length(toString(timeinfoString)) >= 10  group by timeinfoString,deviceType order by timeinfoString,deviceType;";
        Map<String,Map<String,Long>> mapDiveceMap = new HashMap<String,Map<String,Long>>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                String deviceType = resultSet.getString("deviceType");
                String deviceName = deviceMap.get(deviceType);
                Long monthActivenumstotal = resultSet.getLong("monthActivenumstotal");
                dateSet.add(timeinfoString);
                Map<String,Long> innerMap = mapDiveceMap.get(deviceName);
                if(innerMap == null){
                    innerMap = new HashMap<String,Long>();
                }
                Long data =innerMap.get(timeinfoString)==null?0l:innerMap.get(timeinfoString);
                data = data+ monthActivenumstotal;
                innerMap.put(timeinfoString,data);
                mapDiveceMap.put(deviceName,innerMap);
                System.out.println(timeinfoString +"=="+deviceType+"=="+monthActivenumstotal);
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

            Set<Map.Entry<String,Map<String,Long>>> dataMapSet = mapDiveceMap.entrySet();

            List<Map<String,Object>> newUserHourlist = new ArrayList<Map<String,Object>>();
            for(Map.Entry<String,Map<String,Long>> entry:dataMapSet){
                String name = entry.getKey();
                Map<String,Long> datamap = entry.getValue();
                Set<Map.Entry<String,Long>> setinner = datamap.entrySet();
                Map<String,Long> deepCopy = new HashMap<String,Long>();
                deepCopy.putAll(dateMaptemp);
                for(Map.Entry<String,Long>  innerMap : setinner){
                    String key = innerMap.getKey();
                    Long value = innerMap.getValue();
                    deepCopy.put(key,value);
                }
                List<Long> dataList = new ArrayList<Long>();
                for(String key :sortList){
                    Long value = deepCopy.get(key);
                    dataList.add(value);
                }
                Map<String,Object> maptemp = new HashMap<String,Object>();
                maptemp.put("name",name);
                maptemp.put("data",dataList);
                newUserHourlist.add(maptemp);
            }



            liuLiangResult.setXlist(sortList);
            liuLiangResult.setNewuserslist(newUserHourlist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  liuLiangResult;
    }

    public LiuLiangResult weekActivenumsMinute(){
        LiuLiangResult liuLiangResult = new LiuLiangResult();
        //todo 时间限定
        String sql = "select timeinfoString,deviceType,sum(weekActivenums) as weekActivenumstotal from LiuLiangInfo where " +
                " length(toString(timeinfoString)) >= 12  group by timeinfoString,deviceType order by timeinfoString,deviceType;";
        Map<String,Map<String,Long>> mapDiveceMap = new HashMap<String,Map<String,Long>>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                String deviceType = resultSet.getString("deviceType");
                String deviceName = deviceMap.get(deviceType);
                Long weekActivenumstotal = resultSet.getLong("weekActivenumstotal");
                dateSet.add(timeinfoString);
                Map<String,Long> innerMap = mapDiveceMap.get(deviceName);
                if(innerMap == null){
                    innerMap = new HashMap<String,Long>();
                }
                Long data =innerMap.get(timeinfoString)==null?0l:innerMap.get(timeinfoString);
                data = data+ weekActivenumstotal;
                innerMap.put(timeinfoString,data);
                mapDiveceMap.put(deviceName,innerMap);
                System.out.println(timeinfoString +"=="+deviceType+"=="+weekActivenumstotal);
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

            Set<Map.Entry<String,Map<String,Long>>> dataMapSet = mapDiveceMap.entrySet();

            List<Map<String,Object>> newUserMinutelist = new ArrayList<Map<String,Object>>();
            for(Map.Entry<String,Map<String,Long>> entry:dataMapSet){
                String name = entry.getKey();
                Map<String,Long> datamap = entry.getValue();
                Set<Map.Entry<String,Long>> setinner = datamap.entrySet();
                Map<String,Long> deepCopy = new HashMap<String,Long>();
                deepCopy.putAll(dateMaptemp);
                for(Map.Entry<String,Long>  innerMap : setinner){
                    String key = innerMap.getKey();
                    Long value = innerMap.getValue();
                    deepCopy.put(key,value);
                }
                List<Long> dataList = new ArrayList<Long>();
                for(String key :sortList){
                    Long value = deepCopy.get(key);
                    dataList.add(value);
                }
                Map<String,Object> maptemp = new HashMap<String,Object>();
                maptemp.put("name",name);
                maptemp.put("data",dataList);
                newUserMinutelist.add(maptemp);
            }



            liuLiangResult.setXlist(sortList);
            liuLiangResult.setNewuserslist(newUserMinutelist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  liuLiangResult;
    }
    public LiuLiangResult weekActivenumsHour(){
        LiuLiangResult liuLiangResult = new LiuLiangResult();
        //todo 时间限定
        String sql = "select timeinfoString,deviceType,sum(weekActivenums) as weekActivenumstotal from LiuLiangInfo where length(toString(timeinfoString)) < 12 " +
                "and length(toString(timeinfoString)) >= 10  group by timeinfoString,deviceType order by timeinfoString,deviceType;";
        Map<String,Map<String,Long>> mapDiveceMap = new HashMap<String,Map<String,Long>>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                String deviceType = resultSet.getString("deviceType");
                String deviceName = deviceMap.get(deviceType);
                Long weekActivenumstotal = resultSet.getLong("weekActivenumstotal");
                dateSet.add(timeinfoString);
                Map<String,Long> innerMap = mapDiveceMap.get(deviceName);
                if(innerMap == null){
                    innerMap = new HashMap<String,Long>();
                }
                Long data =innerMap.get(timeinfoString)==null?0l:innerMap.get(timeinfoString);
                data = data+ weekActivenumstotal;
                innerMap.put(timeinfoString,data);
                mapDiveceMap.put(deviceName,innerMap);
                System.out.println(timeinfoString +"=="+deviceType+"=="+weekActivenumstotal);
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

            Set<Map.Entry<String,Map<String,Long>>> dataMapSet = mapDiveceMap.entrySet();

            List<Map<String,Object>> newUserHourlist = new ArrayList<Map<String,Object>>();
            for(Map.Entry<String,Map<String,Long>> entry:dataMapSet){
                String name = entry.getKey();
                Map<String,Long> datamap = entry.getValue();
                Set<Map.Entry<String,Long>> setinner = datamap.entrySet();
                Map<String,Long> deepCopy = new HashMap<String,Long>();
                deepCopy.putAll(dateMaptemp);
                for(Map.Entry<String,Long>  innerMap : setinner){
                    String key = innerMap.getKey();
                    Long value = innerMap.getValue();
                    deepCopy.put(key,value);
                }
                List<Long> dataList = new ArrayList<Long>();
                for(String key :sortList){
                    Long value = deepCopy.get(key);
                    dataList.add(value);
                }
                Map<String,Object> maptemp = new HashMap<String,Object>();
                maptemp.put("name",name);
                maptemp.put("data",dataList);
                newUserHourlist.add(maptemp);
            }



            liuLiangResult.setXlist(sortList);
            liuLiangResult.setNewuserslist(newUserHourlist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  liuLiangResult;
    }

    public List<LiuLiangInfo> usersDetailHour(String timeinfoPara){
        List<LiuLiangInfo> userDetailList = new ArrayList<LiuLiangInfo>();
        //2019090801,create "userinfo","info"
        String dayinfo = timeinfoPara.substring(0,8);
        String hourinfo = timeinfoPara.substring(8,10);
        String sql = "select deviceType,timeinfo,userId,sum(times) as times,sum(newusers) as newusers," +
                "sum(hourActivenums) as hourActivenums ,sum(dayActivenums) as dayActivenums,sum(weekActivenums) as weekActivenums,sum(monthActivenums) as monthActivenums from " +
                "LiuLiangUserInfo where dayinfo = "+dayinfo+" and hourinfo = "+hourinfo+" group by deviceType,timeinfo,userId";
        try {
            ResultSet resultSet = HiveUtils.getQueryResult(sql);
            while(resultSet.next()){
                String deviceType = resultSet.getString("deviceType");
                String timeinfo = resultSet.getString("timeinfo");
                String userId = resultSet.getString("userId");
                String userName = HbaseUtils2.getdata("userinfo",userId,"info","userName");
                Long times = resultSet.getLong("times");
                Long newusers = resultSet.getLong("newusers");
                Long hourActivenums = resultSet.getLong("hourActivenums");
                Long dayActivenums = resultSet.getLong("dayActivenums");
                Long weekActivenums = resultSet.getLong("weekActivenums");
                Long monthActivenums = resultSet.getLong("monthActivenums");
                LiuLiangInfo liuLiangInfo = new LiuLiangInfo();
                String deviceName = deviceMap.get(deviceType);
                liuLiangInfo.setDeviceName(deviceName);
                liuLiangInfo.setTimeinfo(timeinfo);
                liuLiangInfo.setTimes(times);
                liuLiangInfo.setUserNums(newusers);
                liuLiangInfo.setHourActivenums(hourActivenums);
                liuLiangInfo.setDayActivenums(dayActivenums);
                liuLiangInfo.setWeekActivenums(weekActivenums);
                liuLiangInfo.setMonthActivenums(monthActivenums);
                liuLiangInfo.setUserName(userName);
                userDetailList.add(liuLiangInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDetailList;
    }




    public static void main(String[] args) {
        LiuLiangResult liuLiangResult = new LiuLiangService().newusersHour();
        System.out.println(JSONObject.toJSONString(liuLiangResult));
    }



}
