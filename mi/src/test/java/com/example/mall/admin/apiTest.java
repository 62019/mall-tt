//package com.example.mall.admin;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class apiTest {
//    public static void main(String[] args) {
//        String host = "https://ali-weather.showapi.com";
//        String path = "/area-to-weather";
//        String method = "GET";
//        String appcode = "你自己的AppCode";
//        Map<String, String> headers = new HashMap<String, String>();
//        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//        headers.put("Authorization", "APPCODE " + appcode);
//        Map<String, String> querys = new HashMap<String, String>();
//        querys.put("area", "丽江");
//        querys.put("areaCode", "areaCode");
//        querys.put("areaid", "101291401");
//        querys.put("need3HourForcast", "0");
//        querys.put("needAlarm", "0");
//        querys.put("needHourData", "needHourData");
//        querys.put("needIndex", "0");
//        querys.put("needMoreDay", "0");
//
//
//        try {
//            /**
//             * 重要提示如下:
//             * HttpUtils请从
//             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
//             * 下载
//             *
//             * 相应的依赖请参照
//             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
//             */
//            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
//            System.out.println(response.toString());
//            //获取response的body
//            //System.out.println(EntityUtils.toString(response.getEntity()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}