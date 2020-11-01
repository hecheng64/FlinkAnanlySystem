package com.youfan.analy;

import com.youfan.entity.TuanGouInfoEntity;
import com.youfan.map.HourTransferMap;
import com.youfan.map.MinuteTransferMap;
import com.youfan.map.tuangou.TuangouOrderMap;
import com.youfan.map.tuangou.TuangouQuchongOrderMap;
import com.youfan.reduce.tuangou.TuangouQuchongReduce;
import com.youfan.reduce.tuangou.TuangouReduce;
import com.youfan.sink.tuangou.TuangouInfoSink;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * Created by Administrator on 2020/2/10.
 */
public class TuangouInfoOrderMinuteAnaly {
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
        DataStream<String> transfermap = stream.map(new MinuteTransferMap());
        DataStream<TuanGouInfoEntity> map = transfermap.map(new TuangouOrderMap());
        DataStream<TuanGouInfoEntity> reduce = map.keyBy("groupByField").timeWindowAll(Time.minutes(5)).reduce(new TuangouReduce());
        reduce.addSink(new TuangouInfoSink());

        DataStream<TuanGouInfoEntity> quchongreduce = map.keyBy("groupByField").timeWindowAll(Time.minutes(5)).reduce(new TuangouQuchongReduce());
        DataStream<TuanGouInfoEntity> quchongmap = quchongreduce.map(new TuangouQuchongOrderMap());
        DataStream<TuanGouInfoEntity> userreduce = quchongmap.keyBy("groupByField").timeWindowAll(Time.minutes(5)).reduce(new TuangouReduce());
        userreduce.addSink(new TuangouInfoSink());

        try {
            env.execute("TuangouInfoOrderMinuteAnaly");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
