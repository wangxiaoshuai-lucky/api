package com.kelab.api.config.aop;

import com.alibaba.fastjson.JSON;
import com.kelab.api.config.AppSetting;
import com.kelab.api.constant.AuthConstant;
import com.kelab.api.constant.enums.CacheBizName;
import com.kelab.api.controller.base.BaseController;
import com.kelab.api.dal.domain.ApiRoleAuthDomain;
import com.kelab.api.dal.redis.RedisCache;
import com.kelab.api.dal.repo.ApiRoleAuthRepo;
import com.kelab.info.base.JsonAndModel;
import com.kelab.info.base.constant.BaseRetCodeConstant;
import com.kelab.info.base.constant.JsonWebTokenConstant;
import com.kelab.info.base.constant.UserRoleConstant;
import com.kelab.info.context.Context;
import com.kelab.util.token.TokenUtil;
import com.kelab.util.uuid.UuidUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@Aspect
@Component
public class AuthAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthAspect.class);

    private final ApiRoleAuthRepo apiRoleAuthRepo;

    private final RedisCache redisCache;

    public AuthAspect(ApiRoleAuthRepo apiRoleAuthRepo,
                      RedisCache redisCache) {
        this.apiRoleAuthRepo = apiRoleAuthRepo;
        this.redisCache = redisCache;
    }

    @Pointcut("execution(public * com.kelab.api.controller.*.*(..)))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object setContext(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        BaseController controller = (BaseController) joinPoint.getTarget();
        HttpServletRequest request = controller.getRequest();
        String token = request.getHeader(AuthConstant.AUTH_HEADER_NAME);
        Integer roleId, userId = -1;
        Long refreshExp = -1L;
        if (StringUtils.isBlank(token)) {
            roleId = UserRoleConstant.NOT_LOGIN;
        } else {
            try {
                token = token.replaceAll(AuthConstant.TOKEN_PRE, "");
                roleId = (Integer) TokenUtil.tokenValueOf(token, AppSetting.secretKey, JsonWebTokenConstant.ROLE_ID);
                userId = (Integer) TokenUtil.tokenValueOf(token, AppSetting.secretKey, JsonWebTokenConstant.USER_ID);
                refreshExp = (Long) TokenUtil.tokenValueOf(token, AppSetting.secretKey, JsonWebTokenConstant.REFRESH_EXP_DATE);
            } catch (ExpiredJwtException e) {
                return JsonAndModel.builder(BaseRetCodeConstant.JWT_IS_EXPIRE_ERROR).build();
            } catch (Exception e) {
                return JsonAndModel.builder(BaseRetCodeConstant.ILLEGAL_ACCESS_ERROR).build();
            }
        }
        // 注入context
        Context context = new Context();
        context.setLogId(UuidUtil.genUUID());
        context.setOperatorId(userId);
        context.setOperatorRoleId(roleId);
        context.setFreshExp(refreshExp);
        controller.setContext(context);
        controller.getResponse().setHeader(AuthConstant.LOG_ID_HEADER, context.getLogId());
        // 鉴权
        String url = String.format("%s:%s", request.getMethod(), request.getRequestURI().replaceAll(request.getContextPath(), ""));
        ApiRoleAuthDomain domain = apiRoleAuthRepo.queryByRoleIdAndUrl(roleId, url);
        if (domain == null) {
            return JsonAndModel.builder(BaseRetCodeConstant.ILLEGAL_ACCESS_ERROR).build();
        }
        // 放行
        try {
            return joinPoint.proceed(args);
        } catch (Throwable throwable) {
            // 保存堆栈信息
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);
            String errMsg = String.format("\n*************error occurred*************\n" +
                            "\tapi:%s\n" +
                            "\tdesc:%s\n" +
                            "\tlogId:%s\n" +
                            "\tuserId:%s\n" +
                            "\troleId:%s\n" +
                            "\targs:%s\n" +
                            "\terr:%s\n" +
                            "*****************************************"
                    , url, domain.getAuthDomain().getDesc(), context.getLogId(), userId, roleId, JSON.toJSONString(args), sw.toString());
            redisCache.saveLog(CacheBizName.LOG, context.getLogId(), errMsg);
            LOGGER.error(errMsg);
            throw throwable;
        }
    }
}
