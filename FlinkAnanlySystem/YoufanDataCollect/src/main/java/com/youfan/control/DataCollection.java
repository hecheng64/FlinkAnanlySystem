package com.youfan.control;


import com.alibaba.fastjson.JSONObject;
import com.youfan.dataCollectUtils.UserStatus;
import com.youfan.input.AppInfo;
import com.youfan.input.PcInfo;
import com.youfan.input.ScanPageLog;
import com.youfan.input.XiaochengxuInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据收集服务
 */
@RestController
public class DataCollection {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @RequestMapping(method = RequestMethod.GET,value = "helloWorld")
    public String testHelloWorld(String name){
        return name;
    }


    @RequestMapping(method = RequestMethod.POST,value = "dataCollect")
    public void dataCollect(@RequestBody  String data){
        if(StringUtils.isNotBlank(data)){
            JSONObject jsonObject = JSONObject.parseObject(data);
            String deviceType = jsonObject.getString("deviceType");
            ScanPageLog scanPageLog = JSONObject.parseObject(data,ScanPageLog.class);
            String deviceComomInfo = jsonObject.getString("deviceComomInfo");
            //0、app端 1、pc端 2、小程序端
            if("0".equals(deviceType)){
                System.out.println("进入app端");
                AppInfo appinfo = JSONObject.parseObject(deviceComomInfo, AppInfo.class);
                UserStatus.filterNewStatus(appinfo);
                UserStatus.filterActiveStatus(appinfo);
                scanPageLog.setDeviceComomInfo(appinfo);
            }else if("1".equals(deviceType)){
                System.out.println("进入pc端");
                PcInfo pcInfo = JSONObject.parseObject(deviceComomInfo, PcInfo.class);
                UserStatus.filterNewStatus(pcInfo);
                UserStatus.filterActiveStatus(pcInfo);
                scanPageLog.setDeviceComomInfo(pcInfo);
            }else if("2".equals(deviceType)){
                System.out.println("进入小程序端");
                XiaochengxuInfo xiaochengxuInfo = JSONObject.parseObject(deviceComomInfo, XiaochengxuInfo.class);
                UserStatus.filterNewStatus(xiaochengxuInfo);
                UserStatus.filterActiveStatus(xiaochengxuInfo);
                scanPageLog.setDeviceComomInfo(xiaochengxuInfo);
            }
            String scanPageLogString = JSONObject.toJSONString(scanPageLog);
            System.out.println(scanPageLogString);
            kafkaTemplate.send("datainfo", scanPageLogString);
        }


    }


}
