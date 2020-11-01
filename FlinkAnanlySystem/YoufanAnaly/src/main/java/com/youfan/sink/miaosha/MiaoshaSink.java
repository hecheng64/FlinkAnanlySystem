package com.youfan.sink.miaosha;

import com.youfan.entity.LiuLiangInfo;
import com.youfan.entity.MiaoshaInfoEntity;
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
public class MiaoshaSink implements SinkFunction<MiaoshaInfoEntity> {

    @Override
    public void invoke(MiaoshaInfoEntity value, Context context) throws Exception {
        String timeinfo = value.getTimeinfo();
        long orderPayNums = value.getOrderPayNums();
        String payType = value.getPayType();
        long payNums = value.getPayNums();
        Map<String,String> dataMap = new HashMap<String,String>();
        String id = System.currentTimeMillis()+"";
        dataMap.put("id",id);
        dataMap.put("timeinfoString",timeinfo);
        String timeinfonew = timeinfo.substring(0,8);
        String newFormateDate = DateUtils.transferFormat(timeinfonew,"yyyyMMdd","yyyy-MM-dd");
        dataMap.put("timeinfo",newFormateDate);
        dataMap.put("orderPayNums",orderPayNums+"");
        if(StringUtils.isNotBlank(payType)){
            dataMap.put("payNums",payNums+"");
            dataMap.put("payType",payType+"");
        }
        Set<String> intFieldSet = new HashSet<String>();
        intFieldSet.add("id");
        intFieldSet.add("orderPayNums");
        if(StringUtils.isNotBlank(payType)){
            intFieldSet.add("payNums");
        }
        ClickHouseUtils.insertData("MiaoshaInfo",dataMap,intFieldSet);
    }
}
