package com.youfan.utils;

import com.alibaba.fastjson.JSONObject;
import com.youfan.yewu.Product;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2020/2/28.
 */
public class BinlogUtils {
    public static Map<String,String> tableEntityMap = new HashMap<String,String>();

    static {
        tableEntityMap.put("product","com.youfan.yewu.Product");
        tableEntityMap.put("orderinfo","com.youfan.yewu.OrderInfo");
        tableEntityMap.put("producttype","com.youfan.yewu.ProductType");
        tableEntityMap.put("miaoshainfo","com.youfan.yewu.MiaoshaInfo");
        tableEntityMap.put("tuangouinfo","com.youfan.yewu.TuangouInfo");
        tableEntityMap.put("huodonginfo","com.youfan.yewu.ZhidingHuoDongInfo");
    }

    public static void transferAndInsert(String tableName,String data) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(data);
        String className = tableEntityMap.get(tableName);
            Class classez = Class.forName(className);
            Field[] fields = classez.getDeclaredFields();
            String id = jsonObject.getString("id");
            Map<String,String> datamap = new HashMap<String,String>();
            for(Field field :fields){
                String fieldName = field.getName();
                String valueString = jsonObject.getString(fieldName);
                if(StringUtils.isNotBlank(valueString)){
                    System.out.println(fieldName);
                    System.out.println(valueString);
                    datamap.put(fieldName,valueString);
                }
            }
           HbaseUtils2.put(tableName,id,"info",datamap);
    }

    public static void main(String[] args) {
        Product product = new Product();
        product.setId(1l);
        product.setProductName("miaojin");
        String productjsonString = JSONObject.toJSONString(product);
        try {
           transferAndInsert("product",productjsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
