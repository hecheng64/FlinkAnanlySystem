package com.youfan.utils;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2020/2/28.
 */
public class KafkaUtils {
    private static Properties getProps(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.246.152:9092");
        props.put("acks", "all"); // 发送所有ISR
        props.put("retries", 2); // 重试次数
//        props.put("batch.size", 16384); // 批量发送大小
//        props.put("buffer.memory", 33554432); // 缓存大小，根据本机内存大小配置
        props.put("linger.ms", 1000); // 发送频率，满足任务一个条件发送
        props.put("client.id", "producer-syn-1"); // 发送端id,便于统计
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    public static void sendData(String topicName,String data){
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(getProps());
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName,data);
        Future<RecordMetadata> metadataFuture = producer.send(record);
        try {
            RecordMetadata recordMetadata = metadataFuture.get();
            System.out.println("topic:"+recordMetadata.topic());
            System.out.println("partition:"+recordMetadata.partition());
            System.out.println("offset:"+recordMetadata.offset());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        sendData("test","{\"id\":\"1\"}");
    }
}
