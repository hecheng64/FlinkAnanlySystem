package com.youfan.analy;

import com.youfan.entity.AdvertisingInfo;
import com.youfan.map.HourTransferMap;
import com.youfan.map.MinuteTransferMap;
import com.youfan.map.advertising.AdvertisingMap;
import com.youfan.reduce.advertising.AdvertisingReduce;
import com.youfan.sink.advertising.AdvertisinginfoSink;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * Created by Administrator on 2020/2/10.
 */
public class AdvertisingHourAnaly {
    public static void main(String[] args) {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "192.168.246.152:9092");
        properties.setProperty("group.id", "youfantest");
        //构建FlinkKafkaConsumer
        FlinkKafkaConsumer<String> myConsumer = new FlinkKafkaConsumer<>("datainfo", new SimpleStringSchema(), properties);
//        //指定偏移量
        myConsumer.setStartFromEarliest();


        DataStream<String> stream = env
                .addSource(myConsumer);

        env.enableCheckpointing(5000);
        DataStream<String> transfermap = stream.map(new HourTransferMap());
        DataStream<AdvertisingInfo> map = transfermap.map(new AdvertisingMap());
        DataStream<AdvertisingInfo> reduce = map.keyBy("groupByField").timeWindowAll(Time.minutes(5)).reduce(new AdvertisingReduce());
        reduce.addSink(new AdvertisinginfoSink());
        try {
            env.execute("AdvertisingMinuteAnaly");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
