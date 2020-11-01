package com.youfan.map;

import com.alibaba.fastjson.JSONObject;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * Created by Administrator on 2020/2/18.
 */
public class HourTransferMap implements MapFunction<String, String> {


    @Override
    public String map(String s) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(s);
        jsonObject.put("flag","hour");
        String result = JSONObject.toJSONString(jsonObject);
        return result;
    }
}
