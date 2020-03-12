package com.kelab.api.controller;

import com.kelab.api.controller.base.BaseController;
import com.kelab.api.service.UserCenterService;
import com.kelab.info.base.JsonAndModel;
import com.kelab.info.usercenter.UserInfo;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserCenterController extends BaseController {

    private final UserCenterService userCenterService;

    public UserCenterController(UserCenterService userCenterService) {
        this.userCenterService = userCenterService;
    }

    @GetMapping("/pic.do")
    public JsonAndModel verifyPic() {
        return userCenterService.verifyPic(getLogId(), getOperatorId());
    }

    @PostMapping("/user.do")
    public JsonAndModel register(@RequestBody UserInfo userInfo) {
        return userCenterService.register(getLogId(), getOperatorId(), userInfo);
    }

    @GetMapping("/user/signin.do")
    public JsonAndModel login(String username, String password, String verifyCode, String uuid) {
        return userCenterService.login(getLogId(), getOperatorId(), username, password, verifyCode, uuid);
    }

    @GetMapping("/user/total.do")
    public JsonAndModel countTotalUser() {
        return userCenterService.countTotalUser(getLogId(), getOperatorId());
    }

    @GetMapping("/user/resetPasswd.do")
    public JsonAndModel resetPwd(String username, String verifyCode, String uuid) {
        return userCenterService.resetPwdEmail(getLogId(), getOperatorId(), username, verifyCode, uuid);
    }

    @PutMapping("/user/resetPasswd.do")
    public JsonAndModel resetPwd(@RequestBody String newPassword) {
        return userCenterService.resetPwd(getLogId(), getOperatorId(), newPassword);
    }

    @GetMapping("/user/submit/statistic.do")
    public JsonAndModel submitStatistic(Integer page, Integer rows, Integer timeType) {
        return userCenterService.submitStatistic(getLogId(), getOperatorId(), page, rows, timeType);
    }
}
