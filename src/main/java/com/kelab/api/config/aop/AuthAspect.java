package com.kelab.api.config.aop;

import com.alibaba.fastjson.JSON;
import com.kelab.api.config.AppSetting;
import com.kelab.api.constant.AuthConstant;
import com.kelab.api.controller.base.BaseController;
import com.kelab.api.dal.domain.ApiRoleAuthDomain;
import com.kelab.api.dal.repo.ApiRoleAuthRepo;
import com.kelab.info.base.JsonAndModel;
import com.kelab.info.base.constant.BaseRetCodeConstant;
import com.kelab.info.base.constant.UserRoleConstant;
import com.kelab.info.context.Context;
import com.kelab.util.token.TokenUtil;
import com.kelab.util.uuid.UuidUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

@Aspect
@Component
public class AuthAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthAspect.class);

    private final ApiRoleAuthRepo apiRoleAuthRepo;

    public AuthAspect(ApiRoleAuthRepo apiRoleAuthRepo) {
        this.apiRoleAuthRepo = apiRoleAuthRepo;
    }

    @Pointcut("execution(public * com.kelab.api.controller.*.*(..)))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object setContext(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        BaseController controller = (BaseController) joinPoint.getTarget();
        HttpServletRequest request = controller.getRequest();
        String token = request.getHeader(AuthConstant.AUTH_HEADER_NAME);
        Integer roleId, userId = -1;
        if (token == null) {
            roleId = UserRoleConstant.NOT_LOGIN;
        } else {
            try {
                token = token.replaceAll(AuthConstant.TOKEN_PRE, "");
                roleId = (Integer) TokenUtil.tokenValueOf(token, AppSetting.secretKey, AuthConstant.ROLE_ID);
                userId = (Integer) TokenUtil.tokenValueOf(token, AppSetting.secretKey, AuthConstant.USER_ID);
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
        controller.setContext(context);
        controller.getResponse().setHeader(AuthConstant.LOG_ID_HEADER, context.getLogId());
        // 鉴权
        String url = String.format("%s:%s",request.getMethod(), request.getRequestURI().replaceAll(request.getContextPath(), ""));
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
            LOGGER.error("\n*************error occurred*************\n" +
                            "\tapi:{}\n" +
                            "\tdesc:{}\n" +
                            "\tlogId:{}\n" +
                            "\tuserId:{}\n" +
                            "\troleId:{}\n" +
                            "\targs:{}\n" +
                            "\terr:{}\n" +
                            "*****************************************"
                    , url, domain.getAuthDomain().getDesc(), context.getLogId(), userId, roleId, JSON.toJSONString(args), sw.toString());
            return JsonAndModel.builder(BaseRetCodeConstant.ERROR).build();
        }
    }
}
