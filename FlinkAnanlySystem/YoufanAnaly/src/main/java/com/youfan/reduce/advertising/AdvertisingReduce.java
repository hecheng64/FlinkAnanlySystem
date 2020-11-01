package com.youfan.reduce.advertising;

import com.youfan.entity.AdvertisingInfo;
import com.youfan.entity.ChannelInfo;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * Created by Administrator on 2020/2/19.
 */

public class AdvertisingReduce implements ReduceFunction<AdvertisingInfo> {
    @Override
    public AdvertisingInfo reduce(AdvertisingInfo advertisingInfo,AdvertisingInfo t1) throws Exception {
        String groupByField = advertisingInfo.getGroupByField();
        String timeInfo = advertisingInfo.getTimeinfo();
        String productId = advertisingInfo.getProductId();
        String adId = advertisingInfo.getAdId();
        long times1 = advertisingInfo.getTimes();
        long times2 = t1.getTimes();

        long userNums1 = advertisingInfo.getUserNums();
        long userNums2 = t1.getUserNums();


        AdvertisingInfo advertisingInfofinal = new AdvertisingInfo();
        advertisingInfofinal.setGroupByField(groupByField);
        advertisingInfofinal.setTimeinfo(timeInfo);
        advertisingInfofinal.setTimes(times1+times2);
        advertisingInfofinal.setAdId(adId);
        advertisingInfofinal.setProductId(productId);
        advertisingInfofinal.setUserNums(userNums1+userNums2);
        return advertisingInfofinal;
    }
}
