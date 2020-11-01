package com.youfan.reduce.orderInfo;

import com.youfan.entity.OrderInfoEntity;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * Created by Administrator on 2020/2/19.
 */

public class OrderInfoQuchongReduce implements ReduceFunction<OrderInfoEntity> {
    @Override
    public OrderInfoEntity reduce(OrderInfoEntity orderInfoEntity, OrderInfoEntity t1) throws Exception {
        String groupByField = orderInfoEntity.getGroupByField();
        String timeInfo = orderInfoEntity.getTimeinfo();
        String userId = orderInfoEntity.getUserId();
        OrderInfoEntity orderInfoEntity1 = new OrderInfoEntity();
        orderInfoEntity1.setGroupByField(groupByField);
        orderInfoEntity1.setTimeinfo(timeInfo);
        orderInfoEntity1.setUserId(userId);
        return orderInfoEntity1;
    }
}
