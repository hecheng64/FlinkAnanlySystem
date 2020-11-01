package com.youfan.map.huodong;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.HuodongInfoEntity;
import com.youfan.entity.MiaoshaInfoEntity;
import com.youfan.utils.DateUtils;
import com.youfan.yewu.OrderInfo;
import com.youfan.yewu.ZhidingHuoDongInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.Date;

/**
 * Created by Administrator on 2020/2/18.
 */
public class HuodongInfoMap implements MapFunction<String, HuodongInfoEntity> {


    @Override
    public HuodongInfoEntity map(String s) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String flag = jsonObject.getString("flag");
        ZhidingHuoDongInfo zhidingHuoDongInfo = JSONObject.parseObject(s,ZhidingHuoDongInfo.class);
        String huodongArea = zhidingHuoDongInfo.getHuodongArea();
        Date startTime = zhidingHuoDongInfo.getHuodongStartTime();
        String time = startTime.getTime()+"";


        String interTime = DateUtils.getByinterMinute(time);

        MiaoshaInfoEntity miaoshaInfoEntity = new MiaoshaInfoEntity();

        if("hour".equals(flag)){
            interTime = DateUtils.getByinterHour(time);
        }else if ("minute".equals(flag)){
            interTime = DateUtils.getByinterMinute(time);
        }
        HuodongInfoEntity huodongInfoEntity = new HuodongInfoEntity();
        huodongInfoEntity.setTimeinfo(interTime);
        huodongInfoEntity.setAreaNum(1l);
        huodongInfoEntity.setHuodongArea(huodongArea);
        huodongInfoEntity.setGroupByField(interTime+"=="+huodongArea);
        return huodongInfoEntity;
    }
}
