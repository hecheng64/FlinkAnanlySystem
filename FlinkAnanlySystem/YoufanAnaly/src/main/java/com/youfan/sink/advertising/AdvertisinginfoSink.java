package com.youfan.sink.advertising;

import com.youfan.entity.AdvertisingInfo;
import com.youfan.entity.ChannelInfo;
import com.youfan.utils.ClickHouseUtils;
import com.youfan.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2020/2/19.
 */
public class AdvertisinginfoSink implements SinkFunction<AdvertisingInfo> {

    @Override
    public void invoke(AdvertisingInfo value, Context context) throws Exception {
        String timeinfo = value.getTimeinfo();
        long times = value.getTimes();
        String adId = value.getAdId();
        String productId = value.getProductId();
        long userNums = value.getUserNums();
        if(StringUtils.isBlank(timeinfo)){
            return;
        }
        Map<String,String> dataMap = new HashMap<String,String>();
        String id = System.currentTimeMillis()+adId+productId;
        dataMap.put("id",id);
        String timeinfonew = timeinfo.substring(0,8);
        String newFormateDate = DateUtils.transferFormat(timeinfonew,"yyyyMMdd","yyyy-MM-dd");
        dataMap.put("timeinfo",newFormateDate);
        dataMap.put("timeinfoString",timeinfo);
        dataMap.put("times",times+"");
        dataMap.put("adId",adId);
        dataMap.put("productId",productId);
        dataMap.put("userNums",userNums+"");
        Set<String> intFieldSet = new HashSet<String>();
        intFieldSet.add("times");
        intFieldSet.add("userNums");
        intFieldSet.add("id");
        ClickHouseUtils.insertData("AdvertisingInfo",dataMap,intFieldSet);
    }

    public static void main(String[] args) {
        String test = "20190807 14";
        String timeinfonew = test.substring(0,8);
        String newFormateDate = DateUtils.transferFormat(timeinfonew,"yyyyMMdd","yyyy-MM-dd");
        System.out.println(newFormateDate);
    }
}
