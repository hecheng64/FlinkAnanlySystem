package com.youfan.analy;

import com.alibaba.fastjson.JSONObject;
import com.youfan.utils.DateUtils;
import org.apache.flink.streaming.connectors.fs.Clock;
import org.apache.flink.streaming.connectors.fs.bucketing.BasePathBucketer;
import org.apache.hadoop.fs.Path;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2020/2/19.
 */
public class MyPathBucketer extends BasePathBucketer {
    @Override
    public Path getBucketPath(Clock clock, Path basePath, Object element) {
        String dateMillons = JSONObject.parseObject( element.toString()).getString("visitTime");
        String dateString = DateUtils.getByMillons(dateMillons,"yyyyMMddHH");
        return new Path(basePath + File.separator +dateString);
    }
}
