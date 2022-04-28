package com.sff.rbacdemo.biz.job;

import com.sff.rbacdemo.biz.dto.OverdueBillingsDTO;
import com.sff.rbacdemo.biz.mapper.BillingMapper;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * XxlJob开发示例（Bean模式）
 *
 * 开发步骤：
 *      1、任务开发：在Spring Bean实例中，开发Job方法；
 *      2、注解配置：为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法", destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 *      3、执行日志：需要通过 "XxlJobHelper.log" 打印执行日志；
 *      4、任务结果：默认任务结果为 "成功" 状态，不需要主动设置；如有诉求，比如设置任务结果为失败，可以通过 "XxlJobHelper.handleFail/handleSuccess" 自主设置任务结果；
 *
 * @author xuxueli 2019-12-11 21:52:51
 */
@Slf4j
@Component
public class AbnormalBillingDetector {

    @Autowired
    private BillingMapper billingMapper;

    @XxlJob("detectorHandler")
    public void detectorHandler() throws Exception {
        XxlJobHelper.log("Begin detect abnormal billings: ");
        List<OverdueBillingsDTO> overdueBillings = billingMapper.getOverdueBillings();
        XxlJobHelper.log("There are %d billings have overdued.", overdueBillings.size());
        XxlJobHelper.log("Overdue billings are:");
        overdueBillings.forEach(item -> {
            XxlJobHelper.log(item.getBillingCode() + "\n");
        });
    }
}
