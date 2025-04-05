//package longlh.dev.base.excel.base.excel.service.impl;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.JedisPool;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.List;
//
//@Slf4j
//public class CacheServiceImpl {
//    public static final String DEV_PROFILE = "dev";
//
//    /**
//     * The Constant ERROR_LABLE.
//     */
//    private static final String ERROR_LABLE = "getCache, error cacheKey=: {}";
//
//
//    @Value("${app.id}")
//    private String appId;
//
//    @Value("${spring.profiles.active}")
//    private String profileActive;
//
//    @Value("${index-redis}")
//    private Integer indexRedis;
//
//    /**
//     * The pool.
//     */
//    @Autowired
//    private JedisPool pool;
//
//    @Autowired
//    private JedisCluster jedisCluster;
//
//    /**
//     * Jedis.
//     *
//     * @return the jedis
//     */
//    public Jedis jedis() {
//        if (pool != null) {
//// jedis.select(indexRedis);
//            return pool.getResource();
//        }
//        return null;
//    }
//
//
//    @Override
//    public <T> T get(String key) {
//        return !DEV_PROFILE.equals(profileActive) ? getFromCluster(key) : getFromPool(key);
//    }
//
//    @Override
//    public <T> T get(byte[] key) {
//        return !DEV_PROFILE.equals(profileActive) ? getFromCluster(key) : getFromPool(key);
//    }
//
//    @Override
//    public <T> List<T> gets(String key) {
//        return !DEV_PROFILE.equals(profileActive) ? getsFromCluster(key) : getsFromPool(key);
//    }
//
//    @Override
//    public <T> void set(String key, T value) {
//        if (!DEV_PROFILE.equals(profileActive)) {
//            setToCluster(key, value);
//        } else {
//            setToPool(key, value);
//        }
//    }
//
//    @Override
//    public <T> void setExpire(String key, T value, Long second) {
//        if (!DEV_PROFILE.equals(profileActive)) {
//            setToClusterWithExpired(key, value, second);
//        } else {
//            setToPoolWithExpired(key, value, second);
//        }
//    }
//
//    @Override
//    public <T> void set(String key, T value, long expMinis) {
//        if (!DEV_PROFILE.equals(profileActive)) {
//            setToCluster(key, value);
//        } else {
//            setToPool(key, value);
//        }
//    }
//
//    @Override
//    public <T> void set(String key, List<T> value) {
//        log.info("###ooo REDIS key: {} ------ size: {}", key, value.size());
//        if (!DEV_PROFILE.equals(profileActive)) {
//            setToCluster(key, value);
//        } else {
//            setToPool(key, value);
//        }
//    }
//
//    @Override
//    public <T> void set(String key, List<T> value, long expMinis) {
//        log.info("###ooo REDIS key: {} ------ size: {}", key, value.size());
//        if (!DEV_PROFILE.equals(profileActive)) {
//            setToCluster(key, value, expMinis);
//        } else {
//            setToPool(key, value, expMinis);
//        }
//    }
//
//    @Override
//    public void deleteAllUserToken(Integer id) {
//        if (!DEV_PROFILE.equals(profileActive)) {
//            deleteAllUserTokenInCluster(id);
//        } else {
//            deleteAllUserTokenInPool(id);
//        }
//    }
//
//    @Override
//    public Long ttl(String key) {
//        if (!DEV_PROFILE.equals(profileActive)) {
//            return ttlInCluster(key);
//        } else {
//            return ttlInPool(key);
//        }
//    }
//
//    @Override
//    public Set<byte[]> keys(String pattern) {
//        if (!DEV_PROFILE.equals(profileActive)) {
//            return keysFromCluster(pattern);
//        } else {
//            return keysFromPool(pattern);
//        }
//    }
//
//    @Override
//    public <T> void put(String key, T value) {
//        if (!DEV_PROFILE.equals(profileActive)) {
//            putToCluster(key, value);
//        } else {
//            putToPool(key, value);
//        }
//    }
//
//    @Override
//    public <T> void put(String key, List<T> value) {
//        if (!DEV_PROFILE.equals(profileActive)) {
//            putToCluster(key, value);
//        } else {
//            putToPool(key, value);
//        }
//    }
//
//    @Override
//    public void delete(String key) {
//        if (!DEV_PROFILE.equals(profileActive)) {
//            deleteInCluster(key);
//        } else {
//            deleteInPool(key);
//        }
//    }
//
//    @Override
//    public void delete(byte[] key) {
//        if (!DEV_PROFILE.equals(profileActive)) {
//            deleteInCluster(key);
//        } else {
//            deleteInPool(key);
//        }
//    }
//
//    @Override
//    public void expire(String key, int second) {
//        if (!DEV_PROFILE.equals(profileActive)) {
//            expireInCluster(key, second);
//        } else {
//            expireInPool(key, second);
//        }
//    }
//
//    @Override
//    public boolean has(String key) {
//        if (!DEV_PROFILE.equals(profileActive)) {
//            return hasInCluster(key);
//        } else {
//            return hasInPool(key);
//        }
//    }
//
//    private <T> T getFromPool(String key) {
//        Jedis jedis = jedis();
//        T result = null;
//        try {
//            if (hasCache(jedis)) {
//                result = SerializeHelper.deserialize(jedis.get(SerializeHelper.serialize(genKey(key))));
//            }
//        } catch (IOException e) {
//            log.error(key, e);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//            return null;
//        } finally {
//            jedis.close();
//        }
//        return result;
//    }
//
//    private <T> T getFromCluster(String key) {
//        T result = null;
//        try {
//            result = SerializeHelper.deserialize(jedisCluster.get(SerializeHelper.serialize(genKey(key))));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//            return null;
//        }
//        return result;
//    }
//
//    private <T> List<T> getsFromPool(String key) {
//        Jedis jedis = jedis();
//        List<T> result = Collections.emptyList();
//        try {
//            if (hasCache(jedis)) {
//                result = SerializeHelper.deserialize(jedis.get(SerializeHelper.serialize(genKey(key))));
//            }
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        } finally {
//            jedis.close();
//        }
//        return result;
//    }
//
//    private <T> T getFromCluster(byte[] key) {
//        T result = null;
//        result = SerializeHelper.deserialize(jedisCluster.get(key));
//        return result;
//    }
//
//    private <T> T getFromPool(byte[] key) {
//        Jedis jedis = jedis();
//        T result = null;
//        try {
//            if (hasCache(jedis)) {
//                result = SerializeHelper.deserialize(jedis.get(key));
//            }
//        } finally {
//            jedis.close();
//        }
//        return result;
//    }
//
//    private <T> List<T> getsFromCluster(String key) {
//        List<T> result = Collections.emptyList();
//        try {
//            result = SerializeHelper.deserialize(jedisCluster.get(SerializeHelper.serialize(genKey(key))));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//        return result;
//    }
//
//    private <T> void setToPool(String key, T value) {
//        try (Jedis jedis = jedis()) {
//            if (hasCache(jedis)) {
//                jedis.del(SerializeHelper.serialize(genKey(key)));
//                jedis.set(SerializeHelper.serialize(genKey(key)), SerializeHelper.serialize(value));
//            }
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    private <T> void setToCluster(String key, T value) {
//        try {
//            jedisCluster.del(SerializeHelper.serialize(genKey(key)));
//            jedisCluster.set(SerializeHelper.serialize(genKey(key)), SerializeHelper.serialize(value));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    private <T> void setToPool(String key, List<T> value) {
//        try (Jedis jedis = jedis()) {
//            if (hasCache(jedis)) {
//                jedis.del(SerializeHelper.serialize(genKey(key)));
//                jedis.set(SerializeHelper.serialize(genKey(key)), SerializeHelper.serialize(value));
//            }
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    private <T> void setToClusterWithExpired(String key, T value, Long second) {
//        try {
//            jedisCluster.del(SerializeHelper.serialize(genKey(key)));
//            jedisCluster.setex(SerializeHelper.serialize(genKey(key)), second, SerializeHelper.serialize(value));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    private <T> void setToPoolWithExpired(String key, T value, Long second) {
//        try (Jedis jedis = jedis()) {
//            if (hasCache(jedis)) {
//                jedis.del(SerializeHelper.serialize(genKey(key)));
//                jedis.setex(SerializeHelper.serialize(genKey(key)), second, SerializeHelper.serialize(value));
//            }
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    private <T> void setToPool(String key, List<T> value, long exp) {
//        try (Jedis jedis = jedis()) {
//            if (hasCache(jedis)) {
//                jedis.del(SerializeHelper.serialize(genKey(key)));
//                SetParams params = new SetParams();
//                params.ex(exp * 60);
//                jedis.set(SerializeHelper.serialize(genKey(key)), SerializeHelper.serialize(value), params);
//            }
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    private <T> void setToCluster(String key, List<T> value) {
//        try {
//            jedisCluster.del(SerializeHelper.serialize(genKey(key)));
//            jedisCluster.set(SerializeHelper.serialize(genKey(key)), SerializeHelper.serialize(value));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    private <T> void setToCluster(String key, List<T> value, long exp) {
//        try {
//            jedisCluster.del(SerializeHelper.serialize(genKey(key)));
//            SetParams params = new SetParams();
//            params.ex(exp * 60);
//            jedisCluster.set(SerializeHelper.serialize(genKey(genKey(key))), SerializeHelper.serialize(value), params);
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    private <T> void putToCluster(String key, List<T> value) {
//        try {
//            jedisCluster.del(SerializeHelper.serialize(genKey(key)));
//            jedisCluster.set(SerializeHelper.serialize(genKey(key)), SerializeHelper.serialize(value));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    private <T> void putToPool(String key, List<T> value) {
//        Jedis jedis = jedis();
//
//        try (jedis) {
//            if (!hasCache(jedis)) {
//                return;
//            }
//            jedis.del(SerializeHelper.serialize(genKey(key)));
//            jedis.set(SerializeHelper.serialize(genKey(key)), SerializeHelper.serialize(value));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    private <T> void putToCluster(String key, T value) {
//        try {
//            jedisCluster.del(SerializeHelper.serialize(genKey(key)));
//            jedisCluster.set(SerializeHelper.serialize(genKey(genKey(key))), SerializeHelper.serialize(value));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    private Long ttlInCluster(String key) {
//        try {
//            return jedisCluster.ttl(SerializeHelper.serialize(genKey(key)));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private Long ttlInPool(String key) {
//        Jedis jedis = jedis();
//        if (!hasCache(jedis)) {
//            return 0L;
//        }
//
//        try {
//            return jedis.ttl(SerializeHelper.serialize(genKey(key)));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private Set<byte[]> keysFromCluster(String key) {
//        String pattern = "{*" + genKey(key) + "*}";
//
//// jedisCluster.
//        return jedisCluster.keys(pattern.getBytes());
//    }
//
//    private Set<byte[]> keysFromPool(String key) {
//        Jedis jedis = jedis();
//        if (!hasCache(jedis)) {
//            return new HashSet<>();
//        }
//
//        String pattern = "*" + genKey(key) + "*";
//
//        return jedis.keys(pattern.getBytes());
//    }
//
//    private <T> void putToPool(String key, T value) {
//        Jedis jedis = jedis();
//
//        try (jedis) {
//            if (!hasCache(jedis)) {
//                return;
//            }
//            jedis.del(SerializeHelper.serialize(genKey(key)));
//            jedis.set(SerializeHelper.serialize(genKey(key)), SerializeHelper.serialize(value));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    private void deleteInCluster(String key) {
//        try {
//            jedisCluster.del(SerializeHelper.serialize(genKey(key)));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    private void deleteInCluster(byte[] key) {
//        jedisCluster.del(key);
//    }
//
//    private void deleteInPool(String key) {
//        Jedis jedis = jedis();
//
//        try (jedis) {
//            if (!hasCache(jedis)) {
//                return;
//            }
//            jedis.del(SerializeHelper.serialize(genKey(key)));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    private void deleteInPool(byte[] key) {
//        Jedis jedis = jedis();
//
//        try (jedis) {
//            if (!hasCache(jedis)) {
//                return;
//            }
//            jedis.del(key);
//        }
//    }
//
//    private void expireInCluster(String key, int second) {
//        try {
//            jedisCluster.expire(SerializeHelper.serialize(genKey(key)), second);
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    private void expireInPool(String key, int second) {
//        Jedis jedis = jedis();
//
//        try (jedis) {
//            if (!hasCache(jedis)) {
//                return;
//            }
//            jedis.expire(SerializeHelper.serialize(genKey(key)), second);
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    private boolean hasInPool(String key) {
//        Jedis jedis = jedis();
//        if (!hasCache(jedis)) {
//            return false;
//        }
//
//        boolean result = false;
//        try {
//            result = jedis.exists(SerializeHelper.serialize(genKey(key)));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        } finally {
//            jedis.close();
//        }
//        return result;
//    }
//
//    private boolean hasInCluster(String key) {
//        boolean result = false;
//        try {
//            result = jedisCluster.exists(SerializeHelper.serialize(genKey(key)));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//        return result;
//    }
//
//    private void deleteAllUserTokenInPool(Integer id) {
//        String key = String.format("TOKENS_%s", id);
//        if (has(key)) {
//            List<String> tokens = gets(key);
//            tokens.forEach(this::delete);
//        }
//        delete(key);
//    }
//
//    public void deleteAllUserTokenInCluster(Integer id) {
//        String key = String.format("TOKENS_%s", id);
//        if (has(key)) {
//            List<String> tokens = gets(key);
//            tokens.forEach(this::delete);
//        }
//        delete(key);
//    }
//
//    private static boolean hasCache(Jedis jedis) {
//        return jedis != null;
//    }
//
//    private static void logError(String key, Object e) {
//        log.error(String.format(ERROR_LABLE, key), e);
//    }
//
//    private String genKey(String key) {
//        return String.format("%s_%s", appId, key);
//    }
//}
