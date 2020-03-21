package com.kelab.api.service;

import cn.wzy.verifyUtils.annotation.Verify;
import com.kelab.info.base.JsonAndModel;
import com.kelab.info.base.constant.StatusMsgConstant;
import com.kelab.info.context.Context;
import com.kelab.info.problemcenter.info.ProblemInfo;
import com.kelab.info.problemcenter.info.ProblemSubmitRecordInfo;
import com.kelab.info.problemcenter.info.ProblemTagsInfo;
import com.kelab.info.problemcenter.info.ProblemUserMarkInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "service-problemcenter")
@RequestMapping("/problemcenter")
public interface ProblemCenterService {

    /**
     * 获取用户的做题记录
     */
    @GetMapping("/user/problem/challenging.do")
    JsonAndModel queryAcAndChallenging(@RequestParam Map<String, Object> param);


    /**
     * 获取用户的做题记录
     */
    @GetMapping("/user/problem/collect.do")
    JsonAndModel collect(@RequestParam Map<String, Object> param);

    /**
     * 取消/添加收藏
     */
    @PostMapping("/user/problem/collect.do")
    JsonAndModel saveOrDeleteProblemCollect(@RequestParam Map<String, Object> param,
                                            @RequestBody ProblemUserMarkInfo problemUserMark);

    /**
     * 分页查询
     */
    @GetMapping("/problem.do")
    JsonAndModel queryPage(@RequestParam Map<String, Object> param);

    /**
     * 添加题目
     */
    @PostMapping("/problem.do")
    JsonAndModel save(@RequestParam Map<String, Object> param,
                      @RequestBody ProblemInfo problemInfo);

    /**
     * 删除题目
     */
    @DeleteMapping("/problem.do")
    JsonAndModel delete(@RequestParam Map<String, Object> param);

    /**
     * 修改题目
     */
    @PutMapping("/problem.do")
    JsonAndModel update(@RequestParam Map<String, Object> param,
                        @RequestBody ProblemInfo record);

    /**
     * 题目总数
     */
    @GetMapping("/problem/count.do")
    JsonAndModel problemTotal(@RequestParam Map<String, Object> param);

    /**
     * 查询来源列表
     */
    @GetMapping("/problem/source.do")
    JsonAndModel querySource(@RequestParam Map<String, Object> param);

    /**
     * 分页查询标签
     */
    @GetMapping("/tags.do")
    JsonAndModel queryTagsPage(@RequestParam Map<String, Object> param);

    /**
     * 添加标签
     */
    @PostMapping("/tags.do")
    JsonAndModel save(@RequestParam Map<String, Object> param,
                      @RequestBody ProblemTagsInfo record);

    /**
     * 修改标签
     */
    @PutMapping("/tags.do")
    JsonAndModel update(@RequestParam Map<String, Object> param,
                        @RequestBody ProblemTagsInfo record);

    /**
     * 删除标签
     */
    @DeleteMapping("/tags.do")
    JsonAndModel deleteTags(@RequestParam Map<String, Object> param);

    /**
     * 查询提交记录
     */
    @GetMapping("/submit.do")
    JsonAndModel querySubmitRecordPage(@RequestParam Map<String, Object> param);

    /**
     * 查询总提交量
     */
    @GetMapping("/submit/count.do")
    JsonAndModel judgeCount(@RequestParam Map<String, Object> param);

    /**
     * 提交判题
     */
    @PostMapping("/submit.do")
    JsonAndModel submit(@RequestParam Map<String, Object> param,
                        @RequestBody ProblemSubmitRecordInfo record);

    /**
     * 查询提交细节：源码和错误信息
     */
    @GetMapping("/submit/detail.do")
    JsonAndModel querySubmitDetail(@RequestParam Map<String, Object> param);
}
