package com.example.mall.portal;

import com.example.mall.portal.entity.OmsOrderPortalEntity;
import com.example.mall.portal.service.OmsOrderPortalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
public class bingfa {

    private static CountDownLatch cdl = new CountDownLatch(200);

    @Autowired
    public OmsOrderPortalService orderPortalService;

    @Test
    void bing() throws InterruptedException {
        for (int i = 0; i < 20000; i++) {
            new Thread(() -> {
                try {
                    cdl.countDown();
                    cdl.await();
                    OmsOrderPortalEntity id = orderPortalService.getById(1);
                    System.out.println(id.toString());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(2000);
    }
}
