package com.youfan.reduce.tuangou;

import com.youfan.entity.ChannelInfo;
import com.youfan.entity.TuanGouInfoEntity;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * Created by Administrator on 2020/2/19.
 */

public class TuangouReduce implements ReduceFunction<TuanGouInfoEntity> {
    @Override
    public TuanGouInfoEntity reduce(TuanGouInfoEntity tuanGouInfoEntity, TuanGouInfoEntity t1) throws Exception {
        String groupByField = tuanGouInfoEntity.getGroupByField();
        String timeInfo = tuanGouInfoEntity.getTimeinfo();
        String productType = tuanGouInfoEntity.getProductType();
        long times1 = tuanGouInfoEntity.getTimes();
        long times2 = t1.getTimes();

        long orderTimes1 = tuanGouInfoEntity.getOrderTimes();
        long orderTimes2 = t1.getOrderTimes();

        long userNums1 = tuanGouInfoEntity.getUserNums();

        long userNums2 = t1.getUserNums();

        TuanGouInfoEntity tuanGouInfoEntityFinal = new TuanGouInfoEntity();
        tuanGouInfoEntityFinal.setGroupByField(groupByField);
        tuanGouInfoEntityFinal.setTimeinfo(timeInfo);
        tuanGouInfoEntityFinal.setProductType(productType);
        tuanGouInfoEntityFinal.setTimes(times1+times2);
        tuanGouInfoEntityFinal.setOrderTimes(orderTimes1+orderTimes2);
        tuanGouInfoEntityFinal.setUserNums(userNums1+userNums2);
        return tuanGouInfoEntityFinal;
    }
}
