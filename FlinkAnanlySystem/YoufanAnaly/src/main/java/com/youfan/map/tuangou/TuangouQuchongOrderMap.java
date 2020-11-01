package com.youfan.map.tuangou;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.TuanGouInfoEntity;
import com.youfan.utils.DateUtils;
import com.youfan.yewu.OrderInfo;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.Date;

/**
 * Created by Administrator on 2020/2/18.
 */
public class TuangouQuchongOrderMap implements MapFunction<TuanGouInfoEntity, TuanGouInfoEntity> {


    @Override
    public TuanGouInfoEntity map(TuanGouInfoEntity tuanGouInfoEntity) throws Exception {
        String timeinfo = tuanGouInfoEntity.getTimeinfo();

        TuanGouInfoEntity tuanGouInfoEntityFinal = new TuanGouInfoEntity();
        tuanGouInfoEntityFinal.setTimeinfo(timeinfo);
        tuanGouInfoEntityFinal.setUserNums(1l);


        return tuanGouInfoEntityFinal;

    }
}
