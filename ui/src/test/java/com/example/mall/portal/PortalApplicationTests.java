package com.example.mall.portal;

import com.example.mall.portal.entity.OmsOrderPortalEntity;
import com.example.mall.portal.service.OmsOrderPortalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

//@SpringBootTest
class PortalApplicationTests {
    // 格式化的时间字符串
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    // 获取当前时间年月日时分秒毫秒字符串
    private static String getNowDateStr() {
        return sdf.format(new Date());
    }

    @Test
    void contextLoads() {
        String nowDateStr = getNowDateStr();
    }




}
