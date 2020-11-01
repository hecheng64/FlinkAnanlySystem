package com.youfan.map.advertising;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.AdvertisingInfo;
import com.youfan.entity.ChannelInfo;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * Created by Administrator on 2020/2/18.
 */
public class AdversitingUserDetailTransferMap implements MapFunction<AdvertisingInfo,String> {


    @Override
    public String map(AdvertisingInfo s) throws Exception {
        return JSONObject.toJSONString(s);
    }
}
