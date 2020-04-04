package com.kelab.api.service;

import com.kelab.info.base.JsonAndModel;
import com.kelab.info.experiment.info.ExperimentClassInfo;
import com.kelab.info.experiment.info.ExperimentContestInfo;
import com.kelab.info.experiment.info.ExperimentStudentInfo;
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
     * 申请加班
     */
    @PostMapping("/experiment/class/student.do")
    JsonAndModel applyClass(@RequestParam Map<String, Object> param);

    /**
     * 教师审核学生
     */
    @PutMapping("/experiment/class/student.do")
    JsonAndModel reviewStudentApply(@RequestParam Map<String, Object> param,
                                    @RequestBody ExperimentStudentInfo record);

    /**
     * 查询所有的实验
     */
    @GetMapping("/experiment/class/contest.do")
    JsonAndModel queryByClassId(@RequestParam Map<String, Object> param);

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
}
