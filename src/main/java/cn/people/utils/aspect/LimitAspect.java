package cn.people.utils.aspect;

import cn.people.utils.aspect.annotation.Limit;
import cn.people.utils.common.LimitType;
import cn.people.utils.common.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Slf4j
@Aspect
@Component
public class LimitAspect {

    private final RedisTemplate<Object,Object> redisTemplate;

    public LimitAspect(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @Pointcut("@annotation(cn.people.utils.aspect.annotation.Limit)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = RequestHolder.getRequest();

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Limit limit = method.getAnnotation(Limit.class);

        String key = limit.key();
        if (StringUtils.isEmpty(key)){
            if (limit.limitType() == LimitType.IP){

                key = RequestHolder.getIp(request);
            }else {
                key = method.getName();
            }
        }
        Long count = redisTemplate.opsForValue().increment(key, 1);
        redisTemplate.expire(key,limit.period(), TimeUnit.SECONDS);
        if (count < limit.count()){
            log.info("第{}次访问key为{},描述为[{}]的接口",count,key,limit.name());
            return point.proceed();
        }else {
            throw new RuntimeException("访问受限");
        }

    }
}
