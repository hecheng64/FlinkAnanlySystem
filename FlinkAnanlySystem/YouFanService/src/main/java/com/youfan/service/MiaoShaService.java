package com.youfan.service;

import com.youfan.utils.ClickHouseUtils;
import com.youfan.utils.DateUtils;
import com.youfan.viewResult.LiuLiangResult;
import com.youfan.viewResult.MiaoshaResult;
import com.youfan.viewResult.TuanGouInfoResult;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2020/3/3.
 */
@Service
public class MiaoShaService {
    static Map<String,String> payTypeMap = new HashMap<String,String>();
    static {
        //1 微信支付 2 支付宝支付 3 银联支付 4 京东支付
        payTypeMap.put("1","微信支付");
        payTypeMap.put("2","支付宝支付");
        payTypeMap.put("3","银联支付");
        payTypeMap.put("4","京东支付");
    }
//    private Long orderPayNums = 0l;//成交数
//    private String payType = "";
//    private Long payNums=0l;
    public MiaoshaResult orderPayNumsHour(){
        MiaoshaResult miaoshaResult = new MiaoshaResult();
        //todo 时间限定
        String sql = "select timeinfoString,sum(orderPayNums) as orderPayNumstotal from MiaoshaInfo where length(toString(timeinfoString)) < 12 "+
                "and length(toString(timeinfoString)) >= 10  group by timeinfoString order by timeinfoString;";

        Map<String,Long> mapDiveceMap = new HashMap<String,Long>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                Long orderPayNumstotal = resultSet.getLong("orderPayNumstotal");
                dateSet.add(timeinfoString);
                Long data =mapDiveceMap.get(timeinfoString)==null?0l:mapDiveceMap.get(timeinfoString);
                data = data+ orderPayNumstotal;
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
            maptemp.put("name","秒杀成交订单量");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            miaoshaResult.setXlist(sortList);
            miaoshaResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  miaoshaResult;
    }

    public MiaoshaResult orderPayNumsMinute(){

        MiaoshaResult miaoshaResult = new MiaoshaResult();
        //todo 时间限定
        String sql = "select timeinfoString,sum(orderPayNums) as orderPayNumstotal from MiaoshaInfo" +
                " where length(toString(timeinfoString)) >= 12  group by timeinfoString order by timeinfoString;";

        Map<String,Long> mapDiveceMap = new HashMap<String,Long>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                Long orderPayNumstotal = resultSet.getLong("orderPayNumstotal");
                dateSet.add(timeinfoString);
                Long data =mapDiveceMap.get(timeinfoString)==null?0l:mapDiveceMap.get(timeinfoString);
                data = data+ orderPayNumstotal;
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
            maptemp.put("name","秒杀成交订单量");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            miaoshaResult.setXlist(sortList);
            miaoshaResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  miaoshaResult;
    }

    public MiaoshaResult payNumsHour(){
        MiaoshaResult miaoshaResult = new MiaoshaResult();
        //todo 时间限定
        String sql = "select timeinfoString,payType,sum(payNums) as payNumstotal from MiaoshaInfo where length(toString(timeinfoString)) < 12 "+
                "and length(toString(timeinfoString)) >= 10  group by timeinfoString,payType order by timeinfoString,payType;";
        Map<String,Map<String,Long>> mapDiveceMap = new HashMap<String,Map<String,Long>>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                String payType = resultSet.getString("payType");
                String payTypeName = payTypeMap.get(payType);
                Long payNumstotal = resultSet.getLong("payNumstotal");
                dateSet.add(timeinfoString);
                Map<String,Long> innerMap = mapDiveceMap.get(payTypeName);
                if(innerMap == null){
                    innerMap = new HashMap<String,Long>();
                }
                Long data =innerMap.get(timeinfoString)==null?0l:innerMap.get(timeinfoString);
                data = data+ payNumstotal;
                innerMap.put(timeinfoString,data);
                mapDiveceMap.put(payTypeName,innerMap);
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



            miaoshaResult.setXlist(sortList);
            miaoshaResult.setNewuserslist(newUserHourlist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  miaoshaResult;
    }

    public MiaoshaResult payNumsMinute(){
        MiaoshaResult miaoshaResult = new MiaoshaResult();
        //todo 时间限定
        String sql = "select timeinfoString,payType,sum(payNums) as payNumstotal from MiaoshaInfo "+
                " where length(toString(timeinfoString)) >= 12  group by timeinfoString,payType order by timeinfoString,payType;";
        Map<String,Map<String,Long>> mapDiveceMap = new HashMap<String,Map<String,Long>>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                String payType = resultSet.getString("payType");
                String payTypeName = payTypeMap.get(payType);
                Long payNumstotal = resultSet.getLong("payNumstotal");
                dateSet.add(timeinfoString);
                Map<String,Long> innerMap = mapDiveceMap.get(payTypeName);
                if(innerMap == null){
                    innerMap = new HashMap<String,Long>();
                }
                Long data =innerMap.get(timeinfoString)==null?0l:innerMap.get(timeinfoString);
                data = data+ payNumstotal;
                innerMap.put(timeinfoString,data);
                mapDiveceMap.put(payTypeName,innerMap);
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



            miaoshaResult.setXlist(sortList);
            miaoshaResult.setNewuserslist(newUserHourlist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  miaoshaResult;
    }



}