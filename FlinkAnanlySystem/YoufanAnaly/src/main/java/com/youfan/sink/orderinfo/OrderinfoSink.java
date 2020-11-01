package com.youfan.sink.orderinfo;

import com.youfan.entity.ChannelInfo;
import com.youfan.entity.OrderInfoEntity;
import com.youfan.utils.ClickHouseUtils;
import com.youfan.utils.DateUtils;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2020/2/19.
 */
public class OrderinfoSink implements SinkFunction<OrderInfoEntity> {

    @Override
    public void invoke(OrderInfoEntity value, Context context) throws Exception {
        String timeinfo = value.getTimeinfo();
        long times = value.getTimes();
        Double orderAmount = value.getOrderAmount();

        Map<String,String> dataMap = new HashMap<String,String>();
        String id = System.currentTimeMillis()+"";
        String timeinfonew = timeinfo.substring(0,8);
        String newFormateDate = DateUtils.transferFormat(timeinfonew,"yyyyMMdd","yyyy-MM-dd");
        dataMap.put("timeinfo",newFormateDate);
        dataMap.put("id",id);
        dataMap.put("timeinfoString",timeinfo);
        dataMap.put("times",times+"");
        dataMap.put("orderAmount",orderAmount+"");
        if(value.getUserNum() !=0){
            dataMap.put("userNum",value.getUserNum()+"");
        }
        Set<String> intFieldSet = new HashSet<String>();
        intFieldSet.add("id");
        intFieldSet.add("times");
        if(value.getUserNum() !=0){
            intFieldSet.add("userNum");
        }
        ClickHouseUtils.insertData("OrderInfo",dataMap,intFieldSet);


    }
}
