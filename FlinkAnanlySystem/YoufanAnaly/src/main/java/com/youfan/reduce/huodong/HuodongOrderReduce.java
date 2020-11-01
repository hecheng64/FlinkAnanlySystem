package com.youfan.reduce.huodong;

import com.youfan.entity.HuodongInfoEntity;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * Created by Administrator on 2020/2/19.
 */

public class HuodongOrderReduce implements ReduceFunction<HuodongInfoEntity> {
    @Override
    public HuodongInfoEntity reduce(HuodongInfoEntity huodongInfoEntity, HuodongInfoEntity t1) throws Exception {
        String groupByField = huodongInfoEntity.getGroupByField();
        String timeInfo = huodongInfoEntity.getTimeinfo();

        long orderNums1 = huodongInfoEntity.getOrderNums();
        long orderNums2 = t1.getOrderNums();

        HuodongInfoEntity huodongInfoEntityfinal = new HuodongInfoEntity();
        huodongInfoEntityfinal.setGroupByField(groupByField);
        huodongInfoEntityfinal.setTimeinfo(timeInfo);
        huodongInfoEntityfinal.setOrderNums(orderNums1+orderNums2);
        return huodongInfoEntityfinal;
    }
}
