package com.youfan.analy;

import com.youfan.entity.AdvertisingInfo;
import com.youfan.entity.ChannelInfo;
import com.youfan.map.ChannelUserDetailMap;
import com.youfan.map.ChannelUserDetailTransferMap;
import com.youfan.map.advertising.AdversitingUserDetailTransferMap;
import com.youfan.map.advertising.AdvertisingUserDetailMap;
import com.youfan.reduce.ChannelUserDetailReduce;
import com.youfan.reduce.advertising.AdvertisingUserDetailReduce;
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
public class AdvertisingHourUserDetailAnaly {
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
        DataStream<AdvertisingInfo> map = stream.map(new AdvertisingUserDetailMap());
        DataStream<AdvertisingInfo> reduce = map.keyBy("groupByField").timeWindowAll(Time.hours(1l)).reduce(new AdvertisingUserDetailReduce());
        DataStream<String> mapTransfer = reduce.map(new AdversitingUserDetailTransferMap());

        BucketingSink<String> fileSink = new BucketingSink<String>("hdfs://192.168.246.152:9000/dataanlay/adversiting");
        fileSink.setBucketer(new UserDetailPathBucketer());
        fileSink.setWriter(new StringWriter());
        fileSink.setBatchRolloverInterval(60 * 60 * 1000);

        mapTransfer.addSink(fileSink);
        try {
            env.execute("AdvertisingHourUserDetailAnaly");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
