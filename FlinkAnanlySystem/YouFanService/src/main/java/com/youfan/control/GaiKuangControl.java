package com.youfan.control;

import com.alibaba.fastjson.JSONObject;
import com.youfan.entity.ChannelInfo;
import com.youfan.service.ChannelService;
import com.youfan.service.GaiKuangService;
import com.youfan.viewResult.ChannelResult;
import com.youfan.viewResult.GaiKuangResult;
import com.youfan.viewResult.GaiKuangResultList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2020/3/2.
 */
@RestController
@RequestMapping("gaiKuang")
@CrossOrigin
public class GaiKuangControl {

    @Autowired
    private GaiKuangService gaiKuangService ;

    @RequestMapping(value = "list",method = RequestMethod.POST)
    public String list(){
        List<GaiKuangResult> list = gaiKuangService.listGaiKuang(50);
        GaiKuangResultList gaiKuangResultList = new GaiKuangResultList();
        gaiKuangResultList.setList(list);
        String finalResult = JSONObject.toJSONString(gaiKuangResultList);
        return finalResult;
    }


}