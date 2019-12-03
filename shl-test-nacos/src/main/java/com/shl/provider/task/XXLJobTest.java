package com.shl.provider.task;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-11-06
 */
@JobHandler(value = "demoJobHandler")
@Component("demoJobHandler")
public class XXLJobTest extends IJobHandler {
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        System.out.println("XXL-JOB Hello World" + s);
        return ReturnT.SUCCESS;
    }
}
