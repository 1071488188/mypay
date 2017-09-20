define({ "api": [
  {
    "type": "post",
    "url": "/api/v1/demo/test",
    "title": "1.api Demo测试",
    "version": "1.0.0",
    "name": "Demo_api_Demo__",
    "group": "Demo___",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>Demo测试</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "price",
            "description": "<p>价格</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "date",
            "description": "<p>时间</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "province",
            "description": "<p>地区</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespCode",
            "description": "<p>响应编码，8位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespDesc",
            "description": "<p>响应描述</p>"
          },
          {
            "group": "200",
            "type": "Object[]",
            "optional": false,
            "field": "Data",
            "description": "<p>响应数据</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.id",
            "description": "<p>ID</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.title",
            "description": "<p>标题</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.realType",
            "description": "<p>类型</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.date",
            "description": "<p>公告时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.province",
            "description": "<p>行政区域</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/demo/DemoResource.java",
    "groupTitle": "Demo___",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/demo/test"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/admin/closeAnAccount",
    "title": "4.api 结算",
    "version": "1.0.0",
    "name": "__",
    "group": "admin",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>结算</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "billingId",
            "description": "<p>结算记录id</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespCode",
            "description": "<p>响应编码，8位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespDesc",
            "description": "<p>响应描述</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "Data",
            "description": ""
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/administrator/AdministratorResource.java",
    "groupTitle": "admin",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/admin/closeAnAccount"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/admin/withdrawDeposit",
    "title": "6.api 提现",
    "version": "1.0.0",
    "name": "__",
    "group": "admin",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>提现</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "reflectTheAmountOf",
            "description": "<p>提现金额</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespCode",
            "description": "<p>响应编码，8位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespDesc",
            "description": "<p>响应描述</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "Data",
            "description": ""
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/administrator/AdministratorResource.java",
    "groupTitle": "admin",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/admin/withdrawDeposit"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/v1/admin/settlementRecords",
    "title": "3.api 结算记录",
    "version": "1.0.0",
    "name": "____",
    "group": "admin",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>结算记录</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>页码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示条数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespCode",
            "description": "<p>响应编码，8位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespDesc",
            "description": "<p>响应描述</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "Data",
            "description": "<p>响应数据</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.id",
            "description": ""
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.time",
            "description": "<p>时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.commission",
            "description": "<p>营业额</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.ratio",
            "description": "<p>当前返佣比例</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.status",
            "description": "<p>结算状态2、已结算3、待结算</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/administrator/AdministratorResource.java",
    "groupTitle": "admin",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/admin/settlementRecords"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/v1/admin/balanceDetails",
    "title": "5.api 余额明细",
    "version": "1.0.0",
    "name": "____",
    "group": "admin",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>余额明细</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>页码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示条数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespCode",
            "description": "<p>响应编码，8位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespDesc",
            "description": "<p>响应描述</p>"
          },
          {
            "group": "200",
            "type": "Object[]",
            "optional": false,
            "field": "Data",
            "description": "<p>响应数据</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.applyTimeZh",
            "description": "<p>时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.typeZh",
            "description": "<p>明细名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.money",
            "description": "<p>金额</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/administrator/AdministratorResource.java",
    "groupTitle": "admin",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/admin/balanceDetails"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/v1/admin/expenseCalendar",
    "title": "2.api 消费记录",
    "version": "1.0.0",
    "name": "____",
    "group": "admin",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>消费记录</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>页码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示条数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespCode",
            "description": "<p>响应编码，8位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespDesc",
            "description": "<p>响应描述</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "Data",
            "description": "<p>响应数据</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.theNumberOfConsumer",
            "description": "<p>消费人数</p>"
          },
          {
            "group": "200",
            "type": "Object[]",
            "optional": false,
            "field": "Data.particulars",
            "description": "<p>列表</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.particulars.headimgUrl",
            "description": "<p>头像</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.particulars.name",
            "description": "<p>昵称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.particulars.theCumulative",
            "description": "<p>累计消费</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/administrator/AdministratorResource.java",
    "groupTitle": "admin",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/admin/expenseCalendar"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/admin/bindManager",
    "title": "1.api 绑定手机号",
    "version": "1.0.0",
    "name": "_____",
    "group": "admin",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>绑定手机号</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cellPhoneNumber",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "verificationCode",
            "description": "<p>验证码</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespCode",
            "description": "<p>响应编码，8位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespDesc",
            "description": "<p>响应描述</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "Data",
            "description": ""
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/administrator/AdministratorResource.java",
    "groupTitle": "admin",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/admin/bindManager"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/v1/dispatch/replenishmentList",
    "title": "3.api 补货列表",
    "version": "1.0.0",
    "name": "dispatch_api_____",
    "group": "dispatch",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>补货列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "dispatchNo",
            "description": "<p>配送单号</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespCode",
            "description": "<p>响应编码，8位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespDesc",
            "description": "<p>响应描述</p>"
          },
          {
            "group": "200",
            "type": "Object[]",
            "optional": false,
            "field": "Data",
            "description": "<p>响应数据</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.replenishmentNum",
            "description": "<p>补货总数</p>"
          },
          {
            "group": "200",
            "type": "Object[]",
            "optional": false,
            "field": "Data.layer",
            "description": "<p>层级(layer为：jsonObject的key)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.layer.goodsName",
            "description": "<p>商品名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.layer.goodsValue",
            "description": "<p>商品单价</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.layer.goodsPicture",
            "description": "<p>商品图片路径</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.layer.goodsId",
            "description": "<p>商品ID</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/dispatch/DispatchResource.java",
    "groupTitle": "dispatch",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/dispatch/replenishmentList"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/dispatch/confirmReplenishment",
    "title": "4.api 确认补货",
    "version": "1.0.0",
    "name": "dispatch_api_____",
    "group": "dispatch",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>确认补货</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "dispatchNo",
            "description": "<p>配送单号</p>"
          },
          {
            "group": "Parameter",
            "type": "Object[]",
            "optional": false,
            "field": "Data.layer",
            "description": "<p>层级(layer为：jsonObject的key)</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "Data.layer.goodsId",
            "description": "<p>商品ID</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "Data.layer.ifConfirm",
            "description": "<p>是否确认(0：未确认 、1:已确认)</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespCode",
            "description": "<p>响应编码，8位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespDesc",
            "description": "<p>响应描述</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/dispatch/DispatchResource.java",
    "groupTitle": "dispatch",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/dispatch/confirmReplenishment"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/v1/dispatch/dispatchList",
    "title": "1.api 配送中心列表",
    "version": "1.0.0",
    "name": "dispatch_api_______",
    "group": "dispatch",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>配送中心列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "status[]",
            "description": "<p>状态（0: 未接单、1: 已接单、2: 派送中、3: 已完成）</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespCode",
            "description": "<p>响应编码，8位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespDesc",
            "description": "<p>响应描述</p>"
          },
          {
            "group": "200",
            "type": "Object[]",
            "optional": false,
            "field": "Data",
            "description": "<p>响应数据</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.storeName",
            "description": "<p>取货位置</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.position",
            "description": "<p>配送位置</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.status",
            "description": "<p>状态（0: 未接单、1: 已接单、2: 派送中、3: 已完成）</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dispatchNo",
            "description": "<p>配送单号</p>"
          },
          {
            "group": "200",
            "type": "Object[]",
            "optional": false,
            "field": "Data.carrierNote",
            "description": "<p>取货单</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.carrierNote.goodsName",
            "description": "<p>商品名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.carrierNote.spec",
            "description": "<p>商品规格</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.carrierNote.quantity",
            "description": "<p>商品数量</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/dispatch/DispatchResource.java",
    "groupTitle": "dispatch",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/dispatch/dispatchList"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/dispatch/updateDispatchStatus",
    "title": "2.api 更新配送单状态",
    "version": "1.0.0",
    "name": "dispatch_api________",
    "group": "dispatch",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>更新配送单状态</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "dispatchNo",
            "description": "<p>配送单号</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "status",
            "description": "<p>状态（0: 未接单、1: 已接单、2: 派送中、3: 已完成）</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespCode",
            "description": "<p>响应编码，8位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespDesc",
            "description": "<p>响应描述</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/dispatch/DispatchResource.java",
    "groupTitle": "dispatch",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/dispatch/updateDispatchStatus"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/smsValidate/sendValidate",
    "title": "1.api 发送短信验证码",
    "version": "1.0.0",
    "name": "smsValidate_api________",
    "group": "smsValidate",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>发送短信验证码</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespCode",
            "description": "<p>响应编码，8位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespDesc",
            "description": "<p>响应描述</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/smsvalidate/SmsValidateResource.java",
    "groupTitle": "smsValidate",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/smsValidate/sendValidate"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/smsValidate/validateCode",
    "title": "2.api 验证短信验证码",
    "version": "1.0.0",
    "name": "smsValidate_api________",
    "group": "smsValidate",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>验证短信验证码</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "validateCode",
            "description": "<p>验证码</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespCode",
            "description": "<p>响应编码，8位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "RespDesc",
            "description": "<p>响应描述</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/smsvalidate/SmsValidateResource.java",
    "groupTitle": "smsValidate",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/smsValidate/validateCode"
      }
    ]
  }
] });
