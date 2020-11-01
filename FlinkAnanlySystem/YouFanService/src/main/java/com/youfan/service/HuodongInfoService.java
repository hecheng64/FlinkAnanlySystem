package com.youfan.service;

import com.youfan.utils.ClickHouseUtils;
import com.youfan.utils.DateUtils;
import com.youfan.viewResult.HuodongInfoResult;
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
public class HuodongInfoService {

    public HuodongInfoResult orderNumsHour(){
        HuodongInfoResult huodongInfoResult = new HuodongInfoResult();
        //todo 时间限定
        String sql = "select timeinfoString,sum(orderNums) as orderNumstotal from HuodongInfo where length(toString(timeinfoString)) < 12 "+
                "and length(toString(timeinfoString)) >= 10  group by timeinfoString order by timeinfoString;";

        Map<String,Long> mapDiveceMap = new HashMap<String,Long>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                Long orderNumstotal = resultSet.getLong("orderNumstotal");
                dateSet.add(timeinfoString);
                Long data =mapDiveceMap.get(timeinfoString)==null?0l:mapDiveceMap.get(timeinfoString);
                data = data+ orderNumstotal;
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
            maptemp.put("name","订单成交量");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            huodongInfoResult.setXlist(sortList);
            huodongInfoResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  huodongInfoResult;
    }

    public HuodongInfoResult orderNumsMinute(){
        HuodongInfoResult huodongInfoResult = new HuodongInfoResult();
        //todo 时间限定
        String sql = "select timeinfoString,sum(orderNums) as orderNumstotal from HuodongInfo  "+
                " where length(toString(timeinfoString)) >= 12  group by timeinfoString order by timeinfoString;";

        Map<String,Long> mapDiveceMap = new HashMap<String,Long>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                Long orderNumstotal = resultSet.getLong("orderNumstotal");
                dateSet.add(timeinfoString);
                Long data =mapDiveceMap.get(timeinfoString)==null?0l:mapDiveceMap.get(timeinfoString);
                data = data+ orderNumstotal;
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
            maptemp.put("name","订单成交量");
            maptemp.put("data",dataList);
            mapList.add(maptemp);


            huodongInfoResult.setXlist(sortList);
            huodongInfoResult.setNewuserslist(mapList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  huodongInfoResult;
    }

    public HuodongInfoResult areaNumHour(){
        HuodongInfoResult huodongInfoResult = new HuodongInfoResult();
        //todo 时间限定
        String sql = "select timeinfoString,huodongArea,sum(areaNum) as areaNumtotal from HuodongInfo where length(toString(timeinfoString)) < 12 "+
                "and length(toString(timeinfoString)) >= 10  group by timeinfoString,huodongArea order by timeinfoString;";

        Map<String,Map<String,Long>> mapDiveceMap = new HashMap<String,Map<String,Long>>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                String huodongArea = resultSet.getString("huodongArea");
                Long areaNumtotal = resultSet.getLong("areaNumtotal");
                dateSet.add(timeinfoString);
                Map<String,Long> innerMap = mapDiveceMap.get(huodongArea);
                if(innerMap == null){
                    innerMap = new HashMap<String,Long>();
                }
                Long data =innerMap.get(timeinfoString)==null?0l:innerMap.get(timeinfoString);
                data = data+ areaNumtotal;
                innerMap.put(timeinfoString,data);
                mapDiveceMap.put(huodongArea,innerMap);
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



            huodongInfoResult.setXlist(sortList);
            huodongInfoResult.setNewuserslist(newUserHourlist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  huodongInfoResult;
    }

    public HuodongInfoResult areaNumMinute(){
        HuodongInfoResult huodongInfoResult = new HuodongInfoResult();
        //todo 时间限定
        String sql = "select timeinfoString,huodongArea,sum(areaNum) as areaNumtotal from HuodongInfo "+
                " where length(toString(timeinfoString)) >= 12  group by timeinfoString,huodongArea order by timeinfoString;";

        Map<String,Map<String,Long>> mapDiveceMap = new HashMap<String,Map<String,Long>>();
        Set<String> dateSet = new HashSet<String>();
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands",sql);
            while(resultSet.next()){
                String timeinfoString = resultSet.getString("timeinfoString");
                String huodongArea = resultSet.getString("huodongArea");
                Long areaNumtotal = resultSet.getLong("areaNumtotal");
                dateSet.add(timeinfoString);
                Map<String,Long> innerMap = mapDiveceMap.get(huodongArea);
                if(innerMap == null){
                    innerMap = new HashMap<String,Long>();
                }
                Long data =innerMap.get(timeinfoString)==null?0l:innerMap.get(timeinfoString);
                data = data+ areaNumtotal;
                innerMap.put(timeinfoString,data);
                mapDiveceMap.put(huodongArea,innerMap);
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



            huodongInfoResult.setXlist(sortList);
            huodongInfoResult.setNewuserslist(newUserHourlist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  huodongInfoResult;
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
                BigDecimal resultBigDecimal = timesBigDecimal.divide(amountBigDecimal);
                resultBigDecimal.setScale(6);
                Double resultDouble = resultBigDecimal.doubleValue();
                deepCopy.put(key,resultDouble);


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
                BigDecimal resultBigDecimal = timesBigDecimal.divide(amountBigDecimal);
                resultBigDecimal.setScale(6);
                Double resultDouble = resultBigDecimal.doubleValue();
                deepCopy.put(key,resultDouble);


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