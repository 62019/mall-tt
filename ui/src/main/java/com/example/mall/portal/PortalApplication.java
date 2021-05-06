package com.example.mall.portal;

import com.example.mall.admin.AdminApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

//加上这个冲突   这个冲突 怎么办 和上面那个shuju
//@SpringBootApplication(excludeName=("com.example.mall.admin.config.AdminSecurityConfig"),scanBasePackages = "com.example.mall")

@SpringBootApplication
@MapperScan(
        {
                "com.example.mall.portal.mapper"
        }
)

public class PortalApplication implements CommandLineRunner {
//不卡了

    @Autowired
    private ApplicationContext appContext;

    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        String[] beans = appContext.getBeanDefinitionNames();
        Arrays.sort(beans);
        for (String bean : beans) {
            System.out.println(bean);
        }

    }
}