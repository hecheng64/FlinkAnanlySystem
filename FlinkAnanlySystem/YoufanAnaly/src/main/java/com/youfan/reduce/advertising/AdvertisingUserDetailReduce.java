package com.youfan.reduce.advertising;

import com.youfan.entity.AdvertisingInfo;
import com.youfan.entity.ChannelInfo;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * Created by Administrator on 2020/2/19.
 */

public class AdvertisingUserDetailReduce implements ReduceFunction<AdvertisingInfo> {
    @Override
    public AdvertisingInfo reduce(AdvertisingInfo advertisingInfo, AdvertisingInfo t1) throws Exception {
        long times1 = advertisingInfo.getTimes();
        long times2 = t1.getTimes();


        AdvertisingInfo advertisingInfofinal = new  AdvertisingInfo();
        advertisingInfofinal.setGroupByField(advertisingInfo.getGroupByField());
        advertisingInfofinal.setTimes(times1+times2);
        advertisingInfofinal.setDeviceType(advertisingInfo.getDeviceType());
        advertisingInfofinal.setTimeinfo(advertisingInfo.getTimeinfo());
        advertisingInfofinal.setUserId(advertisingInfo.getUserId());
        advertisingInfofinal.setAdId(advertisingInfo.getAdId());
        advertisingInfofinal.setProductId(advertisingInfo.getProductId());
        return  advertisingInfofinal;
    }
}
