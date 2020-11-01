package com.youfan.analy;

import com.alibaba.fastjson.JSONObject;
import com.youfan.utils.DateUtils;
import org.apache.flink.streaming.connectors.fs.Clock;
import org.apache.flink.streaming.connectors.fs.bucketing.BasePathBucketer;
import org.apache.hadoop.fs.Path;

import java.io.File;

/**
 * Created by Administrator on 2020/2/19.
 */
public class UserDetailPathBucketer extends BasePathBucketer {
    @Override
    public Path getBucketPath(Clock clock, Path basePath, Object element) {
        String dateString = JSONObject.parseObject( element.toString()).getString("timeinfo");
        String result = dateString.substring(0,8)+"/"+dateString.substring(8,10);
        System.out.println("哈哈哈=="+result);
        return new Path(basePath+"/"+result);
    }
}
