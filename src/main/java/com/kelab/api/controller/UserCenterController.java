package com.kelab.api.controller;

import com.kelab.api.controller.base.BaseController;
import com.kelab.api.service.UserCenterService;
import com.kelab.info.base.JsonAndModel;
import com.kelab.info.base.query.PageQuery;
import com.kelab.info.usercenter.info.UserInfo;
import com.kelab.info.usercenter.query.UserQuery;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class UserCenterController extends BaseController {

    private final UserCenterService userCenterService;

    public UserCenterController(UserCenterService userCenterService) {
        this.userCenterService = userCenterService;
    }

    @GetMapping("/pic.do")
    public JsonAndModel verifyPic() {
        return userCenterService.verifyPic(buildParam());
    }

    //
    @PostMapping("/user.do")
    public JsonAndModel register(@RequestBody UserInfo userInfo) {
        return userCenterService.register(buildParam(), userInfo);
    }

    //
    @GetMapping("/user/signin.do")
    public JsonAndModel login(String username, String password, String verifyCode, String uuid) {
        Map<String, Object> param = new HashMap<>();
        param.put("password", password);
        param.put("username", username);
        param.put("verifyCode", verifyCode);
        param.put("uuid", uuid);
        return userCenterService.login(buildParam(param));
    }

    @GetMapping("/user/total.do")
    public JsonAndModel countTotalUser() {
        return userCenterService.countTotalUser(buildParam());
    }

    @GetMapping("/user/resetPasswd.do")
    public JsonAndModel resetPwd(String username, String verifyCode, String uuid) {
        Map<String, Object> param = new HashMap<>();
        param.put("username", username);
        param.put("verifyCode", verifyCode);
        param.put("uuid", uuid);
        return userCenterService.resetPwdEmail(buildParam(param));
    }

    @PutMapping("/user/resetPasswd.do")
    public JsonAndModel resetPwd(@RequestBody String newPassword) {
        return userCenterService.resetPwd(buildParam(), newPassword);
    }

    @GetMapping("/user/submit/statistic.do")
    public JsonAndModel submitStatistic(PageQuery query, Integer timeType) {
        Map<String, Object> param = new HashMap<>();
        param.put("timeType", timeType);
        return userCenterService.submitStatistic(buildParam(query, param));
    }

    @GetMapping("/user.do")
    public JsonAndModel queryPage(UserQuery query) {
        return userCenterService.queryPage(buildParam(query));
    }

    @PutMapping("/user.do")
    public JsonAndModel update(@RequestBody UserInfo userInfo) {
        return userCenterService.update(buildParam(), userInfo);
    }

    @DeleteMapping("/user.do")
    public JsonAndModel delete(String uiids) {
        Map<String, Object> param = new HashMap<>();
        param.put("uiids", uiids);
        return userCenterService.delete(buildParam(param));
    }

    @GetMapping("/user/online.do")
    public JsonAndModel onlineUserId() {
        return userCenterService.onlineUserId(buildParam());
    }

    @GetMapping("/user/jwt/refresh.do")
    public JsonAndModel refreshJwt() {
        return userCenterService.refreshJwt(buildParam());
    }
}
