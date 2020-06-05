package com.ruoyi.common.utils;

import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author liy
 * @date 2020/3/21 15:57
 */
public class RedisUtil {

    public static Cursor<String> scan(StringRedisTemplate stringRedisTemplate, String match, int count){
        ScanOptions scanOptions = ScanOptions.scanOptions().match(match).count(count).build();
        RedisSerializer<String> redisSerializer = (RedisSerializer<String>) stringRedisTemplate.getKeySerializer();
        return (Cursor) stringRedisTemplate.executeWithStickyConnection((RedisCallback) redisConnection ->
                new ConvertingCursor<>(redisConnection.scan(scanOptions), redisSerializer::deserialize));
    }

}
