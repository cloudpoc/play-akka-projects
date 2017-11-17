package remote.productapp;

import akka.actor.Props;
import akka.actor.UntypedActor;
import com.google.gson.Gson;
import models.SiteHistory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;

/**
 * Created by bthiru on 11/16/2017.
 */
public class SiteHistoryActor extends UntypedActor {

    public static Props props = Props.create(ProductDetailActor.class);
    //address of your redis server
    private static final String redisHost = "localhost";
    private static final Integer redisPort = 6379;

    //the jedis connection pool..
    private static JedisPool pool = null;

    public SiteHistoryActor() {
        //configure our pool connection
        pool = new JedisPool(redisHost, redisPort);

    }
    @Override
    public void onReceive(Object message) {
        System.out.print("request receved-->"+message);
        if (message instanceof HashMap) {
            //get a jedis connection jedis connection pool
            Jedis jedis = pool.getResource();
            Gson gson = new Gson();

            HashMap siteHistory =  (HashMap)message;
            System.out.print("request String -->"+siteHistory);

            String profileId = (String)siteHistory.get("profileId");
            List cartCatList = (List)siteHistory.get("cartCatList");
            List cartProdList = (List)siteHistory.get("cartProdList");
            List orderCatList = (List)siteHistory.get("orderCatList");
            List orderProdList = (List)siteHistory.get("orderProdList");
            List rvCatList = (List)siteHistory.get("rvCatList");
            List rvProdList = (List)siteHistory.get("rvProdList");

            SiteHistory sh = loadSiteHistory(profileId,jedis);
            if(sh != null){


                if(cartCatList!=null || !cartCatList.isEmpty()) {
                    sh.getCartCategoryList().addAll(cartCatList);
                }
                if(cartProdList!=null || !cartProdList.isEmpty()) {
                    sh.getCartProductList().addAll(cartProdList);
                }
                if(orderCatList!=null || !orderCatList.isEmpty()) {
                    sh.getOrderCategoryList().addAll(orderCatList);
                }
                if(orderProdList!=null || !orderProdList.isEmpty()) {
                    sh.getOrderProductList().addAll(orderProdList);

                }
                if(rvProdList!=null || !rvProdList.isEmpty()) {
                    sh.getRvProductList().addAll(rvProdList);
                }
                if(rvCatList!=null || !rvCatList.isEmpty()) {
                    sh.getRvCategoryList().addAll(rvCatList);
                }
                //store
                String json = gson.toJson(sh);
                jedis.set(profileId,json);
                getSender().tell("Ok", getSelf());
            }else {

                sh = new SiteHistory();
                sh.setGlobalId(profileId);
                if(cartCatList!=null || !cartCatList.isEmpty()) {
                    sh.setCartCategoryList(cartCatList);
                }
                if(cartProdList!=null || !cartProdList.isEmpty()) {
                    sh.setCartProductList(cartProdList);
                }
                if(orderCatList!=null || !orderCatList.isEmpty()) {
                    sh.setOrderCategoryList(orderCatList);
                }
                if(orderProdList!=null || !orderProdList.isEmpty()) {
                    sh.setOrderProductList(orderProdList);

                }
                if(rvProdList!=null || !rvProdList.isEmpty()) {
                    sh.setRvProductList(rvProdList);
                }
                if(rvCatList!=null || !rvCatList.isEmpty()) {
                    sh.setRvCategoryList(rvCatList);
                }
                //store
                String json = gson.toJson(sh);
                jedis.set(profileId,json);
                getSender().tell("Ok", getSelf());
            }

        } else {
            System.out.print("Invalid request");
            unhandled(message);
        }
    }

    public SiteHistory loadSiteHistory(String key, Jedis jedis){
        SiteHistory sh = null;
        Gson gson = new Gson();
        String json = jedis.get(key);
        if(json !=null) {
            sh = gson.fromJson(json, SiteHistory.class);
        }
        //fill all attributes
        return sh;
    }
}