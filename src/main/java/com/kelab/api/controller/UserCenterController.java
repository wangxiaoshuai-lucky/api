package com.kelab.api.controller;

import com.kelab.api.controller.base.BaseController;
import com.kelab.api.service.UserCenterService;
import com.kelab.info.base.JsonAndModel;
import com.kelab.info.context.Context;
import com.kelab.info.usercenter.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserCenterController extends BaseController {

    private final UserCenterService userCenterService;

    public UserCenterController(UserCenterService userCenterService) {
        this.userCenterService = userCenterService;
    }

    @GetMapping("/pic.do")
    public JsonAndModel verifyPic() {
        Context context = getContext();
        return userCenterService.verifyPic(context.getLogId(), context.getOperatorId());
    }

    @PostMapping("/user.do")
    public JsonAndModel register(@RequestBody UserInfo userInfo) {
        Context context = getContext();
        return userCenterService.register(context.getLogId(), context.getOperatorId(), userInfo);
    }
}
