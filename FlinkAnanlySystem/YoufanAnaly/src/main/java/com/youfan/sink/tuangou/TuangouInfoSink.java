package com.youfan.sink.tuangou;

import com.youfan.entity.TuanGouInfoEntity;
import com.youfan.utils.ClickHouseUtils;
import com.youfan.utils.DateUtils;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2020/3/7.
 */
public class TuangouInfoSink implements SinkFunction<TuanGouInfoEntity> {
    @Override
    public void invoke(TuanGouInfoEntity value) throws Exception {
        String timeinfo = value.getTimeinfo();
        long times = value.getTimes();
        long orderTimes = value.getOrderTimes();
        long userNums = value.getUserNums();
        String productType = value.getProductType();
        Map<String,String> dataMap = new HashMap<String,String>();
        String id = System.currentTimeMillis()+productType;
        String timeinfonew = timeinfo.substring(0,8);
        String newFormateDate = DateUtils.transferFormat(timeinfonew,"yyyyMMdd","yyyy-MM-dd");
        dataMap.put("timeinfo",newFormateDate);
        dataMap.put("id",id);
        dataMap.put("timeinfoString",timeinfo);
        if(orderTimes == 0 || userNums == 0 ){
            dataMap.put("productType",productType);
        }
        dataMap.put("orderTimes",orderTimes+"");
        dataMap.put("times",times+"");
        dataMap.put("userNums",userNums+"");
        Set<String> intFieldSet = new HashSet<String>();
        intFieldSet.add("id");
        intFieldSet.add("times");
        intFieldSet.add("orderTimes");
        intFieldSet.add("userNums");
        ClickHouseUtils.insertData("TuangouInfo",dataMap,intFieldSet);
    }
}
