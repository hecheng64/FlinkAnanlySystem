package com.youfan.analy;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.AdvertisingInfo;
import com.youfan.input.DeviceComomInfo;
import com.youfan.input.ScanPageLog;
import com.youfan.map.HourTransferMap;
import com.youfan.map.advertising.AdvertisingMap;
import com.youfan.map.advertising.AdvertisingZhuanHuaLvMap;
import com.youfan.map.advertising.AdvertisingZhuanHuaLvMapSecond;
import com.youfan.reduce.advertising.AdvertisingReduce;
import com.youfan.reduce.advertising.AdvertisingZhuanHuaLvReduce;
import com.youfan.reduce.advertising.AdvertisingZhuanHuaLvReduceSecond;
import com.youfan.sink.advertising.AdvertisinginfoSink;
import com.youfan.sink.advertising.AdvertisinginfoZhuanHuaLvSink;
import com.youfan.yewu.OrderInfo;
import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * Created by Administrator on 2020/2/10.
 */
public class AdvertisingZhuanHuaLvHourAnaly {
    public static void main(String[] args) {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties properties1 = new Properties();
        properties1.setProperty("bootstrap.servers", "192.168.246.152:9092");
        properties1.setProperty("group.id", "youfantest");
        //构建FlinkKafkaConsumer
        FlinkKafkaConsumer<String> myConsumer1 = new FlinkKafkaConsumer<>("dataInfo", new SimpleStringSchema(), properties1);
//        //指定偏移量
        myConsumer1.setStartFromLatest();


        DataStream<String> stream1 = env
                .addSource(myConsumer1);

        Properties properties2 = new Properties();
        properties2.setProperty("bootstrap.servers", "192.168.246.152:9092");
        properties2.setProperty("group.id", "youfantest");
        //构建FlinkKafkaConsumer
        FlinkKafkaConsumer<String> myConsumer2 = new FlinkKafkaConsumer<>("orderinfo", new SimpleStringSchema(), properties2);
//        //指定偏移量
        myConsumer2.setStartFromLatest();


        DataStream<String> stream2 = env
                .addSource(myConsumer2);

        DataStream<String> joinstream = stream1.join(stream2).where(new KeySelector<String, String>() {
            @Override
            public String getKey(String s) throws Exception {
                ScanPageLog scanPageLog = JSONObject.parseObject(s,ScanPageLog.class);
                DeviceComomInfo deviceComomInfo = scanPageLog.getDeviceComomInfo();
                String userId = deviceComomInfo.getUserId();
                return userId;
            }
        }).equalTo(new KeySelector<String,String>(){
            @Override
            public String getKey(String s) throws Exception {
                OrderInfo orderInfo = JSONObject.parseObject(s, OrderInfo.class);
                String userId = orderInfo.getUserId()+"";
                return userId;
            }
        }).window(TumblingProcessingTimeWindows.of(Time.hours(1))).apply(new JoinFunction<String, String, String>() {
            @Override
            public String join(String s, String s2) throws Exception {
                ScanPageLog scanPageLog = JSONObject.parseObject(s,ScanPageLog.class);

                OrderInfo orderInfo = JSONObject.parseObject(s, OrderInfo.class);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("scanPageLog",scanPageLog);
                jsonObject.put("orderInfo",orderInfo);
                String finalResult = JSONObject.toJSONString(jsonObject);
                return finalResult;
            }
        });
        DataStream<AdvertisingInfo> quChongstream =
                joinstream.map(new AdvertisingZhuanHuaLvMap()).keyBy("groupByField").timeWindowAll(Time.hours(1l)).reduce(new AdvertisingZhuanHuaLvReduce());
        DataStream<AdvertisingInfo> reducestream = quChongstream.map(new AdvertisingZhuanHuaLvMapSecond()).keyBy("groupByField").timeWindowAll(Time.hours(1l)).reduce(new AdvertisingZhuanHuaLvReduceSecond());
        reducestream.addSink(new AdvertisinginfoZhuanHuaLvSink());
        try {
            env.execute("AdvertisingZhuanHuaLvHourAnaly");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
