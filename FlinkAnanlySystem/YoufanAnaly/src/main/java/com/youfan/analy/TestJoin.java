package com.youfan.analy;

import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks;
import org.apache.flink.streaming.api.functions.TimestampAssigner;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import javax.annotation.Nullable;
import java.util.Properties;

/**
 * Created by Administrator on 2020/2/29.
 */
public class TestJoin {
    /**
     * test1:id,time,userid 1,20190809,1
     * test2:id,desc,userid 1,测试,1
     * 合并完：userid,time,desc
     * **/
    public static void main(String[] args) {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties properties1 = new Properties();
        properties1.setProperty("bootstrap.servers", "192.168.246.152:9092");
        properties1.setProperty("group.id", "youfantest");
        //构建FlinkKafkaConsumer
        FlinkKafkaConsumer<String> myConsumer1 = new FlinkKafkaConsumer<>("test1", new SimpleStringSchema(), properties1);
//        //指定偏移量
        myConsumer1.setStartFromLatest();


        DataStream<String> stream1 = env
                .addSource(myConsumer1);

        Properties properties2 = new Properties();
        properties2.setProperty("bootstrap.servers", "192.168.246.152:9092");
        properties2.setProperty("group.id", "youfantest");
        //构建FlinkKafkaConsumer
        FlinkKafkaConsumer<String> myConsumer2 = new FlinkKafkaConsumer<>("test2", new SimpleStringSchema(), properties2);
//        //指定偏移量
        myConsumer2.setStartFromLatest();


        DataStream<String> stream2 = env
                .addSource(myConsumer2);

        DataStream<String> stream3 = stream1.join(stream2).where(new KeySelector<String, String>() {
            @Override
            public String getKey(String s) throws Exception {
                String [] result = s.split(",");
                return result[2];
            }
        }).equalTo(new KeySelector<String,String>(){
            @Override
            public String getKey(String s) throws Exception {
                String [] result = s.split(",");
                return result[2];
            }
        }).window(TumblingProcessingTimeWindows.of(Time.seconds(10))).apply(new JoinFunction<String, String, String>() {
            @Override
            public String join(String s, String s2) throws Exception {
                String [] result1 = s.split(",");
                String [] result2 = s2.split(",");
                //userid,time,desc
                String finalResult = "哈哈=="+result1[2]+","+result1[1]+","+result2[1];
                return finalResult;
            }
        });
        stream3.print();
        try {
            env.execute("TestJoin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
