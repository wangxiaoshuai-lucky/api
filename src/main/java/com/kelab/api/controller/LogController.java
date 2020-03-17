package com.kelab.api.controller;

import cn.wzy.verifyUtils.annotation.Verify;
import com.kelab.api.constant.enums.CacheBizName;
import com.kelab.api.controller.base.BaseController;
import com.kelab.api.dal.redis.RedisCache;
import com.kelab.info.base.JsonAndModel;
import com.kelab.info.base.constant.StatusMsgConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController extends BaseController {


    private final RedisCache redisCache;

    public LogController(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    /**
     * 获取日志
     */
    @GetMapping("/log.do")
    @Verify(notNull = "logId")
    public JsonAndModel getLogMsg(String logId) {
        return JsonAndModel.builder(StatusMsgConstant.SUCCESS)
                .data(redisCache.lRange(CacheBizName.LOG, logId, 0, -1))
                .build();
    }
}
