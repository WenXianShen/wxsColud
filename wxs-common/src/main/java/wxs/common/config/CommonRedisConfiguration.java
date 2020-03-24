package wxs.common.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;
import wxs.common.util.RedisHelper;

@Configuration
@EnableCaching
public class CommonRedisConfiguration {

	@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.port}")
	private int redisPort;

	@Value("${spring.redis.password}")
	private String redisAuth;

	@Value("${spring.redis.database}")
	private int redisDb;
	@Bean
	public RedisConnectionFactory connectionFactory() {
	    JedisPoolConfig poolConfig = new JedisPoolConfig();
	    poolConfig.setMaxTotal(100);
	    poolConfig.setMaxIdle(50);
	    poolConfig.setMinIdle(20);
	    poolConfig.setMaxWaitMillis(10000);
	    poolConfig.setTestOnBorrow(true);
	    poolConfig.setTestOnReturn(false);
	    poolConfig.setTestWhileIdle(true);
	    JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
	            .usePooling().poolConfig(poolConfig).build();

	    // 单点redis
	    RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
	    // 哨兵redis
	    // RedisSentinelConfiguration redisConfig = new RedisSentinelConfiguration();
	    // 集群redis
	    // RedisClusterConfiguration redisConfig = new RedisClusterConfiguration();
	    redisConfig.setHostName(redisHost);
	    redisConfig.setPassword(RedisPassword.of(redisAuth));
	    redisConfig.setPort(redisPort);
	    redisConfig.setDatabase(redisDb);

	    return new JedisConnectionFactory(redisConfig,clientConfig);
	}


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);

        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);

        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);

        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }

    @Bean
    public RedisHelper redisHelper(RedisTemplate<String, Object> redisTemplate) {
        return new RedisHelper(redisTemplate);
    }

    /**
     * 序列化和反序列化Redis缓存管理器的数据
     * @return
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer));
        return configuration;
    }

}
