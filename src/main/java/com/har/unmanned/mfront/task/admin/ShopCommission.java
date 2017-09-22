package com.har.unmanned.mfront.task.admin;

import com.har.bigdata.log.LogHelper;
import com.har.bigdata.thread.ThreadLocalCache;
import com.har.unmanned.mfront.config.CodeConstants;
import com.har.unmanned.mfront.config.Constants;
import com.har.unmanned.mfront.dao.extend.ShopCommissionMapperExtend;
import com.har.unmanned.mfront.model.extend.ShopCommissionExtend;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 生成佣金清单定时任务
 * Created by jiang on 2017/9/20.
 */
@Component
@Slf4j
public class ShopCommission {
    @Autowired
    ShopCommissionMapperExtend shopCommissionMapperExtend;
    @Value("${client.APPID}")
    String APPID;
    /**
     * 生成佣金清单定时任务每月1号00时00分00秒跑上月所有订单生成佣金结算清单
     */
    //@Scheduled(cron="0 0 0 1 1/1 ? ")
    @Scheduled(cron="0 0/1 * * * ? ")
    public void shopCommission(){
        ThreadLocalCache.getInstance().setCache(APPID, Constants.Topic, "shopCommission",null, null);//日志系统存日志
        try {
            String startTime=DateUtil.firstDayOfLastMonth();//开始时间
            String endTime=DateUtil.lastMonth();//结束时间
            //1、查询上月未结算记录
            List<ShopCommissionExtend> shopCommissionExtendList=shopCommissionMapperExtend.selectByShop(startTime,endTime);
            if(!CheckUtil.isNull(shopCommissionExtendList)&&shopCommissionExtendList.size()>0){
                Date dateStartTime=DateUtil.convertToDate(startTime+" 00:00:00");
                Date dateEndTime=DateUtil.convertToDate(endTime+" 00:00:00");
                log.info("查询上月未结算记录条数{}",shopCommissionExtendList.size());
                //2生成未结算集合
                List<ShopCommissionExtend> shopCommissionExtends=new ArrayList<>();
                int size=shopCommissionExtendList.size();
                for(int i=0;i<size;i++){
                    ShopCommissionExtend commissionExtend=shopCommissionExtendList.get(i);
                    ShopCommissionExtend shopCommissionExtend=new ShopCommissionExtend();
                    shopCommissionExtend.setStartTime(dateStartTime);
                    shopCommissionExtend.setEndTime(dateEndTime);
                    shopCommissionExtend.setAmount(commissionExtend.getAmount());
                    shopCommissionExtend.setCommission(commissionExtend.getCommission());
                    shopCommissionExtend.setOrderNum(commissionExtend.getOrderNum());
                    shopCommissionExtend.setRatio(commissionExtend.getRatio());
                    shopCommissionExtend.setShopId(commissionExtend.getShopId());
                    shopCommissionExtend.setStatus(CodeConstants.CommissionStatus.FORTHE);
                    shopCommissionExtend.setCommissionNo(UUID.randomUUID().toString().replace("-",""));
                    shopCommissionExtend.setRatio(commissionExtend.getRatio());
                    shopCommissionExtends.add(shopCommissionExtend);
                    //3、每500条执行一次插入
                    try {
                        if(i!=0&&i<size&&i%500==0){
                            shopCommissionMapperExtend.bulkInsert(shopCommissionExtends);
                            shopCommissionExtends.clear();
                        }else if(i==size-1){
                            shopCommissionMapperExtend.bulkInsert(shopCommissionExtends);
                            shopCommissionExtends.clear();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        log.info("批量插入异常{}",e);
                        LogHelper.saveException(Constants.Topic,e);
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
            log.info("生成佣金清单定时任务异常{}",e);
            LogHelper.saveException(Constants.Topic,e);
        }
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-",""));
    }
}
