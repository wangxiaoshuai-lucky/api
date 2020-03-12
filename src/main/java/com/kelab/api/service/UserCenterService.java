package com.kelab.api.service;

import com.kelab.info.base.JsonAndModel;
import com.kelab.info.context.Context;
import com.kelab.info.usercenter.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "service-usercenter")
@RequestMapping("/usercenter")
public interface UserCenterService {

    @GetMapping("/pic.do")
    JsonAndModel verifyPic(@RequestParam String logId, @RequestParam Integer operatorId);

    @PostMapping("/user.do")
    JsonAndModel register(@RequestParam String logId, @RequestParam Integer operatorId, @RequestBody UserInfo userInfo);
}
