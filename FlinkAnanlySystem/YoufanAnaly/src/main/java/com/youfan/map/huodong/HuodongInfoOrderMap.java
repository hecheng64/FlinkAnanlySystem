package com.youfan.map.huodong;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.HuodongInfoEntity;
import com.youfan.entity.MiaoshaInfoEntity;
import com.youfan.utils.DateUtils;
import com.youfan.yewu.OrderInfo;
import com.youfan.yewu.ZhidingHuoDongInfo;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.Date;

/**
 * Created by Administrator on 2020/2/18.
 */
public class HuodongInfoOrderMap implements MapFunction<String, HuodongInfoEntity> {


    @Override
    public HuodongInfoEntity map(String s) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String flag = jsonObject.getString("flag");
        OrderInfo orderInfo = JSONObject.parseObject(s,OrderInfo.class);
        Long huodongId = orderInfo.getHuodongId();
        Date startTime = orderInfo.getCreateTime();
        String time = startTime.getTime()+"";


        String interTime = DateUtils.getByinterMinute(time);

        MiaoshaInfoEntity miaoshaInfoEntity = new MiaoshaInfoEntity();

        if("hour".equals(flag)){
            interTime = DateUtils.getByinterHour(time);
        }else if ("minute".equals(flag)){
            interTime = DateUtils.getByinterMinute(time);
        }
        HuodongInfoEntity huodongInfoEntity = new HuodongInfoEntity();
        if(huodongId != 0 ){
            huodongInfoEntity.setTimeinfo(interTime);
            huodongInfoEntity.setGroupByField(interTime);
            huodongInfoEntity.setOrderNums(1l);
        }

        return huodongInfoEntity;
    }
}
