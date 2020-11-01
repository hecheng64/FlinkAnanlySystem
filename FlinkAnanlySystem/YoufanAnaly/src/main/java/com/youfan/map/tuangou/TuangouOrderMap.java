package com.youfan.map.tuangou;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.TuanGouInfoEntity;
import com.youfan.utils.DateUtils;
import com.youfan.yewu.OrderInfo;
import com.youfan.yewu.TuangouInfo;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.Date;

/**
 * Created by Administrator on 2020/2/18.
 */
public class TuangouOrderMap implements MapFunction<String, TuanGouInfoEntity> {


    @Override
    public TuanGouInfoEntity map(String s) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String flag = jsonObject.getString("flag");

        OrderInfo orderInfo = JSONObject.parseObject(s,OrderInfo.class);
        Date startTime = orderInfo.getCreateTime();
        String time = startTime.getTime()+"";
        String interTime = DateUtils.getByinterMinute(time);
        Long tuangouId = orderInfo.getTuangouId();
        String userId = orderInfo.getUserId()+"";

        if("hour".equals(flag)){
            interTime = DateUtils.getByinterHour(time);
        }else if ("minute".equals(flag)){
            interTime = DateUtils.getByinterMinute(time);
        }

        TuanGouInfoEntity tuanGouInfoEntity = new TuanGouInfoEntity();

        if(tuangouId!=null && tuangouId != 0l){
            tuanGouInfoEntity.setOrderTimes(1l);
            tuanGouInfoEntity.setUserId(userId);
        }
        tuanGouInfoEntity.setTimeinfo(interTime);
        tuanGouInfoEntity.setGroupByField(interTime);


        return tuanGouInfoEntity;
    }
}
