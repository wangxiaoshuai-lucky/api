package com.kelab.api.controller;

import com.kelab.api.controller.base.BaseController;
import com.kelab.api.service.ProblemCenterService;
import com.kelab.info.base.JsonAndModel;
import com.kelab.info.problemcenter.info.*;
import com.kelab.info.problemcenter.query.ProblemNoteQuery;
import com.kelab.info.problemcenter.query.ProblemQuery;
import com.kelab.info.problemcenter.query.ProblemSubmitRecordQuery;
import com.kelab.info.problemcenter.query.ProblemTagsQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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
        return problemCenterService.queryAcAndChallenging(buildParam().param("userId", userId));
    }

    @ApiOperation(value = "获取题目收藏集")
    @GetMapping("/user/problem/collect.do")
    public JsonAndModel collect() {
        return problemCenterService.collect(buildParam());
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
    public JsonAndModel delete(String ids) {
        return problemCenterService.delete(buildParam().param("ids", ids));
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
        return problemCenterService.querySource(buildParam().param("limit", limit));
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
        return problemCenterService.deleteTags(buildParam().param("ids", ids));
    }

    @ApiOperation(value = "查询提交记录")
    @GetMapping("/submit.do")
    public JsonAndModel queryPage(ProblemSubmitRecordQuery query) {
        return problemCenterService.querySubmitRecordPage(buildParam(query));
    }

    @ApiOperation(value = "查询总提交量")
    @GetMapping("/submit/count.do")
    public JsonAndModel judgeCount() {
        return problemCenterService.judgeCount(buildParam());
    }

    @ApiOperation(value = "提交判题")
    @PostMapping("/submit.do")
    public JsonAndModel submit(@RequestBody ProblemSubmitRecordInfo record) {
        return problemCenterService.submit(buildParam(), record);
    }

    @ApiOperation(value = "查询提交细节：源码和错误信息")
    @GetMapping("/submit/detail.do")
    public JsonAndModel querySubmitDetail(Integer submitId) {
        return problemCenterService.querySubmitDetail(buildParam().param("submitId", submitId));
    }

    @ApiOperation(value = "查询里程碑")
    @GetMapping("/user/submit/milestone.do")
    public JsonAndModel queryMilestone() {
        return problemCenterService.queryMilestone(buildParam());
    }

    @ApiOperation(value = "一周内提交次数")
    @GetMapping("/submit/static.do")
    public JsonAndModel queryStatic(Integer userId) {
        return problemCenterService.queryStatic(buildParam().param("userId", userId));
    }

    @ApiOperation(value = "分页查询笔记")
    @GetMapping("/problem/note.do")
    public JsonAndModel queryPage(ProblemNoteQuery query) {
        return problemCenterService.queryNotePage(buildParam(query));
    }

    @ApiOperation(value = "添加笔记，如果有则更新")
    @PostMapping("/problem/note.do")
    public JsonAndModel save(@RequestBody ProblemNoteInfo record) {
        return problemCenterService.save(buildParam(), record);
    }

    @ApiOperation(value = "修改笔记")
    @PutMapping("/problem/note.do")
    public JsonAndModel update(@RequestBody ProblemNoteInfo record) {
        return problemCenterService.update(buildParam(), record);
    }

    @ApiOperation(value = "删除笔记")
    @DeleteMapping("/problem/note.do")
    public JsonAndModel deleteNotes(String ids) {
        return problemCenterService.deleteNotes(buildParam().param("ids", ids));
    }

    @ApiOperation(value = "查询段位")
    @GetMapping("/level.do")
    public JsonAndModel queryAllLevel() {
        return problemCenterService.queryAllLevel(buildParam());
    }

    @ApiOperation(value = "添加段位")
    @PostMapping("/level.do")
    public JsonAndModel saveLevel(@RequestBody LevelInfo record) {
        return problemCenterService.saveLevel(buildParam(), record);
    }

    @ApiOperation(value = "修改段位")
    @PutMapping("/level.do")
    public JsonAndModel updateLevel(@RequestBody LevelInfo record) {
        return problemCenterService.updateLevel(buildParam(), record);
    }

    @ApiOperation(value = "删除段位")
    @DeleteMapping("/level.do")
    public JsonAndModel deleteLevel(String ids) {
        return problemCenterService.deleteLevel(buildParam().param("ids", ids));
    }

    @ApiOperation(value = "查看某个段位的题目-管理员端")
    @GetMapping("/queryProblems.do")
    public JsonAndModel queryProblems(Integer levelId) {
        return problemCenterService.queryProblems(buildParam().param("levelId", levelId));
    }

    @ApiOperation(value = "查询每个小段位的题目列表-用户端")
    @GetMapping("/levelProblem.do")
    public JsonAndModel queryLevelProblems(Integer levelId) {
        return problemCenterService.queryLevelProblems(buildParam().param("levelId", levelId));
    }

    @ApiOperation(value = "插入段位题目")
    @PostMapping("/levelProblem.do")
    public JsonAndModel insertProblem(@RequestBody Set<LevelProblemInfo> records) {
        return problemCenterService.insertProblem(buildParam(), records);
    }

}
