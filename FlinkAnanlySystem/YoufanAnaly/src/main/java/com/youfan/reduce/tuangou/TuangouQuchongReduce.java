package com.youfan.reduce.tuangou;

import com.youfan.entity.TuanGouInfoEntity;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * Created by Administrator on 2020/2/19.
 */

public class TuangouQuchongReduce implements ReduceFunction<TuanGouInfoEntity> {
    @Override
    public TuanGouInfoEntity reduce(TuanGouInfoEntity tuanGouInfoEntity, TuanGouInfoEntity t1) throws Exception {
        String groupByField = tuanGouInfoEntity.getGroupByField();
        String timeInfo = tuanGouInfoEntity.getTimeinfo();
        String userId = tuanGouInfoEntity.getUserId();


        TuanGouInfoEntity tuanGouInfoEntityFinal = new TuanGouInfoEntity();
        tuanGouInfoEntityFinal.setGroupByField(groupByField);
        tuanGouInfoEntityFinal.setTimeinfo(timeInfo);
        tuanGouInfoEntityFinal.setUserId(userId);
        return tuanGouInfoEntityFinal;
    }
}
