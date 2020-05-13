package cn.people.utils.aspect;

import cn.people.domain.Role;
import cn.people.domain.UserInfo;
import cn.people.utils.aspect.annotation.DataScope;
import cn.people.utils.common.BaseEntity;
import cn.people.utils.common.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据过滤
 */
@Aspect
@Component
public class DataScopeAspect {
    /**
     * 全部数据权限
     */
    public static final String DATA_SCOPE_ALL = "1";
    /**
     * 自定数据权限
     */
    public static final String DATA_SCOPE_CUSTOM = "2";
    /**
     * 部门数据权限
     */
    public static final String DATA_SCOPE_DEPT = "3";
    /**
     * 部门及以下数据权限
     */
    public static final String DATA_SCOPE_DEPT_AND_CHILD = "4";
    /**
     * 仅本人数据权限
     */
    public static final String DATA_SCOPE_SELF = "5";
    /**
     * 数据权限过滤关键字
     */
    public static final String DATA_SCOPE = "dataScope";

    @Pointcut("@annotation(cn.people.utils.aspect.annotation.DataScope) )")
    public void dataScopePoint(){};

    /**
     * 后置增强
     * @param point
     */
    @Before("dataScopePoint()")
    public void doBefore(JoinPoint point){
        //获取注解
        MethodSignature methodSignature = (MethodSignature)point.getSignature();
        Method method = methodSignature.getMethod();
        DataScope scope = method.getAnnotation(DataScope.class);
        if (scope == null){
            return;
        }

        //获取当前用户
        UserInfo user = SecurityUtils.getCurrentUser();
        //如果不是超级管理员不过滤数据
        for (Role role : user.getAuthorities()) {
            if ("ROLE_ADMIN".equals(role.getAuthority())){
                return;
            }else {
                dataScopeFilter(point,user,scope.deptAlias(),scope.userAlias());
            }
        }

    }

    /**
     * 数据级别控制
     * @param joinPoint
     * @param user
     * @param depAlias
     * @param userAlias
     */
    public static void dataScopeFilter(JoinPoint joinPoint, UserInfo user,String depAlias,String userAlias){
        StringBuffer sqlString = new StringBuffer();
        for (Role role :user.getAuthorities()) {
            String scope = role.getDataScope();

            if (DATA_SCOPE_ALL.equals(scope)){
                sqlString = new StringBuffer();
                break;
            }
            else if (DATA_SCOPE_CUSTOM.equals(scope)){
                sqlString.append(String.format(
                        " OR %s.id IN (SELECT dept_id FROM) role_m_dept WHERE role_id=%s" ,
                        depAlias,
                        role.getId() ));
            }
            else if (DATA_SCOPE_DEPT.equals(scope)){
                sqlString.append(String.format("OR %s.id = %s",depAlias,user.getDep()));
            }
            else if (DATA_SCOPE_DEPT_AND_CHILD.equals(scope)){
                sqlString.append(String.format(
                        "OR %s.id IN (SELECT id FROM department WHERE id = %s OR find_in_set(%s,ancestors) ) ",
                        user.getDep(),
                        user.getDep()));
            }
            else if (DATA_SCOPE_SELF.equals(scope)){
                sqlString.append(String.format("%s.id = %s",userAlias,user.getId()));
            }
        }

        if (sqlString.toString() != null){
            BaseEntity baseEntity = (BaseEntity)joinPoint.getArgs()[0];
            Object arg = joinPoint.getArgs()[0];
            baseEntity.getParams().put(DATA_SCOPE,"AND ("+sqlString.substring(4)+")");
        }
    }

    /**
     * 通过注解进行插拔
     *
     * 其他人员验证部门
     * 关于数据字段的控制
     * 采用动态代理 sql 写入语句（）
     *
+     */
}

