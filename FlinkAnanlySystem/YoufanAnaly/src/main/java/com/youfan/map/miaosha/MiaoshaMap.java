package com.youfan.map.miaosha;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.ChannelInfo;
import com.youfan.entity.MiaoshaInfoEntity;
import com.youfan.input.DeviceComomInfo;
import com.youfan.input.ScanPageLog;
import com.youfan.utils.DateUtils;
import com.youfan.yewu.OrderInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.Date;

/**
 * Created by Administrator on 2020/2/18.
 */
public class MiaoshaMap implements MapFunction<String, MiaoshaInfoEntity> {


    @Override
    public MiaoshaInfoEntity map(String s) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String flag = jsonObject.getString("flag");
        String payTypeflag = jsonObject.getString("payTypeflag");
        OrderInfo orderInfo = JSONObject.parseObject(s,OrderInfo.class);
        String payType = orderInfo.getPayType()+"";
        Date createTime = orderInfo.getCreateTime();
        String time = createTime.getTime()+"";
        Integer status = orderInfo.getStatus();
        Long miaoshaId = orderInfo.getMiaoshaId();

        String interTime = DateUtils.getByinterMinute(time);

        MiaoshaInfoEntity miaoshaInfoEntity = new MiaoshaInfoEntity();

        if("hour".equals(flag)){
            interTime = DateUtils.getByinterHour(time);
        }else if ("minute".equals(flag)){
            interTime = DateUtils.getByinterMinute(time);
        }
        if(miaoshaId != null && miaoshaId != 0 &&status == 1){
            miaoshaInfoEntity.setTimeinfo(interTime);
            miaoshaInfoEntity.setGroupByField(interTime);
            miaoshaInfoEntity.setOrderPayNums(1l);
            if(StringUtils.isNotBlank(payTypeflag)&&"true".equals(payTypeflag)){
                miaoshaInfoEntity.setPayType(payType);
                miaoshaInfoEntity.setPayNums(1l);
                miaoshaInfoEntity.setGroupByField(interTime+"=="+payType);
            }
        }
        return miaoshaInfoEntity;
    }
}
