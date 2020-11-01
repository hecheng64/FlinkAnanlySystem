package com.youfan.service;

import com.youfan.utils.ClickHouseUtils;
import com.youfan.viewResult.GaiKuangResult;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.*;

/**
 * Created by Administrator on 2020/3/8.
 */
@Service
public class GaiKuangService {

    public List<GaiKuangResult> listGaiKuang(int rows){
        List<GaiKuangResult> gaiKuangResultList = new ArrayList<GaiKuangResult>();
        String sql = "select timeinfoString,scantimes,newusers,dayActivenums,ordernums,orderusernums from (select timeinfoString,sum(times) scantimes,sum(newusers) newusers,sum(dayActivenums) dayActivenums  from LiuLiangInfo group by timeinfoString) any left join (select timeinfoString,sum(times) ordernums,sum(userNum) orderusernums from OrderInfo" +
                " group by timeinfoString)  using timeinfoString where length(toString(timeinfoString)) < 12 and length(toString(timeinfoString)) >= 10 order by timeinfoString desc limit "+rows;
        try {
            ResultSet resultSet = ClickHouseUtils.getQueryResult("youfands", sql);
            while (resultSet.next()) {
                GaiKuangResult gaiKuangResult = new GaiKuangResult();
                String timeinfoString = resultSet.getString("timeinfoString");
                Long scantimes = resultSet.getLong("scantimes");
                Long newusers = resultSet.getLong("newusers");
                Long dayActivenums = resultSet.getLong("dayActivenums");
                Long ordernums = resultSet.getLong("ordernums");
                Long orderusernums = resultSet.getLong("orderusernums");
                gaiKuangResult.setTimeinfoString(timeinfoString);
                gaiKuangResult.setScantimes(scantimes);
                gaiKuangResult.setNewusers(newusers);
                gaiKuangResult.setDayActivenums(dayActivenums);
                gaiKuangResult.setOrdernums(ordernums);
                gaiKuangResult.setOrderusernums(orderusernums);
                gaiKuangResultList.add(gaiKuangResult);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return gaiKuangResultList;
    }
}
