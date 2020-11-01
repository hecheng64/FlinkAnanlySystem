package com.youfan.analy;

import com.youfan.entity.LiuLiangInfo;
import com.youfan.map.HourTransferMap;
import com.youfan.map.LiuLiangMap;
import com.youfan.reduce.LiuLiangReduce;
import com.youfan.sink.LiuLiangSink;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * Created by Administrator on 2020/2/10.
 */
public class LiuLiangHourAnaly {
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
        DataStream<LiuLiangInfo> map = transfermap.map(new LiuLiangMap());
        DataStream<LiuLiangInfo> reduce = map.keyBy("groupByField").timeWindowAll(Time.minutes(1l)).reduce(new LiuLiangReduce());
        reduce.addSink(new LiuLiangSink());
        try {
            env.execute("LiuLiangHourAnaly");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
