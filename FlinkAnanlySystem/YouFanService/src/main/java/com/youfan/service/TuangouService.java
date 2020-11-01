package com.youfan.service;

import com.youfan.utils.ClickHouseUtils;
import com.youfan.utils.DateUtils;
import com.youfan.viewResult.OrderInfoResult;
import com.youfan.viewResult.TuanGouInfoResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2020/3/3.
 */
@Service
public class TuangouService {

    public TuanGouInfoResult orderTimesHour(){
        TuanGouInfoResult tuanGouInfoResult = new TuanGouInfoResult();
        //todo 时间限定
        String sql = "select timeinfoString,sum(orderTimes) as orderTimestotal from TuanGouInfo where length(toString(timeinfoString)) < 12 "+
                "and length(toString(timeinfoString)) >= 10  group by timeinfoString order by timeinfoString;";

        Map<String,Long> mapDiveceMap = new HashMap<String,Long>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                Long orderTimestotal = resultSet.getLong("orderTimestotal");
                dateSet.add(timeinfoString);
                Long data =mapDiveceMap.get(timeinfoString)==null?0l:mapDiveceMap.get(timeinfoString);
                data = data+ orderTimestotal;
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
            maptemp.put("name","团购订单量");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            tuanGouInfoResult.setXlist(sortList);
            tuanGouInfoResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  tuanGouInfoResult;
    }

    public TuanGouInfoResult orderTimesMinute(){
        TuanGouInfoResult tuanGouInfoResult = new TuanGouInfoResult();
        //todo 时间限定
        String sql = "select timeinfoString,sum(orderTimes) as orderTimestotal from TuanGouInfo "+
                " where length(toString(timeinfoString)) >= 12  group by timeinfoString order by timeinfoString;";

        Map<String,Long> mapDiveceMap = new HashMap<String,Long>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                Long orderTimestotal = resultSet.getLong("orderTimestotal");
                dateSet.add(timeinfoString);
                Long data =mapDiveceMap.get(timeinfoString)==null?0l:mapDiveceMap.get(timeinfoString);
                data = data+ orderTimestotal;
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
            maptemp.put("name","团购订单量");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            tuanGouInfoResult.setXlist(sortList);
            tuanGouInfoResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  tuanGouInfoResult;
    }

    public TuanGouInfoResult userNumsHour(){
        TuanGouInfoResult tuanGouInfoResult = new TuanGouInfoResult();
        //todo 时间限定
        String sql = "select timeinfoString,sum(userNums) as userNumstotal from TuanGouInfo where length(toString(timeinfoString)) < 12 "+
                "and length(toString(timeinfoString)) >= 10  group by timeinfoString order by timeinfoString;";

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
            maptemp.put("name","团购用户量");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            tuanGouInfoResult.setXlist(sortList);
            tuanGouInfoResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  tuanGouInfoResult;
    }

    public TuanGouInfoResult userNumsMinute(){
        TuanGouInfoResult tuanGouInfoResult = new TuanGouInfoResult();
        //todo 时间限定
        String sql = "select timeinfoString,sum(userNums) as userNumstotaltotal from TuanGouInfo "+
                " where length(toString(timeinfoString)) >= 12  group by timeinfoString order by timeinfoString;";

        Map<String,Long> mapDiveceMap = new HashMap<String,Long>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                Long userNumstotaltotal = resultSet.getLong("userNumstotaltotal");
                dateSet.add(timeinfoString);
                Long data =mapDiveceMap.get(timeinfoString)==null?0l:mapDiveceMap.get(timeinfoString);
                data = data+ userNumstotaltotal;
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
            maptemp.put("name","团购用户量");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            tuanGouInfoResult.setXlist(sortList);
            tuanGouInfoResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  tuanGouInfoResult;
    }


    public TuanGouInfoResult productTypeHour(String productTypeId){
        TuanGouInfoResult tuanGouInfoResult = new TuanGouInfoResult();
        //todo 时间限定
        String sql = "select timeinfoString,sum(times) as timestotal from TuanGouInfo where length(toString(timeinfoString)) < 12 "+
                "and length(toString(timeinfoString)) >= 10 and productType = '"+productTypeId+"'  group by timeinfoString order by timeinfoString;";

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
            maptemp.put("name","商品类别团购数量");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            tuanGouInfoResult.setXlist(sortList);
            tuanGouInfoResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  tuanGouInfoResult;
    }

    public TuanGouInfoResult productTypeMinute(String productTypeId){
        TuanGouInfoResult tuanGouInfoResult = new TuanGouInfoResult();
        //todo 时间限定
        String sql = "select timeinfoString,sum(times) as timestotal from TuanGouInfo "+
                " where length(toString(timeinfoString)) >= 12  and productType = '"+productTypeId+"'  group by timeinfoString order by timeinfoString;";

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
            maptemp.put("name","商品类别团购数量");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            tuanGouInfoResult.setXlist(sortList);
            tuanGouInfoResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  tuanGouInfoResult;
    }



}