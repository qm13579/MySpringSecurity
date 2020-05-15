package cn.people.utils.aspect;

import cn.people.domain.Field;
import cn.people.domain.Role;
import cn.people.domain.UserInfo;
import cn.people.utils.aspect.annotation.FieldScope;
import cn.people.utils.common.BaseEntity;
import cn.people.utils.common.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 字段过滤增强
 */
@Aspect
@Component
public class FieldScopeAspect {

    /**
     * 数据权限过滤关键字
     */
    public static final String FIELD_SCOPE = "fieldScope";

    /**别名**/
    public static final String ALIAS = "alias";

    @Pointcut("@annotation(cn.people.utils.aspect.annotation.FieldScope)")
    public void FieldPointCut(){}

    @Before("FieldPointCut()")
    public void doBefore(JoinPoint joinPoint){
        FieldScope fieldDataScope= getAnnotation(joinPoint);
        if (fieldDataScope ==null){
            return;
        }

        UserInfo user = SecurityUtils.getCurrentUser();
        if (user != null){
            for (Role role :user.getAuthorities()) {
                if ("ROLE_USER".equals(role.getAuthority() ) ){
                    return;
                }else {
                    fieldScopeFilter(joinPoint,user,fieldDataScope.tableAlias());
                }
            }
        }
    }

    /**筛选条件*/
    private static void fieldScopeFilter(JoinPoint joinPoint,UserInfo user,String tableAlias){

        Role role = getMaxRole(user.getAuthorities());
        StringBuffer sql = null;
        for (Field field :role.getFields()) {
            if (sql == null){
                sql = new StringBuffer(String.format(
                        "select %s.%s",tableAlias,field.getFieldName()
                ));
            }else {
                sql.append(""+String.format("%s.%s",tableAlias,field.getFieldName()));
            }
        }
        BaseEntity baseEntity = (BaseEntity) joinPoint.getArgs()[0];
        baseEntity.params.put(FIELD_SCOPE,sql.toString());
        baseEntity.params.put(ALIAS,tableAlias);
    }

    /**查看是否存在注解，有则获取*/
    private FieldScope getAnnotation(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        if (method != null){
            return method.getAnnotation(FieldScope.class);
        }
        return null;
    }

    /**获取最大优先级的角色*/
    private static Role getMaxRole(List<Role> roles){
        Integer max = null;
        Role roleMax = null;
        for (Role role :roles) {
            if (max == null){
               max = role.getLevel();
            }
            if (max > role.getLevel()) {
                max = role.getLevel();
                roleMax = role;
            }
        }
        return roleMax;
    }

}
