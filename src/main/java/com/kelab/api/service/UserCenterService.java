package com.kelab.api.service;

import com.kelab.info.base.JsonAndModel;
import com.kelab.info.usercenter.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "service-usercenter")
@RequestMapping("/usercenter")
public interface UserCenterService {

    /**
     * 获取验证码
     */
    @GetMapping("/pic.do")
    JsonAndModel verifyPic(@RequestParam String logId, @RequestParam Integer operatorId);

    /**
     * 注册接口
     */
    @PostMapping("/user.do")
    JsonAndModel register(@RequestParam String logId, @RequestParam Integer operatorId,
                          @RequestBody UserInfo userInfo);

    /**
     * 登录接口
     */
    @GetMapping("/user/signin.do")
    JsonAndModel login(@RequestParam String logId, @RequestParam Integer operatorId,
                       @RequestParam String username, @RequestParam String password,
                       @RequestParam String verifyCode, @RequestParam String uuid);

    /**
     * 用户总数接口
     */
    @GetMapping("/user/total.do")
    JsonAndModel countTotalUser(@RequestParam String logId, @RequestParam Integer operatorId);

    /**
     * 找回密码
     */
    @GetMapping("/user/resetPasswd.do")
    JsonAndModel resetPwdEmail(@RequestParam String logId, @RequestParam Integer operatorId,
                               @RequestParam String username, @RequestParam String verifyCode,
                               @RequestParam String uuid);

    /**
     * 修改密码
     */
    @PutMapping("/user/resetPasswd.do")
    JsonAndModel resetPwd(@RequestParam String logId, @RequestParam Integer operatorId,
                          @RequestBody String newPassword);

    /**
     * 提交排行榜
     */
    @GetMapping("/user/submit/statistic.do")
    JsonAndModel submitStatistic(@RequestParam String logId, @RequestParam Integer operatorId,
                                 @RequestParam Integer page, @RequestParam Integer rows,
                                 @RequestParam Integer timeType);
}
