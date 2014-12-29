package wiki.tagger.dao.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by vishnupk on 29/12/14.
 * <p/>
 * RedisManager is used by all DAOs to access redis and to do some redis specific operations.
 */
public class RedisManager {
    private static final RedisManager instance = new RedisManager();

    private static JedisPool pool;

    private RedisManager() {
        connect();
    }

    public static RedisManager getInstance() {
        return instance;
    }

    private void connect() {
        // TODO: get from some config files
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMinIdle(1);
        config.setMaxIdle(5);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        config.setTestWhileIdle(true);
        config.setTimeBetweenEvictionRunsMillis(60000);
        config.setNumTestsPerEvictionRun(10);
        pool = new JedisPool(config, "localhost", 6379);
    }

    public void release() {
        pool.destroy();
    }

    public Jedis getJedis() {
        return pool.getResource();
    }

    public void returnJedis(Jedis jedis) {
        pool.returnResource(jedis);
    }
}
