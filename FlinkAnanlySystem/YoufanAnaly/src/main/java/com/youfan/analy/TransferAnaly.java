package com.youfan.analy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.youfan.entity.ChannelInfo;
import com.youfan.map.ChannelMap;
import com.youfan.map.HourTransferMap;
import com.youfan.reduce.ChannelReduce;
import com.youfan.sink.ChannelinfoSink;
import com.youfan.utils.BinlogUtils;
import com.youfan.utils.HbaseUtils2;
import com.youfan.utils.KafkaUtils;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.Properties;

/**
 * Created by Administrator on 2020/2/10.
 */
public class TransferAnaly {
    public static void main(String[] args) {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "192.168.246.152:9092");
        properties.setProperty("group.id", "youfantest");
        //构建FlinkKafkaConsumer
        FlinkKafkaConsumer<String> myConsumer = new FlinkKafkaConsumer<>("test", new SimpleStringSchema(), properties);
//        //指定偏移量
        myConsumer.setStartFromLatest();


        DataStream<String> stream = env
                .addSource(myConsumer);

        env.enableCheckpointing(5000);
        DataStream<String> filtermap = stream.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String o) throws Exception {
                /**
                 * {"data":[{"id":"11","productName":"maojin","productTypeid":"1","originalPrice":"56.0","huodongPrice":"56.0","shopid":"2"}],"database":"youfands","es":1582849987000,"id":2,"isDdl":false,"mysqlType":{"id":"int(20)","productName":"varchar(50)","productTypeid":"int(20)","originalPrice":"double(20,2)","huodongPrice":"double(20,2)","shopid":"int(20)"},"old":null,"sql":"","sqlType":{"id":4,"productName":12,"productTypeid":4,"originalPrice":8,"huodongPrice":8,"shopid":4},"table":"product","ts":1582850061472,"type":"INSERT"}
                 */
                JSONObject jSONObject = JSONObject.parseObject(o);
                String type = jSONObject.getString("type");
                if("INSERT".equals(type)){
                    return true;
                }
                return false;
            }
        });
        DataStream<String> map = filtermap.map(new MapFunction<String, String>() {
            @Override
            public String map(String s) throws Exception {
                JSONObject jSONObject = JSONObject.parseObject(s);
                String tableName = jSONObject.getString("table");
                JSONArray jsonArray = jSONObject.getJSONArray("data");
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                JSONObject jsonObjectresult = new JSONObject();
                jsonObjectresult.put("tableName",tableName);
                jsonObjectresult.put("data",jsonObject);
                 BinlogUtils.transferAndInsert(tableName,JSONObject.toJSONString(jsonObject));
                 String result = JSONObject.toJSONString(jsonObjectresult);
                return result;
            }
        });
        map.addSink(new SinkFunction<String>() {
            @Override
            public void invoke(String value) throws Exception {
                    JSONObject jsonObject = JSONObject.parseObject(value);
                    String tableName = jsonObject.getString("tableName");
                    String data = jsonObject.getString("data");
                    KafkaUtils.sendData(tableName,data);
            }
        });
        try {
            env.execute("TransferAnaly");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
