
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MyJedis {

    public void Test(){
        String host = "zjuipin420.redis.rds.aliyuncs.com";
        String password = "IPINzju420";
        Random random = new Random();
        List<JedisShardInfo> shards = Arrays.asList(
                new JedisShardInfo(host, 6379, 3000, password));

        ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);

        ShardedJedis one = pool.getResource();

        ArrayList<String> keys = new ArrayList<String>();

        for (int i=0; i<100000; i++) {
            int x = random.nextInt(600);
            int y = random.nextInt(70);
            int direction = random.nextInt(380);
            direction = direction - 180;
            String key = x +";" + y + ";" + direction;
            keys.add(key);
        }

        long start = System.currentTimeMillis();
        for (int i=0; i<100000; i++) {
            one.hget("J9-1-WM",keys.get(i));
        }
        long end = System.currentTimeMillis();
        pool.close();
        System.out.println("Simple@Pool SET: " + ((end - start)/1000.0) + " seconds");


    }

    public void test2(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(50);
        config.setMaxTotal(50);
        config.setMinIdle(0);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);
        String host = "zjuipin420.redis.rds.aliyuncs.com";
        String password = "IPINzju420";
        JedisPool pool = new JedisPool(config, host, 6379, 3000, password);
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Random random = new Random();
            long time1 = System.currentTimeMillis();
            Map map = new Map();
            map.setMapname("J9-1");
            long time2 = System.currentTimeMillis();
            System.out.println(""+(time2-time1));
            for(int i = 0 ; i < 40000 ; i ++){
                int x = random.nextInt(600);
                int y = random.nextInt(70);
                int direction = random.nextInt(380);
                direction = direction - 180;
                String key = x +";" + y + ";" + direction;
                jedis.hget("J9-1-WM",key);
            }
            long time3 = System.currentTimeMillis();
            System.out.println(""+(time3-time2));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
/// ... when closing your application:
        pool.destroy();
    }
}
