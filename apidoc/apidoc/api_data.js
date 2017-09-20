define({ "api": [
  {
    "type": "get",
    "url": "/api/v1/admin/adminInit",
    "title": "1.api 是否为网点管理员初始化接口",
    "version": "1.0.0",
    "name": "adminInit",
    "group": "admin",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>是否为网点管理员初始化接口</p>",
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
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.whetherNetwork",
            "description": "<p>是否为网点管理员，0、否1、是</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/administrator/AdministratorResource.java",
    "groupTitle": "管理员接口",
    "groupDescription": "<p>Created by jiang on 2017/9/19.</p>",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/admin/adminInit"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/v1/admin/balanceDetails",
    "title": "6.api 余额明细",
    "version": "1.0.0",
    "name": "balanceDetails",
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
    "groupTitle": "管理员接口",
    "groupDescription": "<p>Created by jiang on 2017/9/19.</p>",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/admin/balanceDetails"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/admin/bindManager",
    "title": "2.api 绑定手机号",
    "version": "1.0.0",
    "name": "bindManager",
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
    "groupTitle": "管理员接口",
    "groupDescription": "<p>Created by jiang on 2017/9/19.</p>",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/admin/bindManager"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/admin/closeAnAccount",
    "title": "5.api 结算",
    "version": "1.0.0",
    "name": "closeAnAccount",
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
    "groupTitle": "管理员接口",
    "groupDescription": "<p>Created by jiang on 2017/9/19.</p>",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/admin/closeAnAccount"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/v1/admin/expenseCalendar",
    "title": "3.api 消费记录",
    "version": "1.0.0",
    "name": "expenseCalendar",
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
    "groupTitle": "管理员接口",
    "groupDescription": "<p>Created by jiang on 2017/9/19.</p>",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/admin/expenseCalendar"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/v1/admin/settlementRecords",
    "title": "4.api 结算记录",
    "version": "1.0.0",
    "name": "settlementRecords",
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
    "groupTitle": "管理员接口",
    "groupDescription": "<p>Created by jiang on 2017/9/19.</p>",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/admin/settlementRecords"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/admin/withdrawDeposit",
    "title": "7.api 提现",
    "version": "1.0.0",
    "name": "withdrawDeposit",
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
    "groupTitle": "管理员接口",
    "groupDescription": "<p>Created by jiang on 2017/9/19.</p>",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/admin/withdrawDeposit"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/dispatch/confirmReplenishment",
    "title": "4.api 确认补货",
    "version": "1.0.0",
    "name": "confirmReplenishment",
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
    "groupTitle": "配送中心接口",
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
    "name": "dispatchList",
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
            "type": "String",
            "optional": false,
            "field": "status",
            "description": "<p>状态以逗号分隔（0: 未接单、1: 已接单、2: 派送中、3: 已完成）</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "page",
            "defaultValue": "1",
            "description": "<p>当前页数</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "pageSize",
            "defaultValue": "10",
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
            "field": "totalCount",
            "description": "<p>总数</p>"
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
            "field": "Data.storeAddress",
            "description": "<p>取货位置</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.shopAddress",
            "description": "<p>货架位置</p>"
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
    "groupTitle": "配送中心接口",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/dispatch/dispatchList"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/v1/dispatch/replenishmentList",
    "title": "3.api 补货列表",
    "version": "1.0.0",
    "name": "replenishmentList",
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
    "groupTitle": "配送中心接口",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/dispatch/replenishmentList"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/dispatch/updateDispatchStatus",
    "title": "2.api 更新配送单状态",
    "version": "1.0.0",
    "name": "updateDispatchStatus",
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
    "groupTitle": "配送中心接口",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/dispatch/updateDispatchStatus"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/dispatch/validateCode",
    "title": "5.api 验证短信验证码",
    "version": "1.0.0",
    "name": "validateCode_api________",
    "group": "dispatch",
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
    "filename": "../src/main/java/com/har/unmanned/mfront/api/dispatch/DispatchResource.java",
    "groupTitle": "配送中心接口",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/dispatch/validateCode"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/smsValidate/sendValidate",
    "title": "1.api 发送短信验证码",
    "version": "1.0.0",
    "name": "sendValidate",
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
    "groupTitle": "短信管理",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/smsValidate/sendValidate"
      }
    ]
  }
] });
