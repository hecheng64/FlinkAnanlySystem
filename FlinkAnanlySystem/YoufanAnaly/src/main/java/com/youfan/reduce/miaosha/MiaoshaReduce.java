package com.youfan.reduce.miaosha;

import com.youfan.entity.ChannelInfo;
import com.youfan.entity.MiaoshaInfoEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * Created by Administrator on 2020/2/19.
 */

public class MiaoshaReduce implements ReduceFunction<MiaoshaInfoEntity> {
    @Override
    public MiaoshaInfoEntity reduce(MiaoshaInfoEntity miaoshaInfoEntity, MiaoshaInfoEntity t1) throws Exception {
        String groupByField = miaoshaInfoEntity.getGroupByField();
        String timeInfo = miaoshaInfoEntity.getTimeinfo();
        String payType = miaoshaInfoEntity.getPayType();
        long orderPayNums1 = miaoshaInfoEntity.getOrderPayNums();
        long orderPayNums2 = t1.getOrderPayNums();

        MiaoshaInfoEntity miaoshaInfoEntityfinal = new MiaoshaInfoEntity();
        miaoshaInfoEntityfinal.setGroupByField(groupByField);
        miaoshaInfoEntityfinal.setTimeinfo(timeInfo);
        miaoshaInfoEntityfinal.setOrderPayNums(orderPayNums1+orderPayNums2);
        if(StringUtils.isNotBlank(payType)){
            miaoshaInfoEntity.setPayType(payType);
            long payNums1 = miaoshaInfoEntity.getPayNums();
            long payNums2 = t1.getPayNums();
            miaoshaInfoEntity.setPayNums(payNums1+payNums2);
        }
        return miaoshaInfoEntityfinal;
    }
}
