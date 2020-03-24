package com.kelab.api.controller;

import com.kelab.api.controller.base.BaseController;
import com.kelab.api.service.UserCenterService;
import com.kelab.info.base.JsonAndModel;
import com.kelab.info.base.query.BaseQuery;
import com.kelab.info.base.query.PageQuery;
import com.kelab.info.usercenter.info.*;
import com.kelab.info.usercenter.query.NewsQuery;
import com.kelab.info.usercenter.query.NewsRollQuery;
import com.kelab.info.usercenter.query.ScrollPictureQuery;
import com.kelab.info.usercenter.query.UserQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
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
    public JsonAndModel delete(String ids) {
        Map<String, Object> param = new HashMap<>();
        param.put("ids", ids);
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

    @ApiOperation(value = "更新滚动图片")
    @PutMapping("/scrollPicture.do")
    public JsonAndModel updateScrollPicture(@RequestBody ScrollPictureInfo record) {
        return userCenterService.updateScrollPicture(buildParam(), record);
    }

    @ApiOperation(value = "添加滚动图片")
    @PostMapping("/scrollPicture.do")
    public JsonAndModel saveScrollPicture(@RequestBody ScrollPictureInfo record) {
        return userCenterService.saveScrollPicture(buildParam(), record);
    }

    @ApiOperation(value = "删除滚动图片")
    @DeleteMapping("/scrollPicture.do")
    public JsonAndModel deleteScrollPicture(String ids) {
        Map<String, Object> param = new HashMap<>();
        param.put("ids", ids);
        return userCenterService.deleteScrollPicture(buildParam(param));
    }

    @ApiOperation(value = "查询新闻")
    @GetMapping("/news.do")
    public JsonAndModel queryNewsPage(NewsQuery query) {
        return userCenterService.queryNewsPage(buildParam(query));
    }

    @ApiOperation(value = "更新新闻")
    @PutMapping("/news.do")
    public JsonAndModel updateNews(@RequestBody NewsInfo record) {
        return userCenterService.updateNews(buildParam(), record);
    }

    @ApiOperation(value = "添加新闻")
    @PostMapping("/news.do")
    public JsonAndModel saveNews(@RequestBody NewsInfo record) {
        return userCenterService.saveNews(buildParam(), record);
    }

    @ApiOperation(value = "删除新闻")
    @DeleteMapping("/news.do")
    public JsonAndModel deleteNews(String ids) {
        Map<String, Object> param = new HashMap<>();
        param.put("ids", ids);
        return userCenterService.deleteNews(buildParam(param));
    }

    @ApiOperation(value = "访问量+1")
    @PutMapping("/news/viewnum.do")
    public JsonAndModel updateNewsViewNum(Integer id) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        return userCenterService.updateNewsViewNum(buildParam(param));
    }

    @ApiOperation(value = "查询通知")
    @GetMapping("/newsroll.do")
    public JsonAndModel queryNewsRollPage(NewsRollQuery query) {
        return userCenterService.queryNewsRollPage(buildParam(query));
    }

    @ApiOperation(value = "更新通知")
    @PutMapping("/newsroll.do")
    public JsonAndModel updateNewsRoll(@RequestBody NewsRollInfo record) {
        return userCenterService.updateNewsRoll(buildParam(), record);
    }

    @ApiOperation(value = "添加通知")
    @PostMapping("/newsroll.do")
    public JsonAndModel saveNewsRoll(@RequestBody NewsRollInfo record) {
        return userCenterService.saveNewsRoll(buildParam(), record);
    }

    @ApiOperation(value = "删除通知")
    @DeleteMapping("/newsroll.do")
    public JsonAndModel deleteNewsRoll(String ids) {
        Map<String, Object> param = new HashMap<>();
        param.put("ids", ids);
        return userCenterService.deleteNewsRoll(buildParam(param));
    }

    @ApiOperation(value = "查询关于")
    @GetMapping("/about.do")
    public JsonAndModel queryAboutPage(BaseQuery query) {
        return userCenterService.queryAboutPage(buildParam(query));
    }

    @ApiOperation(value = "更新关于")
    @PutMapping("/about.do")
    public JsonAndModel updateAbout(@RequestBody AboutInfo record) {
        return userCenterService.updateAbout(buildParam(), record);
    }

    @ApiOperation(value = "添加关于")
    @PostMapping("/about.do")
    public JsonAndModel saveAbout(@RequestBody AboutInfo record) {
        return userCenterService.saveAbout(buildParam(), record);
    }

    @ApiOperation(value = "删除关于")
    @DeleteMapping("/about.do")
    public JsonAndModel deleteAbout(String ids) {
        Map<String, Object> param = new HashMap<>();
        param.put("ids", ids);
        return userCenterService.deleteAbout(buildParam(param));
    }

    @ApiOperation(value = "更新关于的顺序")
    @PutMapping("/about/order.do")
    public JsonAndModel changeAboutOrder(@RequestBody ChangeOrderInfo record) {
        return userCenterService.changeAboutOrder(buildParam(), record);
    }

    @ApiOperation(value = "查询竞赛")
    @GetMapping("/competition.do")
    public JsonAndModel queryCompetitionPage(BaseQuery query) {
        return userCenterService.queryCompetitionPage(buildParam(query));
    }

    @ApiOperation(value = "更新竞赛")
    @PutMapping("/competition.do")
    public JsonAndModel updateCompetition(@RequestBody CompetitionInfo record) {
        return userCenterService.updateCompetition(buildParam(), record);
    }

    @ApiOperation(value = "添加竞赛")
    @PostMapping("/competition.do")
    public JsonAndModel saveCompetition(@RequestBody CompetitionInfo record) {
        return userCenterService.saveCompetition(buildParam(), record);
    }

    @ApiOperation(value = "删除竞赛")
    @DeleteMapping("/competition.do")
    public JsonAndModel deleteCompetition(String ids) {
        Map<String, Object> param = new HashMap<>();
        param.put("ids", ids);
        return userCenterService.deleteCompetition(buildParam(param));
    }
}
