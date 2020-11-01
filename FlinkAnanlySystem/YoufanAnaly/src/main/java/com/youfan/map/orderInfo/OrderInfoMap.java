package com.youfan.map.orderInfo;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.ChannelInfo;
import com.youfan.entity.OrderInfoEntity;
import com.youfan.input.DeviceComomInfo;
import com.youfan.utils.DateUtils;
import com.youfan.yewu.OrderInfo;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.Date;

/**
 * Created by Administrator on 2020/2/18.
 */
public class OrderInfoMap implements MapFunction<String, OrderInfoEntity> {


    @Override
    public OrderInfoEntity map(String s) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String flag = jsonObject.getString("flag");
        OrderInfo orderInfo = JSONObject.parseObject(s,OrderInfo.class);
        Double orderAmount = orderInfo.getOrderAmount();
        String userId = orderInfo.getUserId()+"";
        Date createTime = orderInfo.getCreateTime();
        String timeString = createTime.getTime()+"";
        String interTime =  DateUtils.getByinterHour(timeString);

        if("hour".equals(flag)){
            interTime = DateUtils.getByinterHour(timeString);
        }else if ("minute".equals(flag)){
            interTime = DateUtils.getByinterMinute(timeString);
        }


        OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
        String timeinfo = DateUtils.getByinterMinute(timeString);
        orderInfoEntity.setTimeinfo(interTime);
        orderInfoEntity.setTimes(1l);
        orderInfoEntity.setUserId(userId);
        orderInfoEntity.setGroupByField(timeinfo);
        orderInfoEntity.setOrderAmount(orderAmount);
        return orderInfoEntity;
    }
}
