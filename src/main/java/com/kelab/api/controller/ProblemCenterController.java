package com.kelab.api.controller;

import cn.wzy.verifyUtils.annotation.Verify;
import com.kelab.api.controller.base.BaseController;
import com.kelab.api.service.ProblemCenterService;
import com.kelab.info.base.JsonAndModel;
import com.kelab.info.base.constant.StatusMsgConstant;
import com.kelab.info.context.Context;
import com.kelab.info.problemcenter.info.ProblemInfo;
import com.kelab.info.problemcenter.info.ProblemTagsInfo;
import com.kelab.info.problemcenter.info.ProblemUserMarkInfo;
import com.kelab.info.problemcenter.query.ProblemQuery;
import com.kelab.info.problemcenter.query.ProblemTagsQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "题目相关Controller", tags = "problem-center")
public class ProblemCenterController extends BaseController {

    private ProblemCenterService problemCenterService;

    public ProblemCenterController(ProblemCenterService problemCenterService) {
        this.problemCenterService = problemCenterService;
    }

    @ApiOperation(value = "获取ac或者挑战情况")
    @GetMapping("/user/problem/challenging.do")
    public JsonAndModel queryAcAndChallenging(Integer userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        return problemCenterService.queryAcAndChallenging(buildParam(param));
    }

    @ApiOperation(value = "获取题目收藏集")
    @GetMapping("/user/problem/collect.do")
    public JsonAndModel collect(Integer userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        return problemCenterService.collect(buildParam(param));
    }

    @ApiOperation(value = "添加或者取消收藏")
    @PostMapping("/user/problem/collect.do")
    public JsonAndModel saveOrDeleteProblemCollect(@RequestBody ProblemUserMarkInfo problemUserMark) {
        return problemCenterService.saveOrDeleteProblemCollect(buildParam(), problemUserMark);
    }

    @ApiOperation(value = "获取题目信息")
    @GetMapping("/problem.do")
    public JsonAndModel queryPage(ProblemQuery query) {
        return problemCenterService.queryPage(buildParam(query));
    }

    @ApiOperation(value = "添加题目内容")
    @PostMapping("/problem.do")
    public JsonAndModel save(@RequestBody ProblemInfo problemInfo) {
        return problemCenterService.save(buildParam(), problemInfo);
    }

    @ApiOperation(value = "删除题目")
    @DeleteMapping("/problem.do")
    public JsonAndModel delete(String pids) {
        Map<String, Object> param = new HashMap<>();
        param.put("pids", pids);
        return problemCenterService.delete(buildParam(param));
    }

    @ApiOperation(value = "修改题目")
    @PutMapping("/problem.do")
    public JsonAndModel update(@RequestBody ProblemInfo record) {
        return problemCenterService.update(buildParam(), record);
    }

    @ApiOperation(value = "题目总数")
    @GetMapping("/problem/count.do")
    public JsonAndModel problemTotal() {
        return problemCenterService.problemTotal(buildParam());
    }

    @ApiOperation(value = "查询来源列表")
    @GetMapping("/problem/source.do")
    public JsonAndModel querySource(Integer limit) {
        Map<String, Object> param = new HashMap<>();
        param.put("limit", limit);
        return problemCenterService.querySource(buildParam(param));
    }

    @ApiOperation(value = "获取标签信息")
    @GetMapping("/tags.do")
    public JsonAndModel queryPage(ProblemTagsQuery query) {
        return problemCenterService.queryTagsPage(buildParam(query));
    }

    @ApiOperation(value = "添加标签信息")
    @PostMapping("/tags.do")
    public JsonAndModel save(@RequestBody ProblemTagsInfo record) {
        return problemCenterService.save(buildParam(), record);
    }

    @ApiOperation(value = "修改标签信息")
    @PutMapping("/tags.do")
    public JsonAndModel update(@RequestBody ProblemTagsInfo record) {
        return problemCenterService.update(buildParam(), record);
    }

    @ApiOperation(value = "删除标签信息")
    @DeleteMapping("/tags.do")
    public JsonAndModel deleteTags(String ids) {
        Map<String, Object> param = new HashMap<>();
        param.put("ids", ids);
        return problemCenterService.deleteTags(buildParam(param));
    }

}
