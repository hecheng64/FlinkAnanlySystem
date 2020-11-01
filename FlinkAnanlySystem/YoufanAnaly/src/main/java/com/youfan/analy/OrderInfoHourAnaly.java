package com.youfan.analy;

import com.youfan.entity.OrderInfoEntity;
import com.youfan.map.HourTransferMap;
import com.youfan.map.MinuteTransferMap;
import com.youfan.map.orderInfo.OrderInfoMap;
import com.youfan.map.orderInfo.OrderInfoQuchongMap;
import com.youfan.reduce.orderInfo.OrderInfoQuchongReduce;
import com.youfan.reduce.orderInfo.OrderInfoReduce;
import com.youfan.sink.orderinfo.OrderinfoSink;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * Created by Administrator on 2020/2/10.
 */
public class OrderInfoHourAnaly {
    public static void main(String[] args) {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "192.168.246.152:9092");
        properties.setProperty("group.id", "youfantest");
        //构建FlinkKafkaConsumer
        FlinkKafkaConsumer<String> myConsumer = new FlinkKafkaConsumer<>("orderinfo", new SimpleStringSchema(), properties);
//        //指定偏移量
        myConsumer.setStartFromEarliest();


        DataStream<String> stream = env
                .addSource(myConsumer);

        env.enableCheckpointing(5000);
        DataStream<String> transfermap = stream.map(new HourTransferMap());
        DataStream<OrderInfoEntity> map = transfermap.map(new OrderInfoMap());
        DataStream<OrderInfoEntity> reduce = map.keyBy("groupByField").timeWindowAll(Time.minutes(5)).reduce(new OrderInfoReduce());

        DataStream<OrderInfoEntity> reducequchong = map.keyBy("groupByField").timeWindowAll(Time.minutes(5)).reduce(new OrderInfoQuchongReduce());
        DataStream<OrderInfoEntity> mapquchong = reducequchong.map(new OrderInfoQuchongMap());
        DataStream<OrderInfoEntity> userreduce = mapquchong.keyBy("groupByField").timeWindowAll(Time.minutes(5)).reduce(new OrderInfoReduce());
        reduce.addSink(new OrderinfoSink());
        userreduce.addSink(new OrderinfoSink());
        try {
            env.execute("OrderInfoHourAnaly");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
