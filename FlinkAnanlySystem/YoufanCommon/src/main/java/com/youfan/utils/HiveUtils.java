package com.youfan.utils;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.LiuLiangInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Administrator on 2020/3/3.
 */
public class HiveUtils {
    private static Connection getConnection(String addressParam, String driverClassNameParam) throws Exception {
        String address = addressParam;
        Class.forName(driverClassNameParam);
        Connection connection  = DriverManager.getConnection(address);
        return connection;
    }

    public static ResultSet getQueryResult(String sql) throws Exception {
        //hive连接
        String connection_url = "jdbc:hive2://192.168.246.152:10000/default";//hive库地址+库名
        Connection connection = getConnection(connection_url,"org.apache.hive.jdbc.HiveDriver");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    public static void main(String[] args) throws Exception {
        String sql = "select deviceType,timeinfo,userId,sum(times) as times,sum(newusers),sum(hourActivenums),sum(dayActivenums),sum(weekActivenums),sum(monthActivenums) from LiuLiangUserInfo group by deviceType,timeinfo,userId";
        ResultSet resultSet = getQueryResult(sql);
        while(resultSet.next()){
            String deviceType = resultSet.getString("deviceType");
            Long times = resultSet.getLong("times");
            System.out.println(deviceType+"=="+times);
        }

//        LiuLiangInfo liuLiangInfo = new LiuLiangInfo();
//        liuLiangInfo.setUserId("1");
//        liuLiangInfo.setDeviceType("1");
//        liuLiangInfo.setTimeinfo("2019080901");
//        liuLiangInfo.setTimes(2l);
//        liuLiangInfo.setHourActivenums(1l);
//        liuLiangInfo.setDayActivenums(1l);
//        System.out.println(JSONObject.toJSONString(liuLiangInfo));
//        liuLiangInfo = new LiuLiangInfo();
//        liuLiangInfo.setUserId("1");
//        liuLiangInfo.setDeviceType("0");
//        liuLiangInfo.setTimeinfo("2019080901");
//        liuLiangInfo.setTimes(21l);
//        liuLiangInfo.setHourActivenums(1l);
//        liuLiangInfo.setDayActivenums(1l);
//        System.out.println(JSONObject.toJSONString(liuLiangInfo));
//        liuLiangInfo = new LiuLiangInfo();
//        liuLiangInfo.setUserId("2");
//        liuLiangInfo.setDeviceType("0");
//        liuLiangInfo.setTimeinfo("2019080902");
//        liuLiangInfo.setTimes(2l);
//        liuLiangInfo.setHourActivenums(1l);
//        liuLiangInfo.setDayActivenums(1l);
//        System.out.println(JSONObject.toJSONString(liuLiangInfo));

    }
}
