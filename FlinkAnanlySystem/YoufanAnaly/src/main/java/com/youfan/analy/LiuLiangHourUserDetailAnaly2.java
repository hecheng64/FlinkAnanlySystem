package com.youfan.analy;

import com.youfan.entity.LiuLiangInfo;
import com.youfan.map.LiuLiangUserDetailMap;
import com.youfan.map.LiuLiangUserDetailTransferMap;
import com.youfan.reduce.LiuLiangUserDetailReduce;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.fs.StringWriter;
import org.apache.flink.streaming.connectors.fs.bucketing.BucketingSink;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * Created by Administrator on 2020/2/10.
 */
public class LiuLiangHourUserDetailAnaly2 {
    public static void main(String[] args) {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "192.168.246.152:9092");
        properties.setProperty("group.id", "youfantest");
        //构建FlinkKafkaConsumer
        FlinkKafkaConsumer<String> myConsumer = new FlinkKafkaConsumer<>("datainfo", new SimpleStringSchema(), properties);
//        //指定偏移量
        myConsumer.setStartFromLatest();


        DataStream<String> stream = env
                .addSource(myConsumer);

        env.enableCheckpointing(5000);
        DataStream<LiuLiangInfo> map = stream.map(new LiuLiangUserDetailMap());
        DataStream<LiuLiangInfo> reduce = map.keyBy("groupByField").timeWindowAll(Time.seconds(5l)).reduce(new LiuLiangUserDetailReduce());
        DataStream<String> mapTransfer = reduce.map(new LiuLiangUserDetailTransferMap());

        BucketingSink<String> fileSink = new BucketingSink<String>("hdfs://192.168.246.152:9000/dataanlay/liuliang");
        fileSink.setBucketer(new UserDetailPathBucketer());
        fileSink.setWriter(new StringWriter());
        fileSink.setBatchRolloverInterval(5 * 1000);

        mapTransfer.addSink(fileSink);
        try {
            env.execute("LiuLiangHourUserDetailAnaly");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
