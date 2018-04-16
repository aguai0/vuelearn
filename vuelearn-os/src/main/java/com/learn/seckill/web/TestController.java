package com.learn.seckill.web;

import com.alibaba.fastjson.JSON;
import com.learn.base.web.BaseRemoteController;
import com.learn.seckill.entity.QueryCondition;
import com.learn.seckill.entity.TestVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/test")
public class TestController extends BaseRemoteController {
    /** 日志记录器 */
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);



	@RequestMapping(value="/query", method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> test(QueryCondition queryCondition){

        System.out.println("--------------test1111----------------");
        LOGGER.info(JSON.toJSONString(queryCondition));
        List<TestVo> list = new ArrayList<TestVo>();
        TestVo vo = new TestVo();
        vo.setName("test1");
        vo.setStartTime(new Date());
        vo.setEndTime(new Date());
        TestVo vo2 = new TestVo();
        vo2.setName("test2");
        vo2.setStartTime(new Date());
        vo2.setEndTime(new Date());
        list.add(vo);
        list.add(vo2);
		return successList(list,queryCondition.getCurrentPage());
	}
}
