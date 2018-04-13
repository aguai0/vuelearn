package com.learn.seckill.web;

import com.alibaba.fastjson.JSON;
import com.learn.seckill.entity.QueryCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="test")
public class TestController {
    /** 日志记录器 */
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);



	@RequestMapping(value="/query")
	public String test(QueryCondition queryCondition){

        System.out.println("--------------test1111----------------");
        LOGGER.info(JSON.toJSONString(queryCondition));
		return "list";
	}
}
