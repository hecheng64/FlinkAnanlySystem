package com.youfan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by Administrator on 2020/2/15.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        String address = "jdbc:clickhouse://192.168.246.152:8123/test";
        Class.forName("ru.yandex.clickhouse.ClickHouseDriver");
        Connection connection  = DriverManager.getConnection(address);
        String sql = "insert into youfantest (id,name,create_date)values(?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,6);
        preparedStatement.setString(2,"xiaocai");
        preparedStatement.setString(3,"2019-08-02");
        preparedStatement.executeUpdate();			//执行sql语句

    }
}
