import redis.clients.jedis.Jedis;

public class TestTransaction{

        public static void main( String[] args )
        {
            Jedis jedis = new Jedis("39.105.139.194",6379);

            jedis.set("haha","dsf");

            System.out.println("---------" + jedis.get("haha"));
            //查看服务是否运行
            System.out.println("服务正在运行: "+jedis.ping());
        }

}
