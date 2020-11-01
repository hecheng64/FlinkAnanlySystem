package com.youfan.map.orderInfo;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.OrderInfoEntity;
import com.youfan.utils.DateUtils;
import com.youfan.yewu.OrderInfo;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.Date;

/**
 * Created by Administrator on 2020/2/18.
 */
public class OrderInfoQuchongMap implements MapFunction<OrderInfoEntity, OrderInfoEntity> {


    @Override
    public OrderInfoEntity map(OrderInfoEntity orderInfoEntity) throws Exception {
        orderInfoEntity.setUserNum(1l);
        orderInfoEntity.setUserNumFlag(true);
        orderInfoEntity.setGroupByField(orderInfoEntity.getTimeinfo());
        return orderInfoEntity;
    }
}
