package com.youfan.map;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.LiuLiangInfo;
import com.youfan.input.DeviceComomInfo;
import com.youfan.input.ScanPageLog;
import com.youfan.utils.DateUtils;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * Created by Administrator on 2020/2/18.
 */
public class LiuLiangUserDetailTransferMap implements MapFunction<LiuLiangInfo,String> {


    @Override
    public String map(LiuLiangInfo s) throws Exception {
        return JSONObject.toJSONString(s);
    }
}
