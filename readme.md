# MALL-TT
## 简介
*前台商城系统及后台管理系统*
<!-- 只写功能 不介绍 -->

## 目录介绍
**common** 工具类及通用代码

**mpg** MybatisPlus代码生成

**security** SpringSecurity公用模块

**mi** 后台商城管理接口

**ui** 前台商城接口
___

**前台商城系统(小程序):**
首页门户、商品分类、商品展示、购物车、订单流程、

会员中心

秒杀系统

*(未开始)*
*商品推荐、商品搜索*

**后台管理系统:**
商品、订单、会员CRUD模块、

用户权限模块

*(未开始)*
*文件上传*

___

## 核心模块介绍
### 前台
**秒杀模块**

通过rabbitmq和redis的场景优化、经过jmeter压力测试

**会员中心模块**

基于SpringSecurity和Token验证

### 后台
**文件上传模块**

基于MinIO对象存储服务实现的文件服务功能

**用户权限模块**

整合SpringSecurity和JWT实现认证和授权功能