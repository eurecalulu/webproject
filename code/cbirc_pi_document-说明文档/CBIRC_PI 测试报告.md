# 测试报告

[TOC]

## 1 测试概述

### 1.1 编写目的

本报告详细描述了对Cbirc系统的测试套件，达到测试驱动开发的目的，同时实现和测试人员及用户的沟通。

本报告面向开发人员、测试人员及最终用户而编写，是了解系统的导航。

### 1.2 参考资料

* IEEE标准 
* 《软件工程与计算III》 
* （迭代一）课程场景主题.：商业银行操作风险管理——案例数据库构建及功能开发.docx
* （迭代一）2021实践课程简介.pptx

## 2 测试执行情况

### 2.1 测试类型

* 功能测试
	* 采用黑盒测试，使用python+selenium进行自动化测试
* 单元测试
	* 分为service测试和dao测试，白盒测试
	* 使用Junit进行测试

### 2.2 测试环境

| 资源         | 配置                                      |
| ------------ | ----------------------------------------- |
| SpringBoot   | 本地                                      |
| Vue          | 本地                                      |
| MySQL 8.0.22 | 阿里云服务器 CentOS 7.3-1核-2G内存-40GSSD |

### 2.3 测试人员

全体

## 3 测试结果分析

### 3.1 测试覆盖率

![image-20211102153536409](https://cyzblog.oss-cn-beijing.aliyuncs.com/img/image-20211102153536409.png)

### 3.3 测试用例

#### 3.2.1 dao层测试

![image-20211102153909718](https://cyzblog.oss-cn-beijing.aliyuncs.com/img/image-20211102153909718.png)

#### 3.2.2 service层测试

![image-20211102153943397](https://cyzblog.oss-cn-beijing.aliyuncs.com/img/image-20211102153943397.png)

#### 3.2.3 功能测试

共进行了注册、登录、新建、搜索、废除与查看细节的功能测试，结果如下：

![image-20211104100311073](https://cbirc-pi.oss-cn-shanghai.aliyuncs.com/img/image-20211104100311073.png)