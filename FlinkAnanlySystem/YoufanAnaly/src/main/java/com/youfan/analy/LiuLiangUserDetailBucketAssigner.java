package com.youfan.analy;

import com.alibaba.fastjson.JSONObject;
import com.youfan.utils.DateUtils;
import org.apache.flink.core.io.SimpleVersionedSerializer;
import org.apache.flink.streaming.api.functions.sink.filesystem.BucketAssigner;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2020/2/19.
 */
public class LiuLiangUserDetailBucketAssigner implements BucketAssigner {
    @Override
    public Object getBucketId(Object o, Context context) {
        System.out.println(o.toString());
        //2018090707 20180907/07
        String dateString = JSONObject.parseObject( o.toString()).getString("timeinfo");
        String result = dateString.substring(0,8)+"/"+dateString.substring(8,10);
        return result;
    }

    @Override
    public SimpleVersionedSerializer getSerializer() {
        return new LiuLiangStringSerializer();
    }

    public static void main(String[] args) {
        String dateString = "2018090707";
        String result = dateString.substring(0,8)+ File.separator+dateString.substring(8,10);
        System.out.println(result);
    }
}

class LiuLiangStringSerializer implements SimpleVersionedSerializer<String>{

    public int getVersion() {
        return 0;
    }

    public byte[] serialize(String s) throws IOException {

        return s.getBytes();
    }

    public String deserialize(int i, byte[] bytes) throws IOException {
        if (i != 77) {
            throw new IOException("version mismatch");
        }else {
            return new String(bytes);

        }
    }
}
