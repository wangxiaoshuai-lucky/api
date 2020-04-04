package com.kelab.api.controller;

import com.kelab.api.controller.base.BaseController;
import com.kelab.api.service.ExperimentCenterService;
import com.kelab.info.base.JsonAndModel;
import com.kelab.info.experiment.info.ExperimentClassInfo;
import com.kelab.info.experiment.info.ExperimentContestInfo;
import com.kelab.info.experiment.info.ExperimentStudentInfo;
import com.kelab.info.experiment.query.ExperimentClassQuery;
import com.kelab.info.experiment.query.ExperimentStudentQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "实验平台相关Controller", tags = "experiment-center")
public class ExperimentCenterController extends BaseController {

    private ExperimentCenterService experimentCenterService;

    public ExperimentCenterController(ExperimentCenterService experimentCenterService) {
        this.experimentCenterService = experimentCenterService;
    }

    @GetMapping("/experiment/class.do")
    @ApiOperation(value = "查询所有班级")
    public JsonAndModel queryPage(ExperimentClassQuery query) {
        return experimentCenterService.queryPage(buildParam(query));
    }

    @ApiOperation(value = "查询所有班级，学生端查看")
    @GetMapping("/experiment/classForUser.do")
    public JsonAndModel queryPageForUser(ExperimentStudentQuery query) {
        return experimentCenterService.queryPageForUser(buildParam(query));
    }

    @PostMapping("/experiment/class.do")
    @ApiOperation(value = "开设班级")
    public JsonAndModel createExperimentClass(@RequestBody ExperimentClassInfo record) {
        return experimentCenterService.createExperimentClass(buildParam(), record);
    }

    @PutMapping("/experiment/class.do")
    @ApiOperation(value = "修改班级信息")
    public JsonAndModel updateExperimentClass(@RequestBody ExperimentClassInfo record) {
        return experimentCenterService.updateExperimentClass(buildParam(), record);
    }

    @DeleteMapping("/experiment/class.do")
    @ApiOperation(value = "删除班级")
    public JsonAndModel deleteExperimentClass(String ids) {
        return experimentCenterService.deleteExperimentClass(buildParam().param("ids", ids));
    }

    @ApiOperation(value = "查询班级学生")
    @GetMapping("/experiment/class/student.do")
    public JsonAndModel queryStudentPage(ExperimentStudentQuery query) {
        return experimentCenterService.queryStudentPage(buildParam(query));
    }

    @ApiOperation(value = "申请加班")
    @PostMapping("/experiment/class/student.do")
    public JsonAndModel applyClass(String classCode) {
        return experimentCenterService.applyClass(buildParam().param("classCode", classCode));
    }

    @ApiOperation(value = "教师审核学生")
    @PutMapping("/experiment/class/student.do")
    public JsonAndModel reviewStudentApply(@RequestBody ExperimentStudentInfo record) {
        return experimentCenterService.reviewStudentApply(buildParam(), record);
    }

    @ApiOperation(value = "查询所有的实验")
    @GetMapping("/experiment/class/contest.do")
    public JsonAndModel queryByClassId(Integer classId) {
        return experimentCenterService.queryByClassId(buildParam().param("classId", classId));
    }

    @ApiOperation(value = "创建实验")
    @PostMapping("/experiment/class/contest.do")
    public JsonAndModel saveContest(@RequestBody ExperimentContestInfo record) {
        return experimentCenterService.saveContest(buildParam(), record);
    }

    @ApiOperation(value = "更新实验")
    @PutMapping("/experiment/class/contest.do")
    public JsonAndModel updateContest(@RequestBody ExperimentContestInfo record) {
        return experimentCenterService.updateContest(buildParam(), record);
    }

    @ApiOperation(value = "删除实验")
    @DeleteMapping("/experiment/class/contest.do")
    public JsonAndModel deleteContest(String ids) {
        return experimentCenterService.deleteContest(buildParam().param("ids", ids));
    }
}
