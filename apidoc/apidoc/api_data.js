define({ "api": [
  {
    "type": "get",
    "url": "/api/v1/admin/balanceDetails",
    "title": "4.api 余额明细",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "currenturl",
            "description": "<p>当前url</p>"
          }
        ]
      }
    },
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
            "type": "Object",
            "optional": false,
            "field": "Data",
            "description": "<p>响应数据</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.shopAccountMoneyZh",
            "description": "<p>账户余额</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.totalCount",
            "description": "<p>总条数</p>"
          },
          {
            "group": "200",
            "type": "Object[]",
            "optional": false,
            "field": "Data.totalList",
            "description": "<p>响应数据</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.totalList.applyTimeZh",
            "description": "<p>时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.totalList.typeZh",
            "description": "<p>明细名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.totalList.moneyZh",
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
    "url": "/api/v1/admin/closeAnAccount",
    "title": "3.api 结算",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "currenturl",
            "description": "<p>当前url</p>"
          }
        ]
      }
    },
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
    "title": "1.api 消费记录",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "currenturl",
            "description": "<p>当前url</p>"
          }
        ]
      }
    },
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
            "field": "Data.particulars.amountZh",
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
    "title": "2.api 结算记录",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "currenturl",
            "description": "<p>当前url</p>"
          }
        ]
      }
    },
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
            "type": "Number",
            "optional": false,
            "field": "Data.totalCount",
            "description": "<p>总条数</p>"
          },
          {
            "group": "200",
            "type": "Object[]",
            "optional": false,
            "field": "Data.totalList",
            "description": "<p>列表</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.totalList.id",
            "description": ""
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.totalList.time",
            "description": "<p>时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.totalList.amountZh",
            "description": "<p>营业额</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.totalList.ratio",
            "description": "<p>当前返佣比例</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.totalList.commissionZh",
            "description": "<p>佣金已结算</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.totalList.status",
            "description": "<p>结算状态0、待结算1、已结算</p>"
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
    "title": "5.api 提现",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "currenturl",
            "description": "<p>当前url</p>"
          }
        ]
      }
    },
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
    "url": "/api/v1/dispatch/confirmPickUp",
    "title": "4.api 确认取货",
    "version": "1.0.0",
    "name": "confirmPickUp",
    "group": "dispatch",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>确认取货</p>",
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
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/dispatch/DispatchResource.java",
    "groupTitle": "配送中心接口",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/dispatch/confirmPickUp"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/dispatch/confirmReplenishment",
    "title": "5.api 确认补货",
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
            "field": "goodsIds",
            "description": "<p>商品IDs(已确认商品集合)</p>"
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
            "description": "<p>状态以逗号分隔（1: 未接单、2: 派送中、3: 已完成）</p>"
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
            "type": "Object",
            "optional": false,
            "field": "Data",
            "description": "<p>响应数据</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.totalCount",
            "description": "<p>总数</p>"
          },
          {
            "group": "200",
            "type": "Object[]",
            "optional": false,
            "field": "Data.dataList",
            "description": "<p>数据集合</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.storeName",
            "description": "<p>仓库名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.storeAddress",
            "description": "<p>仓库位置</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.shopName",
            "description": "<p>货架名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.shopAddress",
            "description": "<p>货架位置</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.status",
            "description": "<p>状态（1: 未接单、2: 派送中、3: 已完成）</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.dispatchNo",
            "description": "<p>配送单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.shopCode",
            "description": "<p>货架编号</p>"
          },
          {
            "group": "200",
            "type": "Object[]",
            "optional": false,
            "field": "Data.dataList.carrierNote",
            "description": "<p>取货单</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.carrierNote.goodsName",
            "description": "<p>商品名称</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.dataList.carrierNote.quantity",
            "description": "<p>商品数量</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.dataList.carrierNote.spec",
            "description": "<p>商品规格</p>"
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
            "type": "Object",
            "optional": false,
            "field": "Data",
            "description": "<p>响应数据</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.replenishmentNum",
            "description": "<p>补货总数</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.species",
            "description": "<p>种类</p>"
          },
          {
            "group": "200",
            "type": "Object[]",
            "optional": false,
            "field": "Data.dataList",
            "description": "<p>商品集合</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.layer",
            "description": "<p>层级</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.goods.goodsList",
            "description": "<p>商品集合</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.goods.goodsList.goodsName",
            "description": "<p>商品名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.goods.goodsList.goodsValue",
            "description": "<p>商品单价</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.goods.goodsList.goodsPicture",
            "description": "<p>商品图片路径</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.goods.goodsList.goodsQuantity",
            "description": "<p>商品数量</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.goods.goodsList.goodsId",
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
    "url": "/api/v1/lose/confirmStockLose",
    "title": "2.api 确认货架库存",
    "version": "1.0.0",
    "name": "confirmStockLose",
    "group": "lose",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>货架盘存</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "shopCode",
            "description": "<p>货架编号</p>"
          },
          {
            "group": "Parameter",
            "type": "Object[]",
            "optional": false,
            "field": "stocks",
            "description": "<p>货架盘存集合</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "stocks.goodsId",
            "description": "<p>商品ID</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "stocks.goodsNum",
            "description": "<p>商品个数</p>"
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
    "filename": "../src/main/java/com/har/unmanned/mfront/api/lose/LoseResource.java",
    "groupTitle": "货架遗损",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/lose/confirmStockLose"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/v1/lose/shopStock",
    "title": "1.api 货架商品",
    "version": "1.0.0",
    "name": "shopStock",
    "group": "lose",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>货架商品列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "shopCode",
            "description": "<p>货架编号</p>"
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
            "type": "Object[]",
            "optional": false,
            "field": "Data.dataList",
            "description": "<p>商品集合</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.layer",
            "description": "<p>层级</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.goods.goodsList",
            "description": "<p>商品集合</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.goods.goodsList.goodsName",
            "description": "<p>商品名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.goods.goodsList.goodsValue",
            "description": "<p>商品单价</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.goods.goodsList.goodsPicture",
            "description": "<p>商品图片路径</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.goods.goodsList.goodsQuantity",
            "description": "<p>商品数量</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.goods.goodsList.goodsId",
            "description": "<p>商品ID</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/lose/LoseResource.java",
    "groupTitle": "货架遗损",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/lose/shopStock"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/validation/bindingPhone",
    "title": "3.api 绑定手机号",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "currenturl",
            "description": "<p>当前url</p>"
          }
        ]
      }
    },
    "version": "1.0.0",
    "name": "bindingPhone",
    "group": "vlidate",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>当前用户权限验证</p>",
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
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "roleType",
            "description": "<p>角色类型3、网点管理员4、配送员5、库存盘点</p>"
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
            "field": "Data.roleType",
            "description": "<p>角色类型3、网点管理员4、配送员5、库存盘点</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/validate/ValidateResource.java",
    "groupTitle": "验证接口",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/validation/bindingPhone"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/v1/validation/permissionsValidation",
    "title": "2.api 初始化权限验证",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "currenturl",
            "description": "<p>当前url</p>"
          }
        ]
      }
    },
    "version": "1.0.0",
    "name": "permissionsValidation",
    "group": "vlidate",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>当前用户权限验证</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "roleType",
            "description": "<p>角色类型3、网点管理员4、配送员5、库存盘点</p>"
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
            "field": "Data.roleType",
            "description": "<p>角色类型0、未绑定信息3、网点管理员4、配送员5、库存盘点</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/validate/ValidateResource.java",
    "groupTitle": "验证接口",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/validation/permissionsValidation"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/validation/sendValidate",
    "title": "1.api 发送短信验证码",
    "version": "1.0.0",
    "name": "sendValidate",
    "group": "vlidate",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>发送短信验证码git</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cellPhoneNumber",
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
    "filename": "../src/main/java/com/har/unmanned/mfront/api/validate/ValidateResource.java",
    "groupTitle": "验证接口",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/validation/sendValidate"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/v1/wxUserShop/buyRecord",
    "title": "3.api 购买记录",
    "version": "1.0.0",
    "name": "buyRecord",
    "group": "wxUserShop",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>用户的消费记录</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "shopCode",
            "description": "<p>货架编号</p>"
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
            "type": "Object[]",
            "optional": false,
            "field": "Data.dataList",
            "description": "<p>响应集合</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.orderNo",
            "description": "<p>订单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.amount",
            "description": "<p>订单金额</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.payTimeString",
            "description": "<p>消费时间(YYYY.MM.dd hh:mm:ss)</p>"
          },
          {
            "group": "200",
            "type": "Object[]",
            "optional": false,
            "field": "Data.dataList.goodsList",
            "description": "<p>购买的商品集合</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.goodsList.name",
            "description": "<p>商品名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.goodsList.image",
            "description": "<p>商品图片</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.dataList.goodsList.price",
            "description": "<p>商品单价(元)</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.dataList.goodsList.quantity",
            "description": "<p>商品的购买数量</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/wxUser/WxUserShopResource.java",
    "groupTitle": "微商城",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/wxUserShop/buyRecord"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/wxUserShop/getWxSign",
    "title": "5.api 获取微信签名",
    "version": "1.0.0",
    "name": "getWxSign",
    "group": "wxUserShop",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>获取微信签名</p>",
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
            "field": "Data.appId",
            "description": "<p>公众号的唯一标识</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.nonceStr",
            "description": "<p>随机串</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.timestamp",
            "description": "<p>时间戳</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.signature",
            "description": "<p>签名</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/wxUser/WxUserShopResource.java",
    "groupTitle": "微商城",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/wxUserShop/getWxSign"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/wxUserShop/submitOrder",
    "title": "2.api 提交订单",
    "version": "1.0.0",
    "name": "submitOrder",
    "group": "wxUserShop",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>提交订单</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "shopCode",
            "description": "<p>货架编号</p>"
          },
          {
            "group": "Parameter",
            "type": "Object[]",
            "optional": false,
            "field": "goodsList",
            "description": "<p>所购买的商品的集合</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "goodsList.goodsId",
            "description": "<p>商品id</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "goodsList.goodsNum",
            "description": "<p>商品的个数</p>"
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
            "field": "Data.orderNo",
            "description": "<p>订单号</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "Data.singData",
            "description": "<p>支付签名数据</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.singData.appId",
            "description": "<p>公众号id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.singData.timeStamp",
            "description": "<p>时间戳</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.singData.nonceStr",
            "description": "<p>随机字符串</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.singData.package",
            "description": "<p>订单详情扩展字符串</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.singData.signType",
            "description": "<p>签名方式</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.singData.paySign",
            "description": "<p>签名</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/wxUser/WxUserShopResource.java",
    "groupTitle": "微商城",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/wxUserShop/submitOrder"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/v1/wxUserShop/userInfo",
    "title": "4.api 用户信息",
    "version": "1.0.0",
    "name": "userInfo",
    "group": "wxUserShop",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>用户基本信息</p>",
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
            "field": "Data.name",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.headimgUrl",
            "description": "<p>用户头像</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.shopCode",
            "description": "<p>货架编号</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/wxUser/WxUserShopResource.java",
    "groupTitle": "微商城",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/wxUserShop/userInfo"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/v1/wxUserShop",
    "title": "1.api 微商城首页",
    "version": "1.0.0",
    "name": "wxUserShop",
    "group": "wxUserShop",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>微商城首页</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "shopCode",
            "description": "<p>货架编号</p>"
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
            "field": "Data.shopCode",
            "description": "<p>货架编号</p>"
          },
          {
            "group": "200",
            "type": "Object[]",
            "optional": false,
            "field": "Data.recentPurchaseList",
            "description": "<p>最近购买的集合(两个)</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.recentPurchaseList.goodsId",
            "description": "<p>商品id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.recentPurchaseList.name",
            "description": "<p>商品名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.recentPurchaseList.image",
            "description": "<p>商品图片</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.recentPurchaseList.price",
            "description": "<p>商品单价(元)</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.recentPurchaseList.quantity",
            "description": "<p>商品库存数量</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.recentPurchaseList.layer",
            "description": "<p>商品摆放位置</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.recentPurchaseList.status",
            "description": "<p>商品状态(0: 已售完, 1: 未售完)</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "Data.dataList",
            "description": "<p>数据的集合, {'1':[], '2':[]}这种结构</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.dataList.layer",
            "description": "<p>货架的层级(1, 2, 3, 4, 5)</p>"
          },
          {
            "group": "200",
            "type": "Object[]",
            "optional": false,
            "field": "Data.dataList.goodsList",
            "description": "<p>每一层所对应的商品</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.dataList.goodsList.goodsId",
            "description": "<p>商品id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.goodsList.name",
            "description": "<p>商品名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.goodsList.image",
            "description": "<p>商品图片</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Data.dataList.goodsList.barCode",
            "description": "<p>商品条形码</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.dataList.goodsList.price",
            "description": "<p>商品单价(元)</p>"
          },
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "Data.dataList.goodsList.quantity",
            "description": "<p>商品库存数量</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/wxUser/WxUserShopResource.java",
    "groupTitle": "微商城",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/wxUserShop"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/v1/wxAuth/distributionNotice",
    "title": "1.api 配送通知",
    "version": "1.0.0",
    "name": "distributionNotice",
    "group": "wx",
    "permission": [
      {
        "name": "none"
      }
    ],
    "description": "<p>配送通知</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "title",
            "description": "<p>推送title</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "distributionNumber",
            "description": "<p>配送单号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "deliveryTime",
            "description": "<p>配送时间</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "distributionNetwork",
            "description": "<p>配送网点</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "totalDistribution",
            "description": "<p>配送总计</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "openId",
            "description": "<p>微信openId</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
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
          }
        ]
      }
    },
    "filename": "../src/main/java/com/har/unmanned/mfront/api/wx/WxAuthResource.java",
    "groupTitle": "微信相关接口",
    "groupDescription": "<p>Created by jiang on 2017/9/19.</p>",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/wxAuth/distributionNotice"
      }
    ]
  }
] });
