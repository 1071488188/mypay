package com.har.unmanned.mfront.model.extend;

import com.har.unmanned.mfront.model.ShopOrder;
import com.har.unmanned.mfront.utils.CheckUtil;
import lombok.Data;
import org.springframework.util.Base64Utils;

/**
 * 订单扩展
 * Created by jiang on 2017/9/20.
 */
@Data
public class ShopOrderExtend extends ShopOrder {
    private String headimgUrl;
    private String amountZh;
    private String name;

    @Override
    public String getName()  {
        String str="";
        try {
            if (!CheckUtil.isNull(this.name)){
                str= new String(Base64Utils.decode( this.name.getBytes("utf-8"))) ;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

     return str;
    }

}
