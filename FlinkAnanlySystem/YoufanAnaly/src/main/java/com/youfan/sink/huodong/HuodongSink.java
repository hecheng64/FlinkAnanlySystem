package com.youfan.sink.huodong;

import com.youfan.entity.HuodongInfoEntity;
import com.youfan.entity.LiuLiangInfo;
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
public class HuodongSink implements SinkFunction<HuodongInfoEntity> {

    @Override
    public void invoke(HuodongInfoEntity value, Context context) throws Exception {
        String timeinfo = value.getTimeinfo();

        String huodongArea = value.getHuodongArea();
        long areaNum = value.getAreaNum();
        long orderNums = value.getOrderNums();

        Map<String,String> dataMap = new HashMap<String,String>();
        String id = System.currentTimeMillis()+"";
        dataMap.put("id",id);
        dataMap.put("timeinfoString",timeinfo);
        String timeinfonew = timeinfo.substring(0,8);
        String newFormateDate = DateUtils.transferFormat(timeinfonew,"yyyyMMdd","yyyy-MM-dd");
        dataMap.put("timeinfo",newFormateDate);
        dataMap.put("huodongArea",huodongArea);
        dataMap.put("areaNum",areaNum+"");
        dataMap.put("orderNums",orderNums+"");
        Set<String> intFieldSet = new HashSet<String>();
        intFieldSet.add("id");
        intFieldSet.add("areaNum");
        intFieldSet.add("orderNums");
        ClickHouseUtils.insertData("HuodongInfo",dataMap,intFieldSet);
    }
}
