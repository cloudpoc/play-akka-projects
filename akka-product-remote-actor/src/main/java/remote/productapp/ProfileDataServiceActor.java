package remote.productapp;

import akka.actor.Props;
import akka.actor.UntypedActor;
import com.google.gson.Gson;
import models.BrowseInfo;
import models.ProductStore;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ProfileDataServiceActor extends UntypedActor {
  public static Props props = Props.create(ProfileDataServiceActor.class);
  //address of your redis server
  private static final String redisHost = "localhost";
  private static final Integer redisPort = 6379;

  //the jedis connection pool..
  private static JedisPool pool = null;

  public ProfileDataServiceActor() {
    //configure our pool connection
    pool = new JedisPool(redisHost, redisPort);

  }
  @Override
  public void onReceive(Object message) {
    System.out.print("request received-->"+message);
    Jedis jedis = pool.getResource();
    if (message instanceof String) {
      BrowseInfo sh = loadSiteHistory((String)message,jedis);
      System.out.print("request String");
      getSender().tell(sh, getSelf());

    } else {
      System.out.print("request No String");
      unhandled(message);
    }
  }
  public BrowseInfo loadSiteHistory(String key, Jedis jedis){
    BrowseInfo sh = null;
    Gson gson = new Gson();
    String json = jedis.get(key);
    if(json !=null && json.trim() !="") {
      sh = gson.fromJson(json, BrowseInfo.class);
    }
    //fill all attributes
    return sh;
  }
}
