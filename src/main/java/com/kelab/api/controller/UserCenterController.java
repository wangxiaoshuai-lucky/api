package com.kelab.api.controller;

import cn.wzy.verifyUtils.annotation.Verify;
import com.kelab.api.controller.base.BaseController;
import com.kelab.api.service.UserCenterService;
import com.kelab.info.base.JsonAndModel;
import com.kelab.info.base.constant.StatusMsgConstant;
import com.kelab.info.base.query.PageQuery;
import com.kelab.info.context.Context;
import com.kelab.info.usercenter.info.ScrollPictureInfo;
import com.kelab.info.usercenter.info.UserInfo;
import com.kelab.info.usercenter.query.ScrollPictureQuery;
import com.kelab.info.usercenter.query.UserQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Api(value = "用户相关Controller", tags = "user-center")
public class UserCenterController extends BaseController {

    private final UserCenterService userCenterService;

    public UserCenterController(UserCenterService userCenterService) {
        this.userCenterService = userCenterService;
    }

    @ApiOperation(value = "获取验证码")
    @GetMapping("/pic.do")
    public JsonAndModel verifyPic(Integer w, Integer h) {
        Map<String, Object> param = new HashMap<>();
        param.put("w", w);
        param.put("h", h);
        return userCenterService.verifyPic(buildParam(param));
    }

    @ApiOperation(value = "注册用户")
    @PostMapping("/user.do")
    public JsonAndModel register(@RequestBody UserInfo userInfo) {
        return userCenterService.register(buildParam(), userInfo);
    }

    @ApiOperation(value = "用户登录")
    @GetMapping("/user/signin.do")
    public JsonAndModel login(String username, String password, String verifyCode, String uuid) {
        Map<String, Object> param = new HashMap<>();
        param.put("password", password);
        param.put("username", username);
        param.put("verifyCode", verifyCode);
        param.put("uuid", uuid);
        return userCenterService.login(buildParam(param));
    }

    @ApiOperation(value = "查询所有用户个数")
    @GetMapping("/user/total.do")
    public JsonAndModel countTotalUser() {
        return userCenterService.countTotalUser(buildParam());
    }

    @ApiOperation(value = "忘记密码发邮件")
    @GetMapping("/user/resetPasswd.do")
    public JsonAndModel resetPwd(String username, String verifyCode, String uuid) {
        Map<String, Object> param = new HashMap<>();
        param.put("username", username);
        param.put("verifyCode", verifyCode);
        param.put("uuid", uuid);
        return userCenterService.resetPwdEmail(buildParam(param));
    }

    @ApiOperation(value = "重置密码")
    @PutMapping("/user/resetPasswd.do")
    public JsonAndModel resetPwd(@RequestBody String newPassword) {
        return userCenterService.resetPwd(buildParam(), newPassword);
    }

    @ApiOperation(value = "日 周 月榜单查询")
    @GetMapping("/user/submit/statistic.do")
    public JsonAndModel submitStatistic(PageQuery query, Integer timeType) {
        Map<String, Object> param = new HashMap<>();
        param.put("timeType", timeType);
        return userCenterService.submitStatistic(buildParam(query, param));
    }

    @ApiOperation(value = "查询用户信息")
    @GetMapping("/user.do")
    public JsonAndModel queryPage(UserQuery query) {
        return userCenterService.queryPage(buildParam(query));
    }

    @ApiOperation(value = "更新用户信息")
    @PutMapping("/user.do")
    public JsonAndModel update(@RequestBody UserInfo userInfo) {
        return userCenterService.update(buildParam(), userInfo);
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/user.do")
    public JsonAndModel delete(String uiids) {
        Map<String, Object> param = new HashMap<>();
        param.put("uiids", uiids);
        return userCenterService.delete(buildParam(param));
    }

    @ApiOperation(value = "获取在线用户")
    @GetMapping("/user/online.do")
    public JsonAndModel onlineUserId() {
        return userCenterService.onlineUserId(buildParam());
    }

    @ApiOperation(value = "今日AC/Submit量")
    @GetMapping("/submit/todayCount.do")
    public JsonAndModel submitStatistic() {
        return userCenterService.todayCount(buildParam());
    }

    @ApiOperation(value = "在线总人数")
    @GetMapping("/user/online/total.do")
    public JsonAndModel onlineCount() {
        return userCenterService.onlineCount(buildParam());
    }

    @ApiOperation(value = "刷新token")
    @GetMapping("/user/jwt/refresh.do")
    public JsonAndModel refreshJwt() {
        return userCenterService.refreshJwt(buildParam());
    }

    @ApiOperation(value = "文件上传,返回下载连接")
    @PostMapping(value = "/file.do", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public JsonAndModel upload(@RequestBody MultipartFile file) {
        return userCenterService.upload(buildParam(), file);
    }

    @ApiOperation(value = "查询滚动图片")
    @GetMapping("/scrollPicture.do")
    public JsonAndModel queryScrollPicture(ScrollPictureQuery query) {
        return userCenterService.queryScrollPicture(buildParam(query));
    }

    /**
     * 更新滚动图片
     */
    @ApiOperation(value = "更新滚动图片")
    @PutMapping("/scrollPicture.do")
    public JsonAndModel updateScrollPicture(@RequestBody ScrollPictureInfo record) {
        return userCenterService.updateScrollPicture(buildParam(), record);
    }

    /**
     * 添加滚动图片
     */
    @ApiOperation(value = "添加滚动图片")
    @PostMapping("/scrollPicture.do")
    public JsonAndModel saveScrollPicture(@RequestBody ScrollPictureInfo record) {
        return userCenterService.saveScrollPicture(buildParam(),record);
    }

    /**
     * 删除滚动图片
     */
    @ApiOperation(value = "删除滚动图片")
    @DeleteMapping("/scrollPicture.do")
    public JsonAndModel deleteScrollPicture(String ids) {
        Map<String, Object> param = new HashMap<>();
        param.put("ids", ids);
        return userCenterService.deleteScrollPicture(buildParam(param));
    }
}
