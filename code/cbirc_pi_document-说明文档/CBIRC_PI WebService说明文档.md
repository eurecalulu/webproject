# WebService说明文档

[TOC]



## 1 接口说明

> 本项目对外封装并以 WebService 形式对外提高数据访问接口

### 1.1 接口概述

WebService 提供以下三种进行数据访问的方式

* 根据id获取政策解读
* 筛选并获取政策解读列表 
* 筛选并获取政策解读数量

返回结果的字段说明如下：

```json
{
    "id": 462, // 内部id 
    "docTitle": "中国银保监会关于服务煤电行业正常生产和商品市场有序流通保障经济平稳运行有关事项的通知", // 政策标题
    "docIdentifier": "银保监发[2021]42号", // 政策文号
    "docDepart": "办公厅", // 发文部门
    "interpretDepart": null, // 解读部门
    "interpretTitle": "中国银保监会印发《关于服务煤电行业正常生产和商品市场有序流通保障经济平稳运行有关事项的通知》", // 解读标题
    "interpretBody": "近日，为维护煤电行业和商品市场正常秩序，助力做好保供稳价工作，严防利用银行保险资金囤积居奇、哄抬价格，保障经济社会高质量发展，中国银保监会印发《关于服务煤电行业正常生产和商品市场有序流通 保障经济平稳运行有关事项的通知》（以下简称《通知》）。\n《通知》指出，各派出机构、机关各部门要从讲政治的高度认识做好当前保供稳价工作的重要意义，坚决贯彻落实党中央、国务院决策部署，牢固树立以人民为中心的发展思想，贯彻新发展理念，坚持系统观念和底线思维，守土有责、守土尽责，指导银行保险机构千方百计加大对保供稳价支持力度，保障群众基本生活和经济平稳运行。\n《通知》强调，要保障煤电、煤炭、钢铁、有色金属等生产企业合理融资需求。要督促银行保险机构全力做好今冬明春能源电力保供金融服务工作，满足能源电力供应合理资金需求，积极支持煤炭主产区和重点煤炭企业增加电煤供应，确保人民群众温暖过冬。要严防银行保险资金影响商品市场正常秩序。严禁利用银行保险资金违规参与煤炭、钢铁、有色金属等大宗商品投机炒作，严禁挪用各种贷款包括经营贷、消费贷投机炒作茅台酒、名贵普洱茶等高端消费品，严禁银行保险资金违规流入股市、债市、期市，严禁对符合支持条件的煤电、煤炭等企业和项目违规抽贷、断贷。要积极推动消费信贷规范健康发展。不得诱导金融消费者盲目借贷、过度超前消费，不得通过诱导信用卡“过度分期”等方式侵害金融消费者权益，不得提供显著高于市场利率的消费信贷产品，不得开发违反公序良俗、助长社会陋习和不良风气的消费信贷产品。要督促银行机构切实加强和改进信贷管理，主动调整完善信贷政策，防止抬高融资准入门槛。\n下一步，银保监会将压实银行保险机构主体责任，推动做好自查自纠工作，及时采取有针对性的整改措施，构建常态化监测排查机制。银保监会将保持监管高压态势，严查银行保险资金被挪用于投机炒作、囤积居奇、哄抬价格等违法违规行为。对机构自查不认真、没有主动报告和性质恶劣的行为依法采取监管措施，及时启动行政处罚程序，依法依规严肃问责。\n附：中国银保监会关于服务煤电行业正常生产和商品市场有序流通保障经济平稳运行有关事项的通知\nhttp://www.cbirc.gov.cn/cn/view/pages/govermentDetail.html?docId=1011374&itemId=861&generaltype=1\n", // 解读正文
    "interpretAbstract": "近日，为维护煤电行业和商品市场正常秩序，助力做好保供稳价工作，严防利用银行保险资金囤积居奇、哄抬价格，保障经济社会高质量发展，中国银保监会印发《关于服务煤电行业正常生产和商品市场有序流通 保障经济平稳运行有关事项的通知》（以下简称《通知》）。", // 解读摘要
    "time": "2021-10-05", // 录入时间
    "user": "办公厅", // 录入人
    "status": "PUBLIC" // 政策解读状态
}
```

### 1.2 参数及返回说明

#### 1.2.1 根据id获取政策解读

```java
PolicyInterpretationVO getById(@WebParam(name = "id") int id);
```

* `id`：内部id

#### 1.2.2 筛选并获取政策解读列表 

```java
List<PolicyInterpretationVO> list(@WebParam(name = "withBody") boolean withBody,
                       @WebParam(name = "docTitle") String docTitle,
                       @WebParam(name = "docIdentifier") String docId,
                       @WebParam(name = "docDepart") String docDepart,
                       @WebParam(name = "startTime") String startTime,
                       @WebParam(name = "endTime") String endTime,
                       @WebParam(name = "pageNo") int pageNo,
                       @WebParam(name = "pageSize") int pageSize);
```

* `withBody`：若为true则返回携带解读正文
* `docTitle`：政策文件标题，若不筛选则设为空串
* `docId`：政策文号，如“银保监发[2021]42号”，若不筛选则设为空串
* `docDepart`：发文部门，若不筛选则设为空串
* `startTime`、`endTime`：录入时间区间，，若不筛选则均设为空串
* `pageNo`：分页页号，从1开始
* `pageSize`：分页大小

#### 1.2.3 筛选并获取政策解读数量

```java
int count(@WebParam(name = "docTitle") String docTitle,
       @WebParam(name = "docIdentifier") String docId,
       @WebParam(name = "docDepart") String docDepart,
       @WebParam(name = "startTime") String startTime,
       @WebParam(name = "endTime") String endTime);
```

* `docTitle`：政策文件标题，若不筛选则设为空串
* `docId`：政策文号，如“银保监发[2021]42号”，若不筛选则设为空串
* `docDepart`：发文部门，若不筛选则设为空串
* `startTime`、`endTime`：录入时间区间，，若不筛选则均设为空串

## 2 使用说明

> 以下介绍 Spring Boot 整合 WebService 的方法，参考https://www.cnblogs.com/wlv1314/p/12157568.html

### 2.1 客户端创建

在IDEA中选择 WebServices -> Generate Java Code From Wsdl

![image-20211102143115563](https://cyzblog.oss-cn-beijing.aliyuncs.com/img/image-20211102143115563.png)

输入url以文档中最终给出的为准，填写 package prefix 等信息

![image-20211102143251812](https://cyzblog.oss-cn-beijing.aliyuncs.com/img/image-20211102143251812.png)

点击OK，IDEA会自动生成代理类

![image-20211102143321761](https://cyzblog.oss-cn-beijing.aliyuncs.com/img/image-20211102143321761.png)

### 2.2 调用Demo

```java
public class WebServiceTest {

    PolicyInterpretationWebServiceImplService policyInterpretationServiceImplService=new PolicyInterpretationWebServiceImplService();
    PolicyInterpretationService webService=policyInterpretationServiceImplService
            .getPolicyInterpretationWebServiceImplPort();


    @Test
    public void testGetById(){
        PolicyInterpretationVO policyInterpretationVO = webService.getById(446);
        assert policyInterpretationVO!=null;
        assert policyInterpretationVO.getId()==446;
    }

    @Test
    public void testGet(){
        assert webService.count("","","","","")>0;
        assert webService.list(false,"","","","","",1,10).size()>0;
    }
}
```

