package cn.people.config;

import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;

/**
 * 配置redis
 * @author : FENGZHI
 * create at:  2020/5/19  下午9:14
 * @description:
 */
@Slf4j
@EnableCaching
@Configuration
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig extends CachingConfigurerSupport {

    /**
     *
     * 设置redis序列化方式，过期时间
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        config.serializeValuesWith(
                RedisSerializationContext
                .SerializationPair
                .fromSerializer(fastJsonRedisSerializer)
        ).entryTtl(Duration.ofHours(2));
        return config;
    }
    /**
     * 重写key生成策略
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {

        return (target,method,params) ->{
            HashMap<Object, Object> container = new HashMap<>(3);
            Class<?> targetClass = target.getClass();
            container.put("class",targetClass.toGenericString());
            container.put("method",targetClass.getPackage());
            container.put("package",targetClass.getPackage());

            for (int i=0;i<params.length;i++) {
                container.put(String.valueOf(i),params[i]);
            }
            String jsonString = JSON.toJSONString(container);
            return DigestUtil.sha256(jsonString);
        };
    }
    @Bean(name = "redisTemplate")
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        ParserConfig.getGlobalInstance().addAccept("cn.people.domain");
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }



}

/**
 * value序列化
 * @param <T>
 */
class FastJsonRedisSerializer<T> implements RedisSerializer<T>{

    private final Class<T> clzz;

    FastJsonRedisSerializer(Class<T> clzz) {
        super();
        this.clzz = clzz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null){
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0){
            return null;
        }
        String str = new String(bytes, StandardCharsets.UTF_8);
        return JSON.parseObject(str,clzz);
    }
}

/**
 *重写序列化器
 *
 */
class StringRedisSerializer implements RedisSerializer<Object>{

    private final Charset charset;

    public StringRedisSerializer() {
        this(StandardCharsets.UTF_8);
    }

    StringRedisSerializer(Charset charset) {
        Assert.notNull(charset,"Charset must not be null!");
        this.charset = charset;
    }

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        String string = JSON.toJSONString(o);
        if (StringUtils.isBlank(string)){
            return null;
        }
        string = string.replace("\"","");
        return string.getBytes(charset);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return (bytes == null ? null:new String(bytes,charset));
    }
}