package com.youfan.sink.advertising;

import com.youfan.entity.AdvertisingInfo;
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
public class AdvertisinginfoZhuanHuaLvSink implements SinkFunction<AdvertisingInfo> {

    @Override
    public void invoke(AdvertisingInfo value, Context context) throws Exception {
        String timeinfo = value.getTimeinfo();
        long userOrderNums = value.getUserOrderNums();
        String adId = value.getAdId();
        String productId = value.getProductId();
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
        dataMap.put("userOrderNums",userOrderNums+"");
        dataMap.put("adId",adId);
        dataMap.put("productId",productId);
        Set<String> intFieldSet = new HashSet<String>();
        intFieldSet.add("userOrderNums");
        intFieldSet.add("id");
        ClickHouseUtils.insertData("AdvertisingInfo",dataMap,intFieldSet);
    }
}
