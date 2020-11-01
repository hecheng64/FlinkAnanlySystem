package com.youfan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Administrator on 2020/2/15.
 */
public class Test2 {
    public static void main(String[] args) throws Exception {
        String address = "jdbc:clickhouse://192.168.246.152:8123/test";
        Class.forName("ru.yandex.clickhouse.ClickHouseDriver");
        Connection connection  = DriverManager.getConnection(address);
        String sql = "select * from youfantest;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery(sql);//执行sql语句
        while(resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String date = resultSet.getString(3);
            System.out.println(id +"=="+name+"=="+date);
        }
    }
}
