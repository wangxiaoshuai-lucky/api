package com.kelab.api.service;

import com.kelab.info.base.JsonAndModel;
import com.kelab.info.usercenter.info.*;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 查询新闻
     */
    @GetMapping("/news.do")
    JsonAndModel queryNewsPage(@RequestParam Map<String, Object> param);

    /**
     * 更新新闻
     */
    @PutMapping("/news.do")
    JsonAndModel updateNews(@RequestParam Map<String, Object> param,
                            @RequestBody NewsInfo record);

    /**
     * 添加新闻
     */
    @PostMapping("/news.do")
    JsonAndModel saveNews(@RequestParam Map<String, Object> param,
                          @RequestBody NewsInfo record);

    /**
     * 删除新闻
     */
    @DeleteMapping("/news.do")
    JsonAndModel deleteNews(@RequestParam Map<String, Object> param);

    /**
     * 访问量+1
     */
    @PutMapping("/news/viewnum.do")
    JsonAndModel updateNewsViewNum(@RequestParam Map<String, Object> param);

    /**
     * 查询新闻
     */
    @GetMapping("/newsroll.do")
    JsonAndModel queryNewsRollPage(@RequestParam Map<String, Object> param);

    /**
     * 更新通知
     */
    @PutMapping("/newsroll.do")
    JsonAndModel updateNewsRoll(@RequestParam Map<String, Object> param,
                                @RequestBody NewsRollInfo record);

    /**
     * 添加通知
     */
    @PostMapping("/newsroll.do")
    JsonAndModel saveNewsRoll(@RequestParam Map<String, Object> param,
                              @RequestBody NewsRollInfo record);

    /**
     * 删除通知
     */
    @DeleteMapping("/newsroll.do")
    JsonAndModel deleteNewsRoll(@RequestParam Map<String, Object> param);

    /**
     * 查询关于
     */
    @GetMapping("/about.do")
    JsonAndModel queryAboutPage(@RequestParam Map<String, Object> param);

    /**
     * 更新关于
     */
    @PutMapping("/about.do")
    JsonAndModel updateAbout(@RequestParam Map<String, Object> param,
                             @RequestBody AboutInfo record);

    /**
     * 添加关于
     */
    @PostMapping("/about.do")
    JsonAndModel saveAbout(@RequestParam Map<String, Object> param,
                           @RequestBody AboutInfo record);

    /**
     * 删除关于
     */
    @DeleteMapping("/about.do")
    JsonAndModel deleteAbout(@RequestParam Map<String, Object> param);

    /**
     * 更新关于的顺序
     */
    @PutMapping("/about/order.do")
    JsonAndModel changeAboutOrder(@RequestParam Map<String, Object> param,
                                  @RequestBody ChangeOrderInfo record);

    /**
     * 查询竞赛
     */
    @GetMapping("/competition.do")
    JsonAndModel queryCompetitionPage(@RequestParam Map<String, Object> param);

    /**
     * 更新竞赛
     */
    @PutMapping("/competition.do")
    JsonAndModel updateCompetition(@RequestParam Map<String, Object> param,
                                   @RequestBody CompetitionInfo record);

    /**
     * 添加竞赛
     */
    @PostMapping("/competition.do")
    JsonAndModel saveCompetition(@RequestParam Map<String, Object> param,
                                 @RequestBody CompetitionInfo record);

    /**
     * 删除竞赛
     */
    @DeleteMapping("/competition.do")
    JsonAndModel deleteCompetition(@RequestParam Map<String, Object> param);

    /**
     * 查询竞赛队伍
     */
    @GetMapping("/competition/team.do")
    JsonAndModel queryCompetitionTeamPage(@RequestParam Map<String, Object> param);

    /**
     * 添加团队
     */
    @PostMapping("/competition/team.do")
    JsonAndModel saveTeam(@RequestParam Map<String, Object> param,
                          @RequestBody CompetitionTeamStudentInfo record);

    /**
     * 审核团队
     */
    @PutMapping("/competition/team.do")
    JsonAndModel updateTeam(@RequestParam Map<String, Object> param,
                            @RequestBody CompetitionTeamInfo teamInfo);

    /**
     * 登录，ac,submit 走势图
     */
    @GetMapping("/online.do")
    JsonAndModel queryOnlineStatistic(@RequestParam Map<String, Object> param);

    /**
     * 导出比赛团队信息
     */
    @GetMapping("/competition/export.do")
    Response downloadTeamMessage(@RequestParam Map<String, Object> param);
}
