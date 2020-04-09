package com.kelab.api.service;

import cn.wzy.verifyUtils.annotation.Verify;
import com.kelab.info.base.JsonAndModel;
import com.kelab.info.base.constant.StatusMsgConstant;
import com.kelab.info.context.Context;
import com.kelab.info.experiment.info.*;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "service-experiment")
@RequestMapping("/experiment")
public interface ExperimentCenterService {
    /**
     * 查询所有班级
     */
    @GetMapping("/experiment/class.do")
    JsonAndModel queryPage(@RequestParam Map<String, Object> param);

    /**
     * 查询所有班级，学生端查看
     */
    @GetMapping("/experiment/classForUser.do")
    JsonAndModel queryPageForUser(@RequestParam Map<String, Object> param);

    /**
     * 开设班级
     */
    @PostMapping("/experiment/class.do")
    JsonAndModel createExperimentClass(@RequestParam Map<String, Object> param,
                                       @RequestBody ExperimentClassInfo record);

    /**
     * 更新班级信息
     */
    @PutMapping("/experiment/class.do")
    JsonAndModel updateExperimentClass(@RequestParam Map<String, Object> param,
                                       @RequestBody ExperimentClassInfo record);

    /**
     * 删除班级
     */
    @DeleteMapping("/experiment/class.do")
    JsonAndModel deleteExperimentClass(@RequestParam Map<String, Object> param);

    /**
     * 查询班级学生
     */
    @GetMapping("/experiment/class/student.do")
    JsonAndModel queryStudentPage(@RequestParam Map<String, Object> param);

    /**
     * 查询班级未分组学生
     */
    @GetMapping("/experiment/class/noGroupStudents.do")
    JsonAndModel queryAllStudentWithoutGroup(@RequestParam Map<String, Object> param);

    /**
     * 申请加班
     */
    @PostMapping("/experiment/class/student.do")
    JsonAndModel applyClass(@RequestParam Map<String, Object> param);

    /**
     * 教师审核学生
     */
    @PutMapping("/experiment/class/student.do")
    JsonAndModel reviewStudentApply(@RequestParam Map<String, Object> param,
                                    @RequestBody ExperimentReviewStudentInfo record);

    /**
     * 查询所有的实验
     */
    @GetMapping("/experiment/class/contest.do")
    JsonAndModel queryContest(@RequestParam Map<String, Object> param);

    /**
     * 创建实验
     */
    @PostMapping("/experiment/class/contest.do")
    JsonAndModel saveContest(@RequestParam Map<String, Object> param,
                             @RequestBody ExperimentContestInfo record);

    /**
     * 更新实验
     */
    @PutMapping("/experiment/class/contest.do")
    JsonAndModel updateContest(@RequestParam Map<String, Object> param,
                               @RequestBody ExperimentContestInfo record);

    /**
     * 删除实验
     */
    @DeleteMapping("/experiment/class/contest.do")
    JsonAndModel deleteContest(@RequestParam Map<String, Object> param);

    /**
     * 查询实验题目
     */
    @GetMapping("/experiment/contest/problems.do")
    JsonAndModel queryByContestIdPage(@RequestParam Map<String, Object> param);

    /**
     * 查询实验排行
     */
    @GetMapping("/experiment/contest/rank.do")
    JsonAndModel queryRankByContestId(@RequestParam Map<String, Object> param);

    /**
     * 查询班级所有的分组
     */
    @GetMapping("/experiment/class/group.do")
    JsonAndModel queryAllGroup(@RequestParam Map<String, Object> param);

    /**
     * 创建分组
     */
    @PostMapping("/experiment/class/group.do")
    JsonAndModel createGroup(@RequestParam Map<String, Object> param,
                             @RequestBody ExperimentGroupInfo record);

    /**
     * 修改分组名字
     */
    @PutMapping("/experiment/class/group.do")
    JsonAndModel updateGroup(@RequestParam Map<String, Object> param,
                             @RequestBody ExperimentGroupInfo record);

    /**
     * 删除分组
     */
    @DeleteMapping("/experiment/class/group.do")
    JsonAndModel deleteGroup(@RequestParam Map<String, Object> param);

    /**
     * 切换分组
     */
    @PutMapping("/experiment/class/changeGroup.do")
    JsonAndModel changeStudentGroup(@RequestParam Map<String, Object> param,
                                    @RequestBody ExperimentChangeGroupInfo record);

    /**
     * 查询作业列表
     */
    @GetMapping("/experiment/class/homework.do")
    JsonAndModel queryHomeworkPage(@RequestParam Map<String, Object> param);

    /**
     * 新建作业
     */
    @PostMapping("/experiment/class/homework.do")
    JsonAndModel createHomework(@RequestParam Map<String, Object> param, @RequestBody ExperimentHomeworkInfo record);

    /**
     * 修改
     */
    @PutMapping("/experiment/class/homework.do")
    JsonAndModel updateHomework(@RequestParam Map<String, Object> param, @RequestBody ExperimentHomeworkInfo record);

    /**
     * 删除作业
     */
    @DeleteMapping("/experiment/class/homework.do")
    JsonAndModel deleteHomework(@RequestParam Map<String, Object> param);

    /**
     * 查询学生提交的作业列表
     */
    @GetMapping("/experiment/class/studentHomework.do")
    JsonAndModel queryStudentHomeworkPage(@RequestParam Map<String, Object> param);

    /**
     * 学生提交作业
     */
    @PostMapping("/experiment/class/studentHomework.do")
    JsonAndModel submitHomework(@RequestParam Map<String, Object> param,
                                @RequestBody ExperimentStudentHomeworkInfo record);

    /**
     * 教师批改作业
     */
    @PutMapping("/experiment/class/studentHomework.do")
    JsonAndModel reviewHomework(@RequestParam Map<String, Object> param,
                                @RequestBody ExperimentStudentHomeworkInfo record);

    /**
     * 教师下载课程成绩
     */
    @GetMapping("/experiment/class/score.do")
    Response downloadClassScore(@RequestParam Map<String, Object> param);
}
