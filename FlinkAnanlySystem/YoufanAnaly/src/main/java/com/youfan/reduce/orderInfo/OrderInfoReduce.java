package com.youfan.reduce.orderInfo;

import com.youfan.entity.ChannelInfo;
import com.youfan.entity.OrderInfoEntity;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * Created by Administrator on 2020/2/19.
 */

public class OrderInfoReduce implements ReduceFunction<OrderInfoEntity> {
    @Override
    public OrderInfoEntity reduce(OrderInfoEntity orderInfoEntity, OrderInfoEntity t1) throws Exception {
        String groupByField = orderInfoEntity.getGroupByField();
        String timeInfo = orderInfoEntity.getTimeinfo();

        long times1 = orderInfoEntity.getTimes();
        long times2 = t1.getTimes();

        Double orderAmount1 = orderInfoEntity.getOrderAmount();
        Double orderAmount2 = t1.getOrderAmount();


        OrderInfoEntity orderInfoEntity1 = new OrderInfoEntity();
        orderInfoEntity1.setGroupByField(groupByField);
        orderInfoEntity1.setTimes(times1+times2);
        orderInfoEntity1.setTimeinfo(timeInfo);
        orderInfoEntity1.setOrderAmount(orderAmount1+orderAmount2);
        if(orderInfoEntity.isUserNumFlag()){
            long userNum1 = orderInfoEntity.getUserNum();
            long userNum2 = t1.getUserNum();

            orderInfoEntity1.setUserNum(userNum1+userNum2);
        }
        return orderInfoEntity1;
    }
}
