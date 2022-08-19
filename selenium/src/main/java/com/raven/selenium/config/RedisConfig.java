package com.raven.selenium.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author zhoutao
 * @Description: redisTemplate配置
 * @date 2018/3/9
 */
@Configuration
@Getter
public class RedisConfig {
    //key中间分隔符
    public static final String KEY_DELIMITER = ":";
    @Value("${redis.key.prefix}")
    private String keyPrefix;
    @Value("${redis.key.maxLength}")
    private int keyMaxLength;

    @Value("${redis.hostName}")
    private String hostName;
    //@Value("${redis.password}")
    //private String password;
    @Value("${redis.defaultDbIndex}")
    private int defaultDbIndex;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.timeout}")
    private int timeout;
    @Value("${redis.pool.maxIdel}")
    private int poolMaxIdel;
    @Value("${redis.pool.maxTotal}")
    private int poolMaxTotal;
    @Value("${redis.pool.minIdel}")
    private int poolMinIdel;
    @Value("${redis.pool.maxWaitMillis}")
    private long poolMaxWaitMillis;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(this.getPoolMaxIdel());
        jedisPoolConfig.setMaxTotal(this.getPoolMaxTotal());
        jedisPoolConfig.setMinIdle(this.getPoolMinIdel());
        jedisPoolConfig.setMaxWaitMillis(this.getPoolMaxWaitMillis());
        jedisPoolConfig.setTestWhileIdle(true);
        jedisPoolConfig.setTestOnBorrow(false);

        JedisConnectionFactory factory = new JedisConnectionFactory(jedisPoolConfig);
        factory.setHostName(this.getHostName());
        factory.setPort(this.getPort());
        factory.setTimeout(this.getTimeout());
        //factory.setPassword(this.getPassword());
        factory.setDatabase(this.getDefaultDbIndex());
        factory.setUsePool(true);
        return factory;
    }

    /**
     * 存对象的redis结构用此
     *
     * @param jedisConnectionFactory
     * @return
     */
    @Bean(name = "objectRedisTemplate")
    RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        JdkSerializationRedisSerializer valueSerializer = new JdkSerializationRedisSerializer();
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setHashKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setHashValueSerializer(valueSerializer);
        return redisTemplate;
    }

    /**
     * 使用简单string结构的用此对象
     * 这样存到redis里的值是可视化的
     *
     * @param jedisConnectionFactory
     * @return
     */
    @Bean(name = "stringRedisTemplate")
    StringRedisTemplate stringRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }
}
