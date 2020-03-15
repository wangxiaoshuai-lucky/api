package com.kelab.api.service;

import com.kelab.info.base.JsonAndModel;
import com.kelab.info.problemcenter.ProblemUserMarkInfo;
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
}
