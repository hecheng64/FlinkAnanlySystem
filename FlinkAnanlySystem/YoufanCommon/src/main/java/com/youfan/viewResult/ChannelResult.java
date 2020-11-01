package com.youfan.viewResult;

import com.youfan.entity.ChannelInfo;
import com.youfan.entity.LiuLiangInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/3/2.
 */
public class ChannelResult {
    private List<String> xlist;//x轴数据
    private List<Map<String,Object>> newuserslist;
    private List<ChannelInfo> userDetailList;

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

    public List<ChannelInfo> getUserDetailList() {
        return userDetailList;
    }

    public void setUserDetailList(List<ChannelInfo> userDetailList) {
        this.userDetailList = userDetailList;
    }
}
