package com.youfan.map.advertising;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.AdvertisingInfo;
import com.youfan.input.DeviceComomInfo;
import com.youfan.input.ScanPageLog;
import com.youfan.utils.DateUtils;
import com.youfan.yewu.OrderInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * Created by Administrator on 2020/2/18.
 */
public class AdvertisingZhuanHuaLvMapSecond implements MapFunction<AdvertisingInfo, AdvertisingInfo> {


    @Override
    public AdvertisingInfo map(AdvertisingInfo advertisingInfo) throws Exception {
        String timeinfo = advertisingInfo.getTimeinfo();//分钟,小时
        String adId = advertisingInfo.getAdId();//广告id
        String productId = advertisingInfo.getProductId();//商品id
        AdvertisingInfo advertisingInfoFinal = new AdvertisingInfo();
        advertisingInfoFinal.setAdId(adId);
        advertisingInfoFinal.setProductId(productId);
        advertisingInfoFinal.setTimeinfo(timeinfo);
        advertisingInfoFinal.setUserOrderNums(1l);
        advertisingInfoFinal.setGroupByField(adId+"=="+productId+"=="+timeinfo);
        return advertisingInfoFinal;
    }
}
