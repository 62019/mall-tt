package com.example.mall.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
@MapperScan(
        {
                "com.example.mall.admin.mapper",
        }

)
public class AdminApplication  implements CommandLineRunner {


    @Autowired
    private ApplicationContext appContext;

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
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
