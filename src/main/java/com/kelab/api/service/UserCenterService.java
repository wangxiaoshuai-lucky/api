package com.kelab.api.service;

import com.kelab.info.base.JsonAndModel;
import com.kelab.info.base.constant.StatusMsgConstant;
import com.kelab.info.context.Context;
import com.kelab.info.usercenter.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "service-usercenter")
@RequestMapping("/usercenter")
public interface UserCenterService {

    /**
     * 获取验证码
     */
    @GetMapping("/pic.do")
    JsonAndModel verifyPic(@RequestParam Map<String, Object> param);

    /**
     * 注册接口
     */
    @PostMapping("/user.do")
    JsonAndModel register(@RequestParam Map<String, Object> context,
                          @RequestBody UserInfo userInfo);

    /**
     * 登录接口
     */
    @GetMapping("/user/signin.do")
    JsonAndModel login(@RequestParam Map<String, Object> param);

    /**
     * 用户总数接口
     */
    @GetMapping("/user/total.do")
    JsonAndModel countTotalUser(@RequestParam Map<String, Object> param);

    /**
     * 找回密码
     */
    @GetMapping("/user/resetPasswd.do")
    JsonAndModel resetPwdEmail(@RequestParam Map<String, Object> param);

    /**
     * 修改密码
     */
    @PutMapping("/user/resetPasswd.do")
    JsonAndModel resetPwd(@RequestParam Map<String, Object> param,
                          @RequestBody String newPassword);

    /**
     * 提交排行榜
     */
    @GetMapping("/user/submit/statistic.do")
    JsonAndModel submitStatistic(@RequestParam Map<String, Object> param);

    /**
     * 查询用户
     */
    @GetMapping("/user.do")
    JsonAndModel queryPage(@RequestParam Map<String, Object> param);

    /**
     * 更新用户
     */
    @PutMapping("/user.do")
    JsonAndModel update(@RequestParam Map<String, Object> param, @RequestBody UserInfo userInfo);

    /**
     * 删除用户
     */
    @DeleteMapping("/user.do")
    JsonAndModel delete(@RequestParam Map<String, Object> param);

    /**
     * 在线用户列表
     */
    @GetMapping("/user/online.do")
    JsonAndModel onlineUserId(@RequestParam Map<String, Object> param);

    /**
     * 刷新 token
     */
    @GetMapping("/user/jwt/refresh.do")
    JsonAndModel refreshJwt(@RequestParam Map<String, Object> param);
}
