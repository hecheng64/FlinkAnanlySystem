package com.youfan.dataCollectUtils;


import com.youfan.input.AppInfo;
import com.youfan.input.DeviceComomInfo;
import com.youfan.input.PcInfo;
import com.youfan.input.XiaochengxuInfo;
import com.youfan.utils.DateUtils;
import com.youfan.utils.HbaseUtils2;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Administrator on 2020/2/18.
 */
public class UserStatus {

    /**
     * 过滤是否是新增用户状态
     * create devicecomominfoapp,info
     * create devicecomominfopc,info
     * create devicecomominfoxiaochengxu,info
     *deviceComomInfo 设备信息
     * @return
     */
    public static void filterNewStatus(DeviceComomInfo deviceComomInfo){
        if(deviceComomInfo instanceof AppInfo){
            AppInfo appInfo = (AppInfo)deviceComomInfo;
            String deviceId = appInfo.getDeviceId();
            String openTime = appInfo.getOpenTime();
            try {
                String result = HbaseUtils2.getdata("devicecomominfoapp",deviceId,"info","uniqueId");
                if(StringUtils.isBlank(result)){
                    String result2 = HbaseUtils2.getdata("devicecomominfopc",deviceId,"info","uniqueId");
                    if(StringUtils.isBlank(result2)){
                        appInfo.setNew(true);
                        HbaseUtils2.putdata("devicecomominfoapp",deviceId,"info","uniqueId",deviceId);
                    }
                }
                deviceComomInfo = appInfo;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (deviceComomInfo instanceof PcInfo){
            PcInfo pcInfo = (PcInfo)deviceComomInfo;
            String macAdress = pcInfo.getMacAdress();
            String deviceId = pcInfo.getDeviceId();
            if(StringUtils.isNotBlank(macAdress)){
                try {
                    String result = HbaseUtils2.getdata("devicecomominfopc",macAdress,"info","uniqueId");
                    if(StringUtils.isBlank(result)){
                        HbaseUtils2.putdata("devicecomominfopc",macAdress,"info","uniqueId",macAdress);
                        pcInfo.setNew(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (StringUtils.isNotBlank(deviceId)){
                try {
                    String result = HbaseUtils2.getdata("devicecomominfopc",deviceId,"info","uniqueId");
                    if(StringUtils.isBlank(result)){
                        String result2 = HbaseUtils2.getdata("devicecomominfoapp",deviceId,"info","uniqueId");
                        if(StringUtils.isBlank(result2)){
                            HbaseUtils2.putdata("devicecomominfopc",deviceId,"info","uniqueId",deviceId);
                            pcInfo.setNew(true);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            deviceComomInfo = pcInfo;
        }else if (deviceComomInfo instanceof XiaochengxuInfo){
            XiaochengxuInfo xiaochengxuInfo = (XiaochengxuInfo)deviceComomInfo;
            String weixinAccount = xiaochengxuInfo.getWeixinAccount();
            try {
                String result = HbaseUtils2.getdata("devicecomominfoxiaochengxu",weixinAccount,"info","uniqueId");
                if(StringUtils.isBlank(result)){
                    xiaochengxuInfo.setNew(true);
                    HbaseUtils2.putdata("devicecomominfoxiaochengxu",weixinAccount,"info","uniqueId",weixinAccount);
                }
                deviceComomInfo = xiaochengxuInfo;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void filterActiveStatus(DeviceComomInfo deviceComomInfo){
        if(deviceComomInfo instanceof AppInfo){
            AppInfo appInfo = (AppInfo)deviceComomInfo;
            String deviceId = appInfo.getDeviceId();
            String openTime = appInfo.getOpenTime();
            Long openTimeMillons = Long.valueOf(openTime);
            try {
                String result = HbaseUtils2.getdata("devicecomominfoapp",deviceId,"info","lastvisittime");
                String lastVisitTime = "";
                if(StringUtils.isBlank(result)){
                    String result2 = HbaseUtils2.getdata("devicecomominfopc",deviceId,"info","lastvisittime");
                    if(StringUtils.isBlank(result2)){
                        appInfo.setHourActive(true);
                        appInfo.setDayActive(true);
                        appInfo.setMonthActive(true);
                        appInfo.setWeekActive(true);
                        HbaseUtils2.putdata("devicecomominfoapp",deviceId,"info","lastvisittime",openTime);
                    }else {
                        lastVisitTime = result2;
                    }
                }else{
                    lastVisitTime = result;
                }

                if(StringUtils.isNotBlank(lastVisitTime)){
                    long lastVisitTimeMillons = Long.valueOf(lastVisitTime);
                    //小时
                    long hourStart = DateUtils.getCurrentHourStart(openTimeMillons);
                    if(lastVisitTimeMillons < hourStart){
                        appInfo.setHourActive(true);
                    }

                    //天
                    long dayStart = DateUtils.getCurrentDayStart(openTimeMillons);
                    if(lastVisitTimeMillons < dayStart){
                        appInfo.setDayActive(true);
                    }

                    //周
                    long weekStart = DateUtils.getCurrentWeekStart(openTimeMillons);
                    if(lastVisitTimeMillons < weekStart){
                        appInfo.setDayActive(true);
                    }

                    //月
                    long monthStart = DateUtils.getCurrentMonthStart(openTimeMillons);
                    if(lastVisitTimeMillons < monthStart){
                        appInfo.setMonthActive(true);
                    }

                    //分钟
                    long fiveMinuteIn = DateUtils.getCurrentFiveMinuteInterStart(openTimeMillons);
                    if(lastVisitTimeMillons < fiveMinuteIn){
                        appInfo.setFiveMinuteActive(true);
                    }
                }
                HbaseUtils2.putdata("devicecomominfoapp",deviceId,"info","lastvisittime",openTime);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (deviceComomInfo instanceof PcInfo){
            PcInfo pcInfo = (PcInfo)deviceComomInfo;
            String macAdress = pcInfo.getMacAdress();
            String deviceId = pcInfo.getDeviceId();
            String openTime = pcInfo.getOpenTime();
            Long openTimeMillons = Long.valueOf(openTime);
            String lastVisitTime = "";
            try {
                    if(StringUtils.isNotBlank(macAdress)) {
                        String result = HbaseUtils2.getdata("devicecomominfopc", macAdress, "info", "lastvisittime");
                        if (StringUtils.isNotBlank(result)) {
                            lastVisitTime = result;
                        }else{
                            pcInfo.setHourActive(true);
                            pcInfo.setDayActive(true);
                            pcInfo.setMonthActive(true);
                            pcInfo.setWeekActive(true);
                            HbaseUtils2.putdata("devicecomominfopc", macAdress, "info", "lastvisittime",openTime);
                        }
                        HbaseUtils2.putdata("devicecomominfopc",macAdress,"info","lastvisittime",openTime);
                    }else if(StringUtils.isNotBlank(deviceId)) {
                        String result = HbaseUtils2.getdata("devicecomominfopc", deviceId, "info", "lastvisittime");
                        if (StringUtils.isBlank(result)) {
                            String result2 = HbaseUtils2.getdata("devicecomominfoapp", deviceId, "info", "lastvisittime");
                            if (StringUtils.isBlank(result2)) {
                                pcInfo.setHourActive(true);
                                pcInfo.setDayActive(true);
                                pcInfo.setMonthActive(true);
                                pcInfo.setWeekActive(true);
                                HbaseUtils2.putdata("devicecomominfopc", deviceId, "info", "lastvisittime", openTime);
                            } else {
                                lastVisitTime = result2;
                            }
                        }else{
                            lastVisitTime = result;
                        }
                        HbaseUtils2.putdata("devicecomominfopc",deviceId,"info","lastvisittime",openTime);
                    }
                if(StringUtils.isNotBlank(lastVisitTime)){
                    long lastVisitTimeMillons = Long.valueOf(lastVisitTime);
                    //小时
                    long hourStart = DateUtils.getCurrentHourStart(openTimeMillons);
                    if(lastVisitTimeMillons < hourStart){
                        pcInfo.setHourActive(true);
                    }

                    //天
                    long dayStart = DateUtils.getCurrentDayStart(openTimeMillons);
                    if(lastVisitTimeMillons < dayStart){
                        pcInfo.setDayActive(true);
                    }

                    //周
                    long weekStart = DateUtils.getCurrentWeekStart(openTimeMillons);
                    if(lastVisitTimeMillons < weekStart){
                        pcInfo.setDayActive(true);
                    }

                    //月
                    long monthStart = DateUtils.getCurrentMonthStart(openTimeMillons);
                    if(lastVisitTimeMillons < monthStart){
                        pcInfo.setMonthActive(true);
                    }

                    //分钟
                    long fiveMinuteIn = DateUtils.getCurrentFiveMinuteInterStart(openTimeMillons);
                    if(lastVisitTimeMillons < fiveMinuteIn){
                        pcInfo.setFiveMinuteActive(true);
                    }
                }


            } catch (Exception e) {
                    e.printStackTrace();
            }

        }else if (deviceComomInfo instanceof XiaochengxuInfo){
            XiaochengxuInfo xiaochengxuInfo = (XiaochengxuInfo)deviceComomInfo;
            String weixinAccount = xiaochengxuInfo.getWeixinAccount();
            String openTime = xiaochengxuInfo.getOpenTime();
            Long openTimeMillons = Long.valueOf(openTime);
            String lastVisitTime = "";
            try {
                String result = HbaseUtils2.getdata("devicecomominfoxiaochengxu", weixinAccount, "info", "lastvisittime");
                if(StringUtils.isBlank(result)){
                    xiaochengxuInfo.setHourActive(true);
                    xiaochengxuInfo.setDayActive(true);
                    xiaochengxuInfo.setMonthActive(true);
                    xiaochengxuInfo.setWeekActive(true);
                    HbaseUtils2.putdata("devicecomominfoxiaochengxu",weixinAccount, "info", "lastvisittime", openTime);
                }else{
                    lastVisitTime = result;
                }

                if(StringUtils.isNotBlank(lastVisitTime)){
                    long lastVisitTimeMillons = Long.valueOf(lastVisitTime);
                    //小时
                    long hourStart = DateUtils.getCurrentHourStart(openTimeMillons);
                    if(lastVisitTimeMillons < hourStart){
                        xiaochengxuInfo.setHourActive(true);
                    }

                    //天
                    long dayStart = DateUtils.getCurrentDayStart(openTimeMillons);
                    if(lastVisitTimeMillons < dayStart){
                        xiaochengxuInfo.setDayActive(true);
                    }

                    //周
                    long weekStart = DateUtils.getCurrentWeekStart(openTimeMillons);
                    if(lastVisitTimeMillons < weekStart){
                        xiaochengxuInfo.setDayActive(true);
                    }

                    //月
                    long monthStart = DateUtils.getCurrentMonthStart(openTimeMillons);
                    if(lastVisitTimeMillons < monthStart){
                        xiaochengxuInfo.setMonthActive(true);
                    }

                    //分钟

                    long fiveMinuteIn = DateUtils.getCurrentFiveMinuteInterStart(openTimeMillons);
                    if(lastVisitTimeMillons < fiveMinuteIn){
                        xiaochengxuInfo.setFiveMinuteActive(true);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
