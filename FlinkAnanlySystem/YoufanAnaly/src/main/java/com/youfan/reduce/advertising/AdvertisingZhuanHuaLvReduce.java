package com.youfan.reduce.advertising;

import com.youfan.entity.AdvertisingInfo;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * Created by Administrator on 2020/2/19.
 */

public class AdvertisingZhuanHuaLvReduce implements ReduceFunction<AdvertisingInfo> {
    @Override
    public AdvertisingInfo reduce(AdvertisingInfo advertisingInfo,AdvertisingInfo t1) throws Exception {
        String groupByField = advertisingInfo.getGroupByField();
        String timeInfo = advertisingInfo.getTimeinfo();
        String productId = advertisingInfo.getProductId();
        String adId = advertisingInfo.getAdId();

        AdvertisingInfo advertisingInfofinal = new AdvertisingInfo();
        advertisingInfofinal.setGroupByField(groupByField);
        advertisingInfofinal.setTimeinfo(timeInfo);
        advertisingInfofinal.setAdId(adId);
        advertisingInfofinal.setProductId(productId);
        return advertisingInfofinal;
    }
}
