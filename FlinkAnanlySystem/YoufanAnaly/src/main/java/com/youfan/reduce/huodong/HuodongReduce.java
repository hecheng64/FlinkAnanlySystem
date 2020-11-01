package com.youfan.reduce.huodong;

import com.youfan.entity.HuodongInfoEntity;
import com.youfan.entity.MiaoshaInfoEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * Created by Administrator on 2020/2/19.
 */

public class HuodongReduce implements ReduceFunction<HuodongInfoEntity> {
    @Override
    public HuodongInfoEntity reduce(HuodongInfoEntity huodongInfoEntity, HuodongInfoEntity t1) throws Exception {
        String groupByField = huodongInfoEntity.getGroupByField();
        String timeInfo = huodongInfoEntity.getTimeinfo();
        String huodongArea = huodongInfoEntity.getHuodongArea();

        long areaNum1 = huodongInfoEntity.getAreaNum();
        long areaNum2 = t1.getAreaNum();

        HuodongInfoEntity huodongInfoEntityfinal = new HuodongInfoEntity();
        huodongInfoEntityfinal.setGroupByField(groupByField);
        huodongInfoEntityfinal.setTimeinfo(timeInfo);
        huodongInfoEntityfinal.setHuodongArea(huodongArea);
        huodongInfoEntityfinal.setAreaNum(areaNum1+areaNum2);
        return huodongInfoEntityfinal;
    }
}
