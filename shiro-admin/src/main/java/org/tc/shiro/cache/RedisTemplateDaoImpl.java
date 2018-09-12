package org.tc.shiro.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisTemplateDaoImpl implements RedisTemplateDao {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public Set<Object> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    @Override
    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    @Override
    public Boolean hashCheckHxists(String mapName, String field) {
        return redisTemplate.opsForHash().hasKey(mapName, field);
    }

    @Override
    public Object hashGet(String tableName, String hashKey) {
        return redisTemplate.opsForHash().get(tableName, hashKey);
    }

    @Override
    public Map<Object, Object> hashGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public Long hashIncrementLong(String hKey, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(hKey, hashKey, delta);
    }

    @Override
    public Double hashIncrementDouble(String hKey, String hashKey, Double delta) {
        return redisTemplate.opsForHash().increment(hKey, hashKey, delta);
    }

    @Override
    public void hashPush(String key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public Set<Object> hashGetAllKey(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    @Override
    public Long hashGetSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    @Override
    public List<Object> hashGetHashAllValues(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    @Override
    public Long hashDeleteKey(String key, Object... hashKeys) {
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }

    @Override
    public void listLeftPush(String key, Object value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public Object listLeftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public Long listSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public List<Object> listRange(String key, Long start, Long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public Long listRemove(String key, long i, Object value) {
        return redisTemplate.opsForList().remove(key, i, value);
    }

    @Override
    public Object listIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    @Override
    public void listSetValue(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    @Override
    public void listTrimByRange(String key, Long start, Long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    @Override
    public void listRightPush(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public Object listRightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public Long setAdd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    @Override
    public Long setGetSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    @Override
    public Set<Object> setGetMember(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public Boolean setCheckIsMember(String key, Object o) {
        return redisTemplate.opsForSet().isMember(key, o);
    }

    @Override
    public Object setRandomMember(String key) {
        return redisTemplate.opsForSet().randomMember(key);
    }

    @Override
    public Integer stringAppend(String key, String value) {
        return redisTemplate.opsForValue().append(key, value);
    }

    @Override
    public Object stringGetStringByKey(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public String stringGetSubString(String key, long start, long end) {
        return redisTemplate.opsForValue().get(key, start, end);
    }

    @Override
    public Long stringIncrementLong(String key, Long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Double stringIncrementDouble(String key, Double delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public void stringSet(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Object stringGetAndSet(String key, String value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    @Override
    public void stringSetValueAndExpireTime(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

}