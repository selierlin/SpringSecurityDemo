package com.imooc.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AsyncController {
    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/order")
    public DeferredResult<String> order() throws Exception {
        logger.info("主线程开始");

        String orderNumber = RandomStringUtils.randomNumeric(8);
        logger.info("orderNumber="+orderNumber);
        mockQueue.setPlaceOrder(orderNumber);

        logger.info("=====28");
        DeferredResult<String> result = new DeferredResult<>();//异步生成返回值
        logger.info("=====29");
        deferredResultHolder.getMap().put(orderNumber, result);
        logger.info("等待返回结果");
        return result;
    }

}
