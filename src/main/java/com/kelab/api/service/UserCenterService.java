package com.kelab.api.service;

import cn.wzy.verifyUtils.annotation.Verify;
import com.kelab.info.base.JsonAndModel;
import com.kelab.info.base.constant.StatusMsgConstant;
import com.kelab.info.context.Context;
import com.kelab.info.usercenter.info.ScrollPictureInfo;
import com.kelab.info.usercenter.info.UserInfo;
import com.kelab.info.usercenter.query.ScrollPictureQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
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
     * 今日AC/Submit量
     */
    @GetMapping("/submit/todayCount.do")
    JsonAndModel todayCount(@RequestParam Map<String, Object> param);

    /**
     * 在线总人数
     */
    @GetMapping("/user/online/total.do")
    JsonAndModel onlineCount(@RequestParam Map<String, Object> param);

    /**
     * 刷新 token
     */
    @GetMapping("/user/jwt/refresh.do")
    JsonAndModel refreshJwt(@RequestParam Map<String, Object> param);

    /**
     * 文件上传,返回下载连接
     */
    @PostMapping(value = "/file.do", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    JsonAndModel upload(@RequestParam Map<String, Object> param,
                        @RequestBody MultipartFile file);

    /**
     * 查询滚动图片
     */
    @GetMapping("/scrollPicture.do")
    JsonAndModel queryScrollPicture(@RequestParam Map<String, Object> param);

    /**
     * 更新滚动图片
     */
    @PutMapping("/scrollPicture.do")
    JsonAndModel updateScrollPicture(@RequestParam Map<String, Object> param,
                                     @RequestBody ScrollPictureInfo record);

    /**
     * 添加滚动图片
     */
    @PostMapping("/scrollPicture.do")
    JsonAndModel saveScrollPicture(@RequestParam Map<String, Object> param,
                                   @RequestBody ScrollPictureInfo record);

    /**
     * 删除滚动图片
     */
    @DeleteMapping("/scrollPicture.do")
    JsonAndModel deleteScrollPicture(@RequestParam Map<String, Object> param);
}
