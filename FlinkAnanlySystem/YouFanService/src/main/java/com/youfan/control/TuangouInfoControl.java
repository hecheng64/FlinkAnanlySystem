package com.youfan.control;

import com.alibaba.fastjson.JSONObject;
import com.youfan.service.OrderinfoService;
import com.youfan.service.TuangouService;
import com.youfan.viewResult.*;
import com.youfan.yewu.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/3/2.
 */
@RestController
@RequestMapping("tuangouInfo")
@CrossOrigin
public class TuangouInfoControl {

    @Autowired
    private TuangouService tuangouService;

    @RequestMapping(value = "orderTimesHour",method = RequestMethod.POST)
    public String orderTimesHour(){
        TuanGouInfoResult tuanGouInfoResult = tuangouService.orderTimesHour();
        String finalString = JSONObject.toJSONString(tuanGouInfoResult);
        return finalString;
    }
    @RequestMapping(value = "orderTimesMinute",method = RequestMethod.POST)
    public String orderTimesMinute() {
        TuanGouInfoResult tuanGouInfoResult = tuangouService.orderTimesMinute();
        String finalString = JSONObject.toJSONString(tuanGouInfoResult);
        return finalString;
    }
    @RequestMapping(value = "userNumsHour",method = RequestMethod.POST)
    public String userNumsHour() {
        TuanGouInfoResult tuanGouInfoResult = tuangouService.userNumsHour();
        String finalString = JSONObject.toJSONString(tuanGouInfoResult);
        return finalString;
    }
    @RequestMapping(value = "userNumsMinute",method = RequestMethod.POST)
    public String userNumsMinute(){
        TuanGouInfoResult tuanGouInfoResult = tuangouService.userNumsMinute();
        String finalString = JSONObject.toJSONString(tuanGouInfoResult);
        return finalString;
    }

    @RequestMapping(value = "productTypeHour",method = RequestMethod.POST)
    public String productTypeHour(String productTypeId){
        TuanGouInfoResult tuanGouInfoResult = tuangouService.productTypeHour(productTypeId);
        String finalString = JSONObject.toJSONString(tuanGouInfoResult);
        return finalString;
    }

    @RequestMapping(value = "productTypeMinute",method = RequestMethod.POST)
    public String productTypeMinute(String productTypeId){
        TuanGouInfoResult tuanGouInfoResult = tuangouService.productTypeMinute(productTypeId);
        String finalString = JSONObject.toJSONString(tuanGouInfoResult);
        return finalString;
    }

    @RequestMapping(value = "listProductTypeBy",method = RequestMethod.POST)
    public String  listProductTypeBy(String productTypeName,String productTitle){
        //todo 请求第三方内部业务系统接口，查询数据
        List<ProductType> resultList = new ArrayList<ProductType>();
        ProductType productType = new ProductType();
        productType.setId(1l);
        productType.setProductTypeName("衣服");
        resultList.add(productType);
        productType = new ProductType();
        productType.setId(2l);
        productType.setProductTypeName("电器");
        resultList.add(productType);
        TuanGouInfoListResult tuanGouInfoListResult = new TuanGouInfoListResult();
        tuanGouInfoListResult.setProductTypeList(resultList);

        return JSONObject.toJSONString(tuanGouInfoListResult);

    }



}