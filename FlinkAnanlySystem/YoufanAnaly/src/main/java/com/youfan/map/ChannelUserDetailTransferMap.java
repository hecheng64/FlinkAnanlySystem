package com.youfan.map;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.ChannelInfo;
import com.youfan.entity.LiuLiangInfo;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * Created by Administrator on 2020/2/18.
 */
public class ChannelUserDetailTransferMap implements MapFunction<ChannelInfo,String> {


    @Override
    public String map(ChannelInfo s) throws Exception {
        return JSONObject.toJSONString(s);
    }
}
