package com.sff.rbacdemo.biz.job;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.sff.rbacdemo.biz.dto.OverdueBillingsDTO;
import com.sff.rbacdemo.biz.mapper.BillingMapper;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

/**
 * 定时任务（Bean模式)
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
        XxlJobHelper.log("Overdue billings are generated.");
        XxlJobHelper.log("Overdue billings are: " + overdueBillings.toString());
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("Overdue Billings","Overdue Billing List"),
                OverdueBillingsDTO.class, overdueBillings);
        StringBuilder fileName = new StringBuilder("Overdue_Billings_");
        fileName.append(new Date().getTime());
        fileName.append(".xls");
        FileOutputStream fos = new FileOutputStream(fileName.toString());
        workbook.write(fos);
        fos.close();
    }
}
