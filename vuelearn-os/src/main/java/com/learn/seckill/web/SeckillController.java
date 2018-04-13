package com.learn.seckill.web;

import com.learn.seckill.dto.Exposer;
import com.learn.seckill.dto.Result;
import com.learn.seckill.dto.SeckillExcution;
import com.learn.seckill.entity.Seckill;
import com.learn.seckill.enums.SeckillStateEnum;
import com.learn.seckill.exception.RepeatKillException;
import com.learn.seckill.exception.SeckillCloseException;
import com.learn.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by aguai on 2017-07-13.
 */
@Controller
@RequestMapping(value = "/seckill")
public class SeckillController {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Seckill> list = seckillService.queryAllSeckill();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.queryById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute(seckill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        Result<Exposer> result = null;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);

            result = new Result<Exposer>(true, exposer);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = new Result<Exposer>(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result execute(@PathVariable("seckillId") Long seckillId,
                          @PathVariable("md5") String md5,
                          @CookieValue(value = "userPhone") Long phone) {
        if (StringUtils.isEmpty(phone)) {
            return new Result(false, "未注册");
        }
        try {
            SeckillExcution seckillExcution = seckillService.excuteSeckill(seckillId, phone, md5);
            return new Result(true, seckillExcution);
        } catch (RepeatKillException e) {
            SeckillExcution excution = new SeckillExcution(seckillId, SeckillStateEnum.getStateOf(-1));//REPEAT_KILL
            return new Result(true, excution);
        } catch (SeckillCloseException e) {
            SeckillExcution excution = new SeckillExcution(seckillId, SeckillStateEnum.getStateOf(2));//END
            return new Result(true, excution);
        } catch (Exception e) {
            SeckillExcution excution = new SeckillExcution(seckillId, SeckillStateEnum.getStateOf(-2));//INNER_ERROR
            return new Result(true, excution);
        }
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public Result<Long> nowTime() {
        Date date = new Date();
        return new Result(true, date);
    }
}
