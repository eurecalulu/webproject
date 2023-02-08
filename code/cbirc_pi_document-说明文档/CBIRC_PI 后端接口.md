---
title: 后端接口 v1.0.0
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
highlight_theme: darkula
headingLevel: 2

---

# 后端接口

> v1.0.0

# 政策解读接口

## POST 更新政策正文

POST /policyInterpretation/updateDocText

> Body 请求参数

```json
{
  "type": "object",
  "properties": {
    "docText": {
      "type": "string"
    },
    "id": {
      "type": "integer",
      "format": "int32"
    }
  },
  "title": "UpdatePolicyDocTextVO"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[UpdatePolicyDocTextVO](#schemaupdatepolicydoctextvo)|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ResponseVO](#schemaresponsevo)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

## POST 新增政策解读

POST /policyInterpretation/addInterpretation

> Body 请求参数

```json
{
  "type": "object",
  "properties": {
    "docDepart": {
      "type": "string"
    },
    "docIdentifier": {
      "type": "string"
    },
    "docTitle": {
      "type": "string"
    },
    "id": {
      "type": "integer",
      "format": "int32"
    },
    "interpretAbstract": {
      "type": "string"
    },
    "interpretBody": {
      "type": "string"
    },
    "interpretDepart": {
      "type": "string"
    },
    "interpretTitle": {
      "type": "string"
    },
    "status": {
      "type": "string"
    },
    "time": {
      "type": "string"
    }
  },
  "title": "AddOrUpdatePolicyInterpretationVO"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[AddOrUpdatePolicyInterpretationVO](#schemaaddorupdatepolicyinterpretationvo)|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ResponseVO](#schemaresponsevo)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

## POST 更新政策解读条目状态

POST /policyInterpretation/updateInterpretationStatus

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|query|string|true|none|
|status|query|string|true|PUBLIC/DRAFT/ABOLISH|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 筛选政策解读列表

POST /policyInterpretation/list

> Body 请求参数

```json
{
  "type": "object",
  "properties": {
    "currIndex": {
      "type": "integer",
      "format": "int32"
    },
    "docDepart": {
      "type": "string"
    },
    "docIdentifier": {
      "type": "string"
    },
    "docTitle": {
      "type": "string"
    },
    "endTime": {
      "type": "string"
    },
    "pageSize": {
      "type": "integer",
      "format": "int32"
    },
    "startTime": {
      "type": "string"
    },
    "withBody": {
      "type": "boolean"
    }
  },
  "title": "ListPolicyInterpretFilter"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[ListPolicyInterpretFilter](#schemalistpolicyinterpretfilter)|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[PageList%C2%ABPolicyInterpretationVO%C2%BB](#schemapagelist%c2%abpolicyinterpretationvo%c2%bb)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

## POST 更新政策解读

POST /policyInterpretation/updateInterpretation

需要带cbirc-token

> Body 请求参数

```json
{
  "type": "object",
  "properties": {
    "docDepart": {
      "type": "string"
    },
    "docIdentifier": {
      "type": "string"
    },
    "docTitle": {
      "type": "string"
    },
    "id": {
      "type": "integer",
      "format": "int32"
    },
    "interpretAbstract": {
      "type": "string"
    },
    "interpretBody": {
      "type": "string"
    },
    "interpretDepart": {
      "type": "string"
    },
    "interpretTitle": {
      "type": "string"
    },
    "status": {
      "type": "string"
    },
    "time": {
      "type": "string"
    }
  },
  "title": "AddOrUpdatePolicyInterpretationVO"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|cbirc-token|header|string|true|none|
|body|body|[AddOrUpdatePolicyInterpretationVO](#schemaaddorupdatepolicyinterpretationvo)|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ResponseVO](#schemaresponsevo)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

## GET 获取docDepart的统计信息

GET /policyInterpretation/getDocDepartCount

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 根据id获取政策解读

GET /policyInterpretation/getById

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|query|string|false|id|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[PolicyInterpretationVO](#schemapolicyinterpretationvo)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

## POST 删除政策解读

POST /policyInterpretation/deleteById

> Body 请求参数

```json
{
  "type": "integer",
  "format": "int32"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|query|string|true|none|
|body|body|[deleteByIdUsingPOSTId](#schemadeletebyidusingpostid)|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ResponseVO](#schemaresponsevo)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

# 用户模块接口

## GET 获取用户数量

GET /user/count

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|integer|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

## POST 注册

POST /user/register

> Body 请求参数

```json
{
  "type": "object",
  "properties": {
    "name": {
      "type": "string"
    },
    "password": {
      "type": "string"
    }
  },
  "title": "用户登录账户密码"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[%E7%94%A8%E6%88%B7%E7%99%BB%E5%BD%95%E8%B4%A6%E6%88%B7%E5%AF%86%E7%A0%81](#schema%e7%94%a8%e6%88%b7%e7%99%bb%e5%bd%95%e8%b4%a6%e6%88%b7%e5%af%86%e7%a0%81)|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ResponseVO](#schemaresponsevo)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

## POST 登录

POST /user/login

登录成功后将response header中的cbirc-token字段保存下来，以后请求均要在request header中带上。可用“查看当前用户信息”来测试。

> Body 请求参数

```json
{
  "type": "object",
  "properties": {
    "name": {
      "type": "string"
    },
    "password": {
      "type": "string"
    }
  },
  "title": "用户登录账户密码"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[%E7%94%A8%E6%88%B7%E7%99%BB%E5%BD%95%E8%B4%A6%E6%88%B7%E5%AF%86%E7%A0%81](#schema%e7%94%a8%e6%88%b7%e7%99%bb%e5%bd%95%e8%b4%a6%e6%88%b7%e5%af%86%e7%a0%81)|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ResponseVO](#schemaresponsevo)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

## GET 查看当前用户信息

GET /user/info

request header中带有效的cbirc-token才会返回正确的用户信息

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|cbirc-token|header|string|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|[%E7%94%A8%E6%88%B7%E8%AF%A6%E6%83%85VO](#schema%e7%94%a8%e6%88%b7%e8%af%a6%e6%83%85vo)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|错误·|[ResponseVO](#schemaresponsevo)|

### 返回数据结构

# 政策解读评论区接口

## DELETE 删除评论

DELETE /comment/delete

带cbirc-token

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|query|string|true|id|
|cbirc-token|header|string|true|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ResponseVO](#schemaresponsevo)|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|No Content|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|

### 返回数据结构

## GET 获取某个政策解读下的全部评论

GET /comment/listByPolicyInterpretationId

不用带token，返回的ref如果不为null说明回复（引用）了某条其他评论

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|query|string|true|政策解读id|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|*anonymous*|[[%E8%AF%84%E8%AE%BA%E6%9D%A1%E7%9B%AE](#schema%e8%af%84%e8%ae%ba%e6%9d%a1%e7%9b%ae)]|false|none|none|
|» 评论条目|[%E8%AF%84%E8%AE%BA%E6%9D%A1%E7%9B%AE](#schema%e8%af%84%e8%ae%ba%e6%9d%a1%e7%9b%ae)|false|none|none|
|»» comment|string|false|none|none|
|»» id|integer(int32)|false|none|none|
|»» piId|integer(int32)|false|none|none|
|»» ref|object|false|none|none|
|»» time|string|false|none|none|
|»» userInfo|object|false|none|none|

## POST 新增评论

POST /comment/add

需登录带token
refCommentId用于引用评论，如果不用设为0即可
如果成功，返回message为新增的评论id

> Body 请求参数

```json
{
  "type": "object",
  "properties": {
    "comment": {
      "type": "string"
    },
    "piId": {
      "type": "integer",
      "format": "int32"
    },
    "refCommentId": {
      "type": "integer",
      "format": "int32"
    }
  },
  "title": "AddCommentVO"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|cbirc-token|header|string|true|none|
|body|body|[AddCommentVO](#schemaaddcommentvo)|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ResponseVO](#schemaresponsevo)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

# 政策解读与具体法规条文关联接口

## POST 检索相关法规条款

POST /ir/retrieval

> Body 请求参数

```json
{
  "type": "object",
  "properties": {
    "piId": {
      "type": "integer",
      "format": "int32"
    },
    "text": {
      "type": "string"
    }
  },
  "title": "PolicyRetrievalVO"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[PolicyRetrievalVO](#schemapolicyretrievalvo)|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[PolicyMatchVO](#schemapolicymatchvo)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

# 词频统计接口

## GET 获取词频排名前n个的词频统计数据

GET /wordFrequency/getRankNWordFrequency

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|n|query|string|true|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 获取所有词频统计数据

GET /wordFrequency/getAllWordFrequency

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 更新词频统计数据

POST /wordFrequency/updateWordFrequency

这个接口调起来因为有一系列词库初始化的工作，非常慢，不建议前端直接在更新的时候调用。

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# 数据模型

<h2 id="tocS_PolicyMatchVO">PolicyMatchVO</h2>
<!-- backwards compatibility -->
<a id="schemapolicymatchvo"></a>
<a id="schema_PolicyMatchVO"></a>
<a id="tocSpolicymatchvo"></a>
<a id="tocspolicymatchvo"></a>

```json
{
  "type": "object",
  "properties": {
    "found": {
      "type": "boolean"
    },
    "policyDoc": {
      "type": "string"
    },
    "policyMatchItems": {
      "type": "array",
      "items": {
        "type": "string"
      }
    }
  },
  "title": "PolicyMatchVO"
}

```

PolicyMatchVO

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|found|boolean|false|none|none|
|policyDoc|string|false|none|none|
|policyMatchItems|[string]|false|none|none|

<h2 id="tocS_Tag">Tag</h2>
<!-- backwards compatibility -->
<a id="schematag"></a>
<a id="schema_Tag"></a>
<a id="tocStag"></a>
<a id="tocstag"></a>

```json
{
  "type": "object",
  "properties": {
    "id": {
      "type": "integer",
      "format": "int64",
      "minimum": 1,
      "description": "标签ID编号"
    },
    "name": {
      "type": "string",
      "description": "标签名称"
    }
  },
  "xml": {
    "name": "Tag"
  }
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|id|integer(int64)|false|none|标签ID编号|
|name|string|false|none|标签名称|

<h2 id="tocS_词频统计信息">词频统计信息</h2>
<!-- backwards compatibility -->
<a id="schema词频统计信息"></a>
<a id="schema_词频统计信息"></a>
<a id="tocS词频统计信息"></a>
<a id="tocs词频统计信息"></a>

```json
{
  "type": "object",
  "properties": {
    "frequency": {
      "type": "integer",
      "format": "int32"
    },
    "keyWord": {
      "type": "string"
    }
  },
  "title": "词频统计信息"
}

```

词频统计信息

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|frequency|integer(int32)|false|none|none|
|keyWord|string|false|none|none|

<h2 id="tocS_用户登录账户密码">用户登录账户密码</h2>
<!-- backwards compatibility -->
<a id="schema用户登录账户密码"></a>
<a id="schema_用户登录账户密码"></a>
<a id="tocS用户登录账户密码"></a>
<a id="tocs用户登录账户密码"></a>

```json
{
  "type": "object",
  "properties": {
    "name": {
      "type": "string"
    },
    "password": {
      "type": "string"
    }
  },
  "title": "用户登录账户密码"
}

```

用户登录账户密码

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|name|string|false|none|none|
|password|string|false|none|none|

<h2 id="tocS_Pet">Pet</h2>
<!-- backwards compatibility -->
<a id="schemapet"></a>
<a id="schema_Pet"></a>
<a id="tocSpet"></a>
<a id="tocspet"></a>

```json
{
  "required": [
    "name",
    "photoUrls",
    "id",
    "category",
    "tags",
    "status"
  ],
  "type": "object",
  "properties": {
    "id": {
      "type": "integer",
      "format": "int64",
      "minimum": 1,
      "maximum": 5000,
      "description": "宠物ID编号"
    },
    "category": {
      "type": "object",
      "properties": {
        "id": {
          "type": "integer",
          "format": "int64",
          "minimum": 1,
          "description": "分类ID编号"
        },
        "name": {
          "type": "string",
          "description": "分类名称"
        }
      },
      "xml": {
        "name": "Category"
      }
    },
    "name": {
      "type": "string",
      "example": "doggie",
      "description": "名称"
    },
    "photoUrls": {
      "type": "array",
      "xml": {
        "name": "photoUrl",
        "wrapped": true
      },
      "items": {
        "type": "string"
      },
      "description": "照片URL"
    },
    "tags": {
      "type": "array",
      "xml": {
        "name": "tag",
        "wrapped": true
      },
      "items": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64",
            "minimum": 1,
            "description": "标签ID编号"
          },
          "name": {
            "type": "string",
            "description": "标签名称"
          }
        },
        "xml": {
          "name": "Tag"
        }
      },
      "description": "标签"
    },
    "status": {
      "type": "string",
      "description": "宠物销售状态",
      "enum": [
        "available",
        "pending",
        "sold"
      ]
    }
  },
  "xml": {
    "name": "Pet"
  }
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|id|integer(int64)|true|none|宠物ID编号|
|category|[Category](#schemacategory)|true|none|none|
|name|string|true|none|名称|
|photoUrls|[string]|true|none|照片URL|
|tags|[[Tag](#schematag)]|true|none|标签|
|status|string|true|none|宠物销售状态|

#### 枚举值

|属性|值|
|---|---|
|status|available|
|status|pending|
|status|sold|

<h2 id="tocS_UpdatePolicyDocTextVO">UpdatePolicyDocTextVO</h2>
<!-- backwards compatibility -->
<a id="schemaupdatepolicydoctextvo"></a>
<a id="schema_UpdatePolicyDocTextVO"></a>
<a id="tocSupdatepolicydoctextvo"></a>
<a id="tocsupdatepolicydoctextvo"></a>

```json
{
  "type": "object",
  "properties": {
    "docText": {
      "type": "string"
    },
    "id": {
      "type": "integer",
      "format": "int32"
    }
  },
  "title": "UpdatePolicyDocTextVO"
}

```

UpdatePolicyDocTextVO

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|docText|string|false|none|none|
|id|integer(int32)|false|none|none|

<h2 id="tocS_PolicyInterpretationVO">PolicyInterpretationVO</h2>
<!-- backwards compatibility -->
<a id="schemapolicyinterpretationvo"></a>
<a id="schema_PolicyInterpretationVO"></a>
<a id="tocSpolicyinterpretationvo"></a>
<a id="tocspolicyinterpretationvo"></a>

```json
{
  "type": "object",
  "properties": {
    "docDepart": {
      "type": "string"
    },
    "docIdentifier": {
      "type": "string"
    },
    "docTitle": {
      "type": "string"
    },
    "id": {
      "type": "integer",
      "format": "int32"
    },
    "interpretAbstract": {
      "type": "string"
    },
    "interpretBody": {
      "type": "string"
    },
    "interpretDepart": {
      "type": "string"
    },
    "interpretTitle": {
      "type": "string"
    },
    "status": {
      "type": "string"
    },
    "time": {
      "type": "string"
    },
    "user": {
      "type": "string"
    }
  },
  "title": "PolicyInterpretationVO"
}

```

PolicyInterpretationVO

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|docDepart|string|false|none|none|
|docIdentifier|string|false|none|none|
|docTitle|string|false|none|none|
|id|integer(int32)|false|none|none|
|interpretAbstract|string|false|none|none|
|interpretBody|string|false|none|none|
|interpretDepart|string|false|none|none|
|interpretTitle|string|false|none|none|
|status|string|false|none|none|
|time|string|false|none|none|
|user|string|false|none|none|

<h2 id="tocS_用户详情VO">用户详情VO</h2>
<!-- backwards compatibility -->
<a id="schema用户详情vo"></a>
<a id="schema_用户详情VO"></a>
<a id="tocS用户详情vo"></a>
<a id="tocs用户详情vo"></a>

```json
{
  "type": "object",
  "properties": {
    "id": {
      "type": "integer",
      "format": "int32"
    },
    "name": {
      "type": "string"
    }
  },
  "title": "用户详情VO"
}

```

用户详情VO

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|id|integer(int32)|false|none|none|
|name|string|false|none|none|

<h2 id="tocS_PolicyRetrievalVO">PolicyRetrievalVO</h2>
<!-- backwards compatibility -->
<a id="schemapolicyretrievalvo"></a>
<a id="schema_PolicyRetrievalVO"></a>
<a id="tocSpolicyretrievalvo"></a>
<a id="tocspolicyretrievalvo"></a>

```json
{
  "type": "object",
  "properties": {
    "piId": {
      "type": "integer",
      "format": "int32"
    },
    "text": {
      "type": "string"
    }
  },
  "title": "PolicyRetrievalVO"
}

```

PolicyRetrievalVO

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|piId|integer(int32)|false|none|none|
|text|string|false|none|none|

<h2 id="tocS_deleteByIdUsingPOSTId">deleteByIdUsingPOSTId</h2>
<!-- backwards compatibility -->
<a id="schemadeletebyidusingpostid"></a>
<a id="schema_deleteByIdUsingPOSTId"></a>
<a id="tocSdeletebyidusingpostid"></a>
<a id="tocsdeletebyidusingpostid"></a>

```json
{
  "type": "integer",
  "format": "int32"
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|*anonymous*|integer(int32)|false|none|none|

<h2 id="tocS_User">User</h2>
<!-- backwards compatibility -->
<a id="schemauser"></a>
<a id="schema_User"></a>
<a id="tocSuser"></a>
<a id="tocsuser"></a>

```json
{
  "type": "object",
  "properties": {
    "accountNonExpired": {
      "type": "boolean"
    },
    "accountNonLocked": {
      "type": "boolean"
    },
    "authorities": {
      "type": "array",
      "items": {
        "type": "object",
        "properties": {
          "authority": {
            "type": "string"
          }
        },
        "title": "GrantedAuthority"
      }
    },
    "credentialsNonExpired": {
      "type": "boolean"
    },
    "enabled": {
      "type": "boolean"
    },
    "id": {
      "type": "integer",
      "format": "int32"
    },
    "name": {
      "type": "string"
    },
    "password": {
      "type": "string"
    },
    "username": {
      "type": "string"
    }
  },
  "title": "User"
}

```

User

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|accountNonExpired|boolean|false|none|none|
|accountNonLocked|boolean|false|none|none|
|authorities|[[GrantedAuthority](#schemagrantedauthority)]|false|none|none|
|credentialsNonExpired|boolean|false|none|none|
|enabled|boolean|false|none|none|
|id|integer(int32)|false|none|none|
|name|string|false|none|none|
|password|string|false|none|none|
|username|string|false|none|none|

<h2 id="tocS_ListPolicyInterpretFilter">ListPolicyInterpretFilter</h2>
<!-- backwards compatibility -->
<a id="schemalistpolicyinterpretfilter"></a>
<a id="schema_ListPolicyInterpretFilter"></a>
<a id="tocSlistpolicyinterpretfilter"></a>
<a id="tocslistpolicyinterpretfilter"></a>

```json
{
  "type": "object",
  "properties": {
    "currIndex": {
      "type": "integer",
      "format": "int32"
    },
    "docDepart": {
      "type": "string"
    },
    "docIdentifier": {
      "type": "string"
    },
    "docTitle": {
      "type": "string"
    },
    "endTime": {
      "type": "string"
    },
    "pageSize": {
      "type": "integer",
      "format": "int32"
    },
    "startTime": {
      "type": "string"
    },
    "withBody": {
      "type": "boolean"
    }
  },
  "title": "ListPolicyInterpretFilter"
}

```

ListPolicyInterpretFilter

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|currIndex|integer(int32)|false|none|none|
|docDepart|string|false|none|none|
|docIdentifier|string|false|none|none|
|docTitle|string|false|none|none|
|endTime|string|false|none|none|
|pageSize|integer(int32)|false|none|none|
|startTime|string|false|none|none|
|withBody|boolean|false|none|none|

<h2 id="tocS_Category">Category</h2>
<!-- backwards compatibility -->
<a id="schemacategory"></a>
<a id="schema_Category"></a>
<a id="tocScategory"></a>
<a id="tocscategory"></a>

```json
{
  "type": "object",
  "properties": {
    "id": {
      "type": "integer",
      "format": "int64",
      "minimum": 1,
      "description": "分类ID编号"
    },
    "name": {
      "type": "string",
      "description": "分类名称"
    }
  },
  "xml": {
    "name": "Category"
  }
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|id|integer(int64)|false|none|分类ID编号|
|name|string|false|none|分类名称|

<h2 id="tocS_AddCommentVO">AddCommentVO</h2>
<!-- backwards compatibility -->
<a id="schemaaddcommentvo"></a>
<a id="schema_AddCommentVO"></a>
<a id="tocSaddcommentvo"></a>
<a id="tocsaddcommentvo"></a>

```json
{
  "type": "object",
  "properties": {
    "comment": {
      "type": "string"
    },
    "piId": {
      "type": "integer",
      "format": "int32"
    },
    "refCommentId": {
      "type": "integer",
      "format": "int32"
    }
  },
  "title": "AddCommentVO"
}

```

AddCommentVO

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|comment|string|false|none|none|
|piId|integer(int32)|false|none|none|
|refCommentId|integer(int32)|false|none|none|

<h2 id="tocS_PolicyInterpretation">PolicyInterpretation</h2>
<!-- backwards compatibility -->
<a id="schemapolicyinterpretation"></a>
<a id="schema_PolicyInterpretation"></a>
<a id="tocSpolicyinterpretation"></a>
<a id="tocspolicyinterpretation"></a>

```json
{
  "type": "object",
  "properties": {
    "docDepart": {
      "type": "string"
    },
    "docIdentifier": {
      "type": "string"
    },
    "docTitle": {
      "type": "string"
    },
    "id": {
      "type": "integer",
      "format": "int32"
    },
    "interpretAbstract": {
      "type": "string"
    },
    "interpretBody": {
      "type": "string"
    },
    "interpretDepart": {
      "type": "string"
    },
    "interpretTitle": {
      "type": "string"
    },
    "status": {
      "type": "string",
      "enum": [
        "DRAFT",
        "PUBLIC",
        "ABOLISH"
      ]
    },
    "time": {
      "type": "string"
    },
    "user": {
      "type": "string"
    }
  },
  "title": "PolicyInterpretation"
}

```

PolicyInterpretation

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|docDepart|string|false|none|none|
|docIdentifier|string|false|none|none|
|docTitle|string|false|none|none|
|id|integer(int32)|false|none|none|
|interpretAbstract|string|false|none|none|
|interpretBody|string|false|none|none|
|interpretDepart|string|false|none|none|
|interpretTitle|string|false|none|none|
|status|string|false|none|none|
|time|string|false|none|none|
|user|string|false|none|none|

#### 枚举值

|属性|值|
|---|---|
|status|DRAFT|
|status|PUBLIC|
|status|ABOLISH|

<h2 id="tocS_doc_depart统计信息VO">doc_depart统计信息VO</h2>
<!-- backwards compatibility -->
<a id="schemadoc_depart统计信息vo"></a>
<a id="schema_doc_depart统计信息VO"></a>
<a id="tocSdoc_depart统计信息vo"></a>
<a id="tocsdoc_depart统计信息vo"></a>

```json
{
  "type": "object",
  "properties": {
    "docDepart": {
      "type": "string"
    },
    "docDepartCount": {
      "type": "integer",
      "format": "int32"
    }
  },
  "title": "doc_depart统计信息VO"
}

```

doc_depart统计信息VO

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|docDepart|string|false|none|none|
|docDepartCount|integer(int32)|false|none|none|

<h2 id="tocS_GrantedAuthority">GrantedAuthority</h2>
<!-- backwards compatibility -->
<a id="schemagrantedauthority"></a>
<a id="schema_GrantedAuthority"></a>
<a id="tocSgrantedauthority"></a>
<a id="tocsgrantedauthority"></a>

```json
{
  "type": "object",
  "properties": {
    "authority": {
      "type": "string"
    }
  },
  "title": "GrantedAuthority"
}

```

GrantedAuthority

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|authority|string|false|none|none|

<h2 id="tocS_AddOrUpdatePolicyInterpretationVO">AddOrUpdatePolicyInterpretationVO</h2>
<!-- backwards compatibility -->
<a id="schemaaddorupdatepolicyinterpretationvo"></a>
<a id="schema_AddOrUpdatePolicyInterpretationVO"></a>
<a id="tocSaddorupdatepolicyinterpretationvo"></a>
<a id="tocsaddorupdatepolicyinterpretationvo"></a>

```json
{
  "type": "object",
  "properties": {
    "docDepart": {
      "type": "string"
    },
    "docIdentifier": {
      "type": "string"
    },
    "docTitle": {
      "type": "string"
    },
    "id": {
      "type": "integer",
      "format": "int32"
    },
    "interpretAbstract": {
      "type": "string"
    },
    "interpretBody": {
      "type": "string"
    },
    "interpretDepart": {
      "type": "string"
    },
    "interpretTitle": {
      "type": "string"
    },
    "status": {
      "type": "string"
    },
    "time": {
      "type": "string"
    }
  },
  "title": "AddOrUpdatePolicyInterpretationVO"
}

```

AddOrUpdatePolicyInterpretationVO

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|docDepart|string|false|none|none|
|docIdentifier|string|false|none|none|
|docTitle|string|false|none|none|
|id|integer(int32)|false|none|none|
|interpretAbstract|string|false|none|none|
|interpretBody|string|false|none|none|
|interpretDepart|string|false|none|none|
|interpretTitle|string|false|none|none|
|status|string|false|none|none|
|time|string|false|none|none|

<h2 id="tocS_PageList«PolicyInterpretationVO»">PageList«PolicyInterpretationVO»</h2>
<!-- backwards compatibility -->
<a id="schemapagelist«policyinterpretationvo»"></a>
<a id="schema_PageList«PolicyInterpretationVO»"></a>
<a id="tocSpagelist«policyinterpretationvo»"></a>
<a id="tocspagelist«policyinterpretationvo»"></a>

```json
{
  "type": "object",
  "properties": {
    "data": {
      "type": "array",
      "items": {
        "type": "object",
        "properties": {}
      }
    },
    "total": {
      "type": "integer",
      "format": "int32"
    }
  },
  "title": "PageList«PolicyInterpretationVO»"
}

```

PageList«PolicyInterpretationVO»

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|data|[object]|false|none|none|
|total|integer(int32)|false|none|none|

<h2 id="tocS_评论条目">评论条目</h2>
<!-- backwards compatibility -->
<a id="schema评论条目"></a>
<a id="schema_评论条目"></a>
<a id="tocS评论条目"></a>
<a id="tocs评论条目"></a>

```json
{
  "type": "object",
  "properties": {
    "comment": {
      "type": "string"
    },
    "id": {
      "type": "integer",
      "format": "int32"
    },
    "piId": {
      "type": "integer",
      "format": "int32"
    },
    "ref": {
      "type": "object",
      "properties": {}
    },
    "time": {
      "type": "string"
    },
    "userInfo": {
      "type": "object",
      "properties": {}
    }
  },
  "title": "评论条目"
}

```

评论条目

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|comment|string|false|none|none|
|id|integer(int32)|false|none|none|
|piId|integer(int32)|false|none|none|
|ref|object|false|none|none|
|time|string|false|none|none|
|userInfo|object|false|none|none|

<h2 id="tocS_ResponseEntity">ResponseEntity</h2>
<!-- backwards compatibility -->
<a id="schemaresponseentity"></a>
<a id="schema_ResponseEntity"></a>
<a id="tocSresponseentity"></a>
<a id="tocsresponseentity"></a>

```json
{
  "type": "object",
  "properties": {
    "body": {
      "type": "object"
    },
    "statusCode": {
      "type": "string",
      "enum": [
        "100 CONTINUE",
        "101 SWITCHING_PROTOCOLS",
        "102 PROCESSING",
        "103 CHECKPOINT",
        "200 OK",
        "201 CREATED",
        "202 ACCEPTED",
        "203 NON_AUTHORITATIVE_INFORMATION",
        "204 NO_CONTENT",
        "205 RESET_CONTENT",
        "206 PARTIAL_CONTENT",
        "207 MULTI_STATUS",
        "208 ALREADY_REPORTED",
        "226 IM_USED",
        "300 MULTIPLE_CHOICES",
        "301 MOVED_PERMANENTLY",
        "302 FOUND",
        "302 MOVED_TEMPORARILY",
        "303 SEE_OTHER",
        "304 NOT_MODIFIED",
        "305 USE_PROXY",
        "307 TEMPORARY_REDIRECT",
        "308 PERMANENT_REDIRECT",
        "400 BAD_REQUEST",
        "401 UNAUTHORIZED",
        "402 PAYMENT_REQUIRED",
        "403 FORBIDDEN",
        "404 NOT_FOUND",
        "405 METHOD_NOT_ALLOWED",
        "406 NOT_ACCEPTABLE",
        "407 PROXY_AUTHENTICATION_REQUIRED",
        "408 REQUEST_TIMEOUT",
        "409 CONFLICT",
        "410 GONE",
        "411 LENGTH_REQUIRED",
        "412 PRECONDITION_FAILED",
        "413 PAYLOAD_TOO_LARGE",
        "413 REQUEST_ENTITY_TOO_LARGE",
        "414 URI_TOO_LONG",
        "414 REQUEST_URI_TOO_LONG",
        "415 UNSUPPORTED_MEDIA_TYPE",
        "416 REQUESTED_RANGE_NOT_SATISFIABLE",
        "417 EXPECTATION_FAILED",
        "418 I_AM_A_TEAPOT",
        "419 INSUFFICIENT_SPACE_ON_RESOURCE",
        "420 METHOD_FAILURE",
        "421 DESTINATION_LOCKED",
        "422 UNPROCESSABLE_ENTITY",
        "423 LOCKED",
        "424 FAILED_DEPENDENCY",
        "426 UPGRADE_REQUIRED",
        "428 PRECONDITION_REQUIRED",
        "429 TOO_MANY_REQUESTS",
        "431 REQUEST_HEADER_FIELDS_TOO_LARGE",
        "451 UNAVAILABLE_FOR_LEGAL_REASONS",
        "500 INTERNAL_SERVER_ERROR",
        "501 NOT_IMPLEMENTED",
        "502 BAD_GATEWAY",
        "503 SERVICE_UNAVAILABLE",
        "504 GATEWAY_TIMEOUT",
        "505 HTTP_VERSION_NOT_SUPPORTED",
        "506 VARIANT_ALSO_NEGOTIATES",
        "507 INSUFFICIENT_STORAGE",
        "508 LOOP_DETECTED",
        "509 BANDWIDTH_LIMIT_EXCEEDED",
        "510 NOT_EXTENDED",
        "511 NETWORK_AUTHENTICATION_REQUIRED"
      ]
    },
    "statusCodeValue": {
      "type": "integer",
      "format": "int32"
    }
  },
  "title": "ResponseEntity"
}

```

ResponseEntity

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|body|object|false|none|none|
|statusCode|string|false|none|none|
|statusCodeValue|integer(int32)|false|none|none|

#### 枚举值

|属性|值|
|---|---|
|statusCode|100 CONTINUE|
|statusCode|101 SWITCHING_PROTOCOLS|
|statusCode|102 PROCESSING|
|statusCode|103 CHECKPOINT|
|statusCode|200 OK|
|statusCode|201 CREATED|
|statusCode|202 ACCEPTED|
|statusCode|203 NON_AUTHORITATIVE_INFORMATION|
|statusCode|204 NO_CONTENT|
|statusCode|205 RESET_CONTENT|
|statusCode|206 PARTIAL_CONTENT|
|statusCode|207 MULTI_STATUS|
|statusCode|208 ALREADY_REPORTED|
|statusCode|226 IM_USED|
|statusCode|300 MULTIPLE_CHOICES|
|statusCode|301 MOVED_PERMANENTLY|
|statusCode|302 FOUND|
|statusCode|302 MOVED_TEMPORARILY|
|statusCode|303 SEE_OTHER|
|statusCode|304 NOT_MODIFIED|
|statusCode|305 USE_PROXY|
|statusCode|307 TEMPORARY_REDIRECT|
|statusCode|308 PERMANENT_REDIRECT|
|statusCode|400 BAD_REQUEST|
|statusCode|401 UNAUTHORIZED|
|statusCode|402 PAYMENT_REQUIRED|
|statusCode|403 FORBIDDEN|
|statusCode|404 NOT_FOUND|
|statusCode|405 METHOD_NOT_ALLOWED|
|statusCode|406 NOT_ACCEPTABLE|
|statusCode|407 PROXY_AUTHENTICATION_REQUIRED|
|statusCode|408 REQUEST_TIMEOUT|
|statusCode|409 CONFLICT|
|statusCode|410 GONE|
|statusCode|411 LENGTH_REQUIRED|
|statusCode|412 PRECONDITION_FAILED|
|statusCode|413 PAYLOAD_TOO_LARGE|
|statusCode|413 REQUEST_ENTITY_TOO_LARGE|
|statusCode|414 URI_TOO_LONG|
|statusCode|414 REQUEST_URI_TOO_LONG|
|statusCode|415 UNSUPPORTED_MEDIA_TYPE|
|statusCode|416 REQUESTED_RANGE_NOT_SATISFIABLE|
|statusCode|417 EXPECTATION_FAILED|
|statusCode|418 I_AM_A_TEAPOT|
|statusCode|419 INSUFFICIENT_SPACE_ON_RESOURCE|
|statusCode|420 METHOD_FAILURE|
|statusCode|421 DESTINATION_LOCKED|
|statusCode|422 UNPROCESSABLE_ENTITY|
|statusCode|423 LOCKED|
|statusCode|424 FAILED_DEPENDENCY|
|statusCode|426 UPGRADE_REQUIRED|
|statusCode|428 PRECONDITION_REQUIRED|
|statusCode|429 TOO_MANY_REQUESTS|
|statusCode|431 REQUEST_HEADER_FIELDS_TOO_LARGE|
|statusCode|451 UNAVAILABLE_FOR_LEGAL_REASONS|
|statusCode|500 INTERNAL_SERVER_ERROR|
|statusCode|501 NOT_IMPLEMENTED|
|statusCode|502 BAD_GATEWAY|
|statusCode|503 SERVICE_UNAVAILABLE|
|statusCode|504 GATEWAY_TIMEOUT|
|statusCode|505 HTTP_VERSION_NOT_SUPPORTED|
|statusCode|506 VARIANT_ALSO_NEGOTIATES|
|statusCode|507 INSUFFICIENT_STORAGE|
|statusCode|508 LOOP_DETECTED|
|statusCode|509 BANDWIDTH_LIMIT_EXCEEDED|
|statusCode|510 NOT_EXTENDED|
|statusCode|511 NETWORK_AUTHENTICATION_REQUIRED|

<h2 id="tocS_ResponseVO">ResponseVO</h2>
<!-- backwards compatibility -->
<a id="schemaresponsevo"></a>
<a id="schema_ResponseVO"></a>
<a id="tocSresponsevo"></a>
<a id="tocsresponsevo"></a>

```json
{
  "type": "object",
  "properties": {
    "message": {
      "type": "string"
    },
    "success": {
      "type": "boolean"
    }
  },
  "title": "ResponseVO"
}

```

ResponseVO

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|message|string|false|none|none|
|success|boolean|false|none|none|

