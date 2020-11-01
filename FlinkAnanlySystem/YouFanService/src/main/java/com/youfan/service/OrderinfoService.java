package com.youfan.service;

import com.youfan.entity.ChannelInfo;
import com.youfan.utils.ClickHouseUtils;
import com.youfan.utils.DateUtils;
import com.youfan.utils.HbaseUtils2;
import com.youfan.utils.HiveUtils;
import com.youfan.viewResult.ChannelResult;
import com.youfan.viewResult.OrderInfoResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2020/3/3.
 */
@Service
public class OrderinfoService {

    public OrderInfoResult timesHour(){
        OrderInfoResult orderInfoResult = new OrderInfoResult();
        //todo 时间限定
        String sql = "select timeinfoString,sum(times) as timestotal from OrderInfo where length(toString(timeinfoString)) < 12 "+
                "and length(toString(timeinfoString)) >= 10  group by timeinfoString order by timeinfoString;";

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
            maptemp.put("name","订单量");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            orderInfoResult.setXlist(sortList);
            orderInfoResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  orderInfoResult;
    }

    public OrderInfoResult timesMinute(){
        OrderInfoResult orderInfoResult = new OrderInfoResult();
        //todo 时间限定
        String sql = "select timeinfoString,sum(times) as timestotal from OrderInfo  "+
                " where length(toString(timeinfoString)) >= 12  group by timeinfoString order by timeinfoString;";

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
            maptemp.put("name","订单量");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            orderInfoResult.setXlist(sortList);
            orderInfoResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  orderInfoResult;
    }

    public OrderInfoResult userNumHour(){
        OrderInfoResult orderInfoResult = new OrderInfoResult();
        //todo 时间限定
        String sql = "select timeinfoString,sum(userNum) as userNumtotal from OrderInfo where length(toString(timeinfoString)) < 12 "+
                "and length(toString(timeinfoString)) >= 10  group by timeinfoString order by timeinfoString;";

        Map<String,Long> mapDiveceMap = new HashMap<String,Long>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                Long userNumtotal = resultSet.getLong("userNumtotal");
                dateSet.add(timeinfoString);
                Long data =mapDiveceMap.get(timeinfoString)==null?0l:mapDiveceMap.get(timeinfoString);
                data = data+ userNumtotal;
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
            maptemp.put("name","订单用户量");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            orderInfoResult.setXlist(sortList);
            orderInfoResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  orderInfoResult;
    }

    public OrderInfoResult userNumMinute(){
        OrderInfoResult orderInfoResult = new OrderInfoResult();
        //todo 时间限定
        String sql = "select timeinfoString,sum(userNum) as userNumtotal from OrderInfo  "+
                " where length(toString(timeinfoString)) >= 12  group by timeinfoString order by timeinfoString;";

        Map<String,Long> mapDiveceMap = new HashMap<String,Long>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                Long userNumtotal = resultSet.getLong("userNumtotal");
                dateSet.add(timeinfoString);
                Long data =mapDiveceMap.get(timeinfoString)==null?0l:mapDiveceMap.get(timeinfoString);
                data = data+ userNumtotal;
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
            maptemp.put("name","订单用户量");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            orderInfoResult.setXlist(sortList);
            orderInfoResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  orderInfoResult;
    }


    public OrderInfoResult keDanJiaHour(){
        OrderInfoResult orderInfoResult = new OrderInfoResult();
        //todo 时间限定
        String sql = "select timeinfoString,sum(times) as timestotal,sum(orderAmount) as orderAmounttotal from OrderInfo where length(toString(timeinfoString)) < 12 "+
                "and length(toString(timeinfoString)) >= 10  group by timeinfoString order by timeinfoString;";

        Map<String,Long> mapTimesMap = new HashMap<String,Long>();
        Map<String,Double> mapOrderAmountMap = new HashMap<String,Double>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                Long timestotal = resultSet.getLong("timestotal");
                Double orderAmounttotal = resultSet.getDouble("orderAmounttotal");
                dateSet.add(timeinfoString);
                Long timesdata =mapTimesMap.get(timeinfoString)==null?0l:mapTimesMap.get(timeinfoString);
                timesdata = timesdata+ timestotal;
                mapTimesMap.put(timeinfoString,timesdata);

                Double orderAmountdata = mapOrderAmountMap.get(timeinfoString)==null?0.0d:mapOrderAmountMap.get(timeinfoString);
                orderAmountdata = orderAmountdata + orderAmounttotal;
                mapOrderAmountMap.put(timeinfoString,orderAmountdata);
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

            Set<Map.Entry<String,Long>> dataMapSet = mapTimesMap.entrySet();


            Map<String,Double> deepCopy = new HashMap<String,Double>();
            deepCopy.putAll(dateMaptemp);
            for(Map.Entry<String,Long> entry:dataMapSet){

                String key = entry.getKey();
                Double totalAmount = mapOrderAmountMap.get(key);
                Long value = entry.getValue();
                BigDecimal amountBigDecimal = new BigDecimal(totalAmount);
                BigDecimal timesBigDecimal = new BigDecimal(value);
                if(value==0){
                    deepCopy.put(key,0.0);
                }else {
                    BigDecimal resultBigDecimal = timesBigDecimal.divide(amountBigDecimal, 6, BigDecimal.ROUND_HALF_UP);
                    Double resultDouble = resultBigDecimal.doubleValue();
                    deepCopy.put(key,resultDouble);
                }
            }
            List<Double> dataList = new ArrayList<Double>();
            for(String key :sortList){
                Double value = deepCopy.get(key);
                dataList.add(value);
            }
            List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
            Map<String,Object> maptemp = new HashMap<String,Object>();
            maptemp.put("name","订单客单价");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            orderInfoResult.setXlist(sortList);
            orderInfoResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  orderInfoResult;
    }

    public OrderInfoResult keDanJiaMinute(){
        OrderInfoResult orderInfoResult = new OrderInfoResult();

        //todo 时间限定
        String sql = "select timeinfoString,sum(times) as timestotal,sum(orderAmount) as orderAmounttotal from OrderInfo "+
                " where length(toString(timeinfoString)) >= 12  group by timeinfoString order by timeinfoString;";

        Map<String,Long> mapTimesMap = new HashMap<String,Long>();
        Map<String,Double> mapOrderAmountMap = new HashMap<String,Double>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                Long timestotal = resultSet.getLong("timestotal");
                Double orderAmounttotal = resultSet.getDouble("orderAmounttotal");
                dateSet.add(timeinfoString);
                Long timesdata =mapTimesMap.get(timeinfoString)==null?0l:mapTimesMap.get(timeinfoString);
                timesdata = timesdata+ timestotal;
                mapTimesMap.put(timeinfoString,timesdata);

                Double orderAmountdata = mapOrderAmountMap.get(timeinfoString)==null?0.0d:mapOrderAmountMap.get(timeinfoString);
                orderAmountdata = orderAmountdata + orderAmounttotal;
                mapOrderAmountMap.put(timeinfoString,orderAmountdata);
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
            Map<String,Double> dateMaptemp = new HashMap<String,Double>();
            for(String timeinfoString:sortList){
                dateMaptemp.put(timeinfoString,0.0d);
            }

            Set<Map.Entry<String,Long>> dataMapSet = mapTimesMap.entrySet();


            Map<String,Double> deepCopy = new HashMap<String,Double>();
            deepCopy.putAll(dateMaptemp);
            for(Map.Entry<String,Long> entry:dataMapSet){

                String key = entry.getKey();
                Double totalAmount = mapOrderAmountMap.get(key);
                Long value = entry.getValue();
                BigDecimal amountBigDecimal = new BigDecimal(totalAmount);
                BigDecimal timesBigDecimal = new BigDecimal(value);
                if(value==0){
                    deepCopy.put(key,0.0);
                }else {
                    BigDecimal resultBigDecimal = timesBigDecimal.divide(amountBigDecimal, 6, BigDecimal.ROUND_HALF_UP);
                    Double resultDouble = resultBigDecimal.doubleValue();
                    deepCopy.put(key,resultDouble);
                }


            }
            List<Double> dataList = new ArrayList<Double>();
            for(String key :sortList){
                Double value = deepCopy.get(key);
                dataList.add(value);
            }
            List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
            Map<String,Object> maptemp = new HashMap<String,Object>();
            maptemp.put("name","订单客单价");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            orderInfoResult.setXlist(sortList);
            orderInfoResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  orderInfoResult;
    }



}