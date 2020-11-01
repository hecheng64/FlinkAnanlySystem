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
public class AdvertisingZhuanHuaLvMap implements MapFunction<String, AdvertisingInfo> {


    @Override
    public AdvertisingInfo map(String s) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(s);
        ScanPageLog scanPageLog = jsonObject.getObject("scanPageLog",ScanPageLog.class);
        OrderInfo orderInfo = jsonObject.getObject("scanPageLog",OrderInfo.class);
        String visitTime = scanPageLog.getVisitTime();

        String deviceComomInfoString = jsonObject.getString("deviceComomInfo");
        DeviceComomInfo deviceComomInfo = JSONObject.parseObject(deviceComomInfoString,DeviceComomInfo.class);
        String userId = deviceComomInfo.getUserId();
        String adId = scanPageLog.getAdId();
        String productId = scanPageLog.getProductId();
        String interTime = DateUtils.getByinterHour(visitTime);
        AdvertisingInfo advertisingInfo = new AdvertisingInfo();

        if(StringUtils.isNotBlank(adId)){
               advertisingInfo.setTimeinfo(interTime);
               advertisingInfo.setUserId(userId);
               advertisingInfo.setGroupByField(productId+"=="+interTime+"=="+adId+"=="+userId);
               advertisingInfo.setProductId(productId);
               advertisingInfo.setAdId(adId);
        }
        return advertisingInfo;



    }
}
