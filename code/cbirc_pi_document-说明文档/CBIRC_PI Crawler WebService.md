# Crawler WebService

## 1 接口说明

只提供一种访问数据的方式

+ 调用 getDocData(), 获取完整的爬虫数据

数据格式

```JSON
{"interpretationList":[{"abstactContent":"为进一步增强我国金融体系的稳定性和健康性，保障我国全球系统重要性银行具有充足的损失吸收和资本重组能力，防范化解系统性金融风险，经国务院同意，人民银行会同银保监会、财政部联合发布《全球系统重要性银行总损失吸收能力管理办法》（以下简称《办法》），有关部门负责","docId":1015207,"text":"XXX","time":"2021-10-29","title":"中国人民银行 银保监会 财政部有关部门负责人就《全球系统重要性银行总损失吸收能力管理办法》答记者问"},{...},...,{...}]}
```

## 2 Client 创建方式

+  安装 Apache CXF 3.2.14
+ 在IDEA的settings->Tools中配置CXF位置
+ 在IDEA中Tool -> Webservice -> Generate java code from wsdl

![clientcode](https://cdn.jsdelivr.net/gh/Coolingsky167/Figure/cover/20211102182400.jpg)

注意：

1. 由于爬虫爬取过程需要几分钟，所以要设置client侧的连接时长
2. 由于xml不支持一些特殊符号，在server端用utf-8进行encode了，在client侧需要decode