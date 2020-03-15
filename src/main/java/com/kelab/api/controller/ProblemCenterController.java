package com.kelab.api.controller;

import com.kelab.api.controller.base.BaseController;
import com.kelab.api.service.ProblemCenterService;
import com.kelab.info.base.JsonAndModel;
import com.kelab.info.problemcenter.ProblemUserMarkInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProblemCenterController extends BaseController {

    private ProblemCenterService problemCenterService;

    public ProblemCenterController(ProblemCenterService problemCenterService) {
        this.problemCenterService = problemCenterService;
    }

    @GetMapping("/user/problem/challenging.do")
    public JsonAndModel queryAcAndChallenging(Integer userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        return problemCenterService.queryAcAndChallenging(buildParam(param));
    }

    @GetMapping("/user/problem/collect.do")
    public JsonAndModel collect(Integer userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        return problemCenterService.collect(buildParam(param));
    }


    @PostMapping("/user/problem/collect.do")
    public JsonAndModel saveOrDeleteProblemCollect(@RequestBody ProblemUserMarkInfo problemUserMark) {
        return problemCenterService.saveOrDeleteProblemCollect(buildParam(), problemUserMark);
    }

}
