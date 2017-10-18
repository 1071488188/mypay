package com.har.unmanned.mfront.api;

import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.util.AESUtil;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.App;
import com.har.unmanned.mfront.service.AppService;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.PropertiesUtil;
import com.har.unmanned.mfront.utils.RSAUtil;
import com.har.unmanned.mfront.utils.ReqMessage;
import com.har.utils.security.AESByCbcUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


/**
 * 基类
 * @author tanzeng
 */
@Slf4j
public class ApiBaseController {

	@Autowired
	private AppService appService;
	@Value("${har.msgkey}")
	private String HAR_APP_MSG_KEY;



	protected static Log actionLog = LogFactory.getLog(ApiBaseController.class);
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// 对输入字符串参数转义
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true, true, true));
	}
	/**
	 * 将请求参数Json字符串转换为ReqMessage对象
	 *
	 * @param reqParam
	 * @return
	 * @throws ApiBizException
	 */
	protected ReqMessage getParam(String reqParam) throws ApiBizException {

		try {
			JSONObject message = JSONObject.parseObject(reqParam);
			actionLog.info("请求原始参数：" + message.toJSONString());

			// 检查应用编号是否合法
			String appId = message.getString("AppId");
			if(CheckUtil.isNull(appId)){
				throw new ApiBizException(ErrorCode.E00000002.CODE, "AppId不能为空",null);
			}

			App app = this.appService.getAppById(appId);
			if(app == null){
				throw new ApiBizException(ErrorCode.E00000002.CODE, "应用编号" + appId + "不存在",null);
			}
			// 检查AES密钥是否合法
			String msgKey = message.getString("MsgKey");
			if(CheckUtil.isNull(msgKey)){
				throw new ApiBizException(ErrorCode.E00000002.CODE, "MsgKey不能为空",null);
			}

			RSAUtil rsaUtil = new RSAUtil();
			rsaUtil.loadPrivateKey(AESUtil.getInstance(HAR_APP_MSG_KEY).decrypt(app.getFrontPrikey()));
			msgKey = rsaUtil.decrypt(msgKey);

			// 检查数据是否合法
			String data = message.getString("Data");
			if(CheckUtil.isNull(data)){
				throw new ApiBizException(ErrorCode.E00000002.CODE, "Data不能为空",null);
			}

			// ios采用AES CBC模式进行解密
			if("ios".equals(message.getString("DevType"))){
				data = AESByCbcUtil.getInstance(msgKey).decrypt(data);
			}else{
				data = AESUtil.getInstance(msgKey).decrypt(data);
			}

			JSONObject dataJson = JSONObject.parseObject(data);

			// 设置请求报文对象
			ReqMessage reqMessage = new ReqMessage();
			reqMessage.setAppId(appId);
			reqMessage.setApp(app);
			reqMessage.setMsgKey(msgKey);
			reqMessage.setDataJson(dataJson);
			reqMessage.setDevType(message.getString("DevType"));

			return reqMessage;
		} catch (Exception e) {

			actionLog.error("getParam解析报文格式异常", e);
			throw new ApiBizException(ErrorCode.E00000002.CODE, ErrorCode.E00000002.MSG + ":" + e.getMessage(),null);
		}
	}
}
