package com.youfan.viewResult;

import com.youfan.entity.AdvertisingInfo;
import com.youfan.entity.ChannelInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/3/2.
 */
public class AdvertisingResult {
    private List<String> xlist;//x轴数据
    private List<Map<String,Object>> newuserslist;
    private List<AdvertisingInfo> userDetailList;

    public List<String> getXlist() {
        return xlist;
    }

    public void setXlist(List<String> xlist) {
        this.xlist = xlist;
    }

    public List<Map<String, Object>> getNewuserslist() {
        return newuserslist;
    }

    public void setNewuserslist(List<Map<String, Object>> newuserslist) {
        this.newuserslist = newuserslist;
    }

    public List<AdvertisingInfo> getUserDetailList() {
        return userDetailList;
    }

    public void setUserDetailList(List<AdvertisingInfo> userDetailList) {
        this.userDetailList = userDetailList;
    }
}
