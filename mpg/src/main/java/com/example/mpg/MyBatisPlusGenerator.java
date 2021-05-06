package com.example.mpg;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;


//@RunWith(SpringRunner.class)
public class MyBatisPlusGenerator {

    public static void main(String[] args) {

        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/ui/src/main/java");
        globalConfig.setAuthor("D_Richard");



        //去掉Serive的I
        globalConfig.setControllerName("%sPortalController");
        globalConfig.setMapperName("%sPortalMapper");
        globalConfig.setEntityName("%sPortalEntity");
        globalConfig.setServiceName("%sPortalService");
        globalConfig.setServiceImplName("%sPortalServiceImpl");
        globalConfig.setXmlName("%sPortalMapper");
        globalConfig.setOpen(false);
        mpg.setGlobalConfig(globalConfig);

        //数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://rm-wz9g2sy23x37m1d88oo.mysql.rds.aliyuncs.com:3306/malltt");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("richard");
        dataSourceConfig.setPassword("Wjmm123@");
        mpg.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName("portal");
        pc.setParent("com.example.mall.portal");
        pc.setService("service");
        mpg.setPackageInfo(pc);
        

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();

//        String[] needInclude={"sys_role","sys_admin","sys_menu","sys_resource","sys_role_resource_relation","sys_admin_role_relation","sys_role_menu_relation"};
//        String[] needInclude={"pms_brand","pms_product","pms_product_category"};
//        String[] needInclude={"oms_order","oms_order_item","oms_cart"};
        String[] needInclude={"miaosha_order","miaosha_goods"};
//        String[] needInclude={"pms_product"};
        strategy.setInclude(needInclude);     //※要映射的表明   // 数据库表映射到实体的命名策略,驼峰命名法


        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);

//        strategy.setTablePrefix("sys_");  //可以去掉表明前缀
        strategy.setControllerMappingHyphenStyle(true);

        mpg.setStrategy(strategy);
//        自动填充配置
        mpg.execute();

    }

}
