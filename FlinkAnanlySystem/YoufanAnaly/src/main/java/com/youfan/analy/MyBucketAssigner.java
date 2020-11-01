package com.youfan.analy;

import com.alibaba.fastjson.JSONObject;
import com.youfan.utils.DateUtils;
import org.apache.flink.core.io.SimpleVersionedSerializer;
import org.apache.flink.streaming.api.functions.sink.filesystem.BucketAssigner;

import java.io.IOException;

/**
 * Created by Administrator on 2020/2/19.
 */
public class MyBucketAssigner implements BucketAssigner {
    @Override
    public Object getBucketId(Object o, Context context) {
        System.out.println(o.toString());
        String dateMillons = JSONObject.parseObject( o.toString()).getString("visitTime");
        String dateString = DateUtils.getByMillons(dateMillons,"yyyyMMddHH");
        return dateString;
    }

    @Override
    public SimpleVersionedSerializer getSerializer() {
        return new StringSerializer();
    }
}
class StringSerializer implements SimpleVersionedSerializer<String>{

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

