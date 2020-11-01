package com.youfan.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.youfan.input.AppInfo;
import com.youfan.input.PcInfo;
import com.youfan.input.ScanPageLog;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SendLogData {
    private static void postHttpMethod(String urlpath,String data){
        try {
            URL url = new URL(urlpath);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.setUseCaches(true);
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setConnectTimeout(1000 * 5);
            urlConnection.connect();
            OutputStream outputStream = urlConnection.getOutputStream();
            outputStream.write(data.getBytes("utf-8"));
            outputStream.flush();
            outputStream.close();
            InputStream inputStream = urlConnection.getInputStream();
            int httpCode = urlConnection.getResponseCode();
            byte[] inputdata = new byte[1024];
            StringBuffer stringBuffer = new StringBuffer();
            while(inputStream.read(inputdata,0,1024) != -1){
                stringBuffer.append(new String (inputdata));
            }
            System.out.println(httpCode);
            System.out.println(stringBuffer.toString());
            inputStream.close();
        } catch (Exception e) {

        }

    }

    public static void main(String[] args) {
        AppInfo appInfo = new AppInfo();
        String appinfoString = JSONObject.toJSONString(appInfo, SerializerFeature.WriteMapNullValue);


        String deviceId = appInfo.getDeviceId();
        String openTime = appInfo.getOpenTime();

        try {
        PcInfo pcInfo = new PcInfo();
        pcInfo.setDeviceId("24564545645");
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
            Date time = dateFormat.parse("2019101106");
            String timeString = time.getTime()+"";
            pcInfo.setOpenTime(timeString);
            ScanPageLog scanPageLog = new ScanPageLog();
            scanPageLog.setDeviceType("1");
            time = dateFormat.parse("2019101107");
            scanPageLog.setVisitTime(time.getTime()+"");
            scanPageLog.setDeviceComomInfo(pcInfo);
            String scanPageLogString = JSONObject.toJSONString(scanPageLog, SerializerFeature.WriteMapNullValue);
            System.out.println(scanPageLogString);
                    postHttpMethod("http://127.0.0.1:9081/dataCollect",scanPageLogString);
        } catch (ParseException e) {
            e.printStackTrace();
        }




    }
}
