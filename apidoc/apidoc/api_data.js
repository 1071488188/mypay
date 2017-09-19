define({ "api": [
  {
    "type": "post",
    "url": "/api/v1/demo/test",
    "title": "1.api Demo测试",
    "version": "1.0.0",
    "name": "Demo_api_Demo__",
    "group": "Demo",
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
    "groupTitle": "Demo",
    "sampleRequest": [
      {
        "url": "http://192.168.70.7:8082/har-unmanned-mfront/api/v1/demo/test"
      }
    ]
  }
] });
