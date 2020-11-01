package com.youfan.map.tuangou;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.ChannelInfo;
import com.youfan.entity.TuanGouInfoEntity;
import com.youfan.input.DeviceComomInfo;
import com.youfan.input.ScanPageLog;
import com.youfan.utils.DateUtils;
import com.youfan.yewu.TuangouInfo;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * Created by Administrator on 2020/2/18.
 */
public class TuangouMap implements MapFunction<String, TuanGouInfoEntity> {


    @Override
    public TuanGouInfoEntity map(String s) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String flag = jsonObject.getString("flag");

        TuangouInfo tuangouInfo = JSONObject.parseObject(s,TuangouInfo.class);
        String productTypeId = tuangouInfo.getProductTypeId()+"";
        String time = tuangouInfo.getTuangouStartTime().getTime()+"";

        String interTime = DateUtils.getByinterMinute(time);

        if("hour".equals(flag)){
            interTime = DateUtils.getByinterHour(time);
        }else if ("minute".equals(flag)){
            interTime = DateUtils.getByinterMinute(time);
        }

        TuanGouInfoEntity tuanGouInfoEntity = new TuanGouInfoEntity();
        tuanGouInfoEntity.setTimes(1l);
        tuanGouInfoEntity.setProductType(productTypeId);
        tuanGouInfoEntity.setTimeinfo(interTime);
        tuanGouInfoEntity.setGroupByField(interTime+"=="+productTypeId);


        return tuanGouInfoEntity;
    }
}
