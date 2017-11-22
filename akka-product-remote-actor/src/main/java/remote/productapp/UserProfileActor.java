package remote.productapp;

import akka.actor.Props;
import akka.actor.UntypedActor;
import com.google.gson.Gson;
import models.UserProfile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;

/**
 * Created by bthiru on 11/16/2017.
 */
public class UserProfileActor extends UntypedActor {

    public static Props props = Props.create(ProductDetailActor.class);
    //address of your redis server
    private static final String redisHost = "localhost";
    private static final Integer redisPort = 6379;

    //the jedis connection pool..
    private static JedisPool pool = null;

    public UserProfileActor() {
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

            HashMap UserProfile =  (HashMap)message;
            System.out.print("request String -->"+UserProfile);

            String globalId = (String)UserProfile.get("globalId");
            String accountNum = (String)UserProfile.get("AccountNum");
            String mtn = (String)UserProfile.get("mtn");
            String profileId = null;
            if(null!=accountNum && null != mtn){
                profileId = accountNum+mtn;
            }else{
                profileId = globalId;
            }
            List cartCatList = (List)UserProfile.get("cartCatList");
            List cartProdList = (List)UserProfile.get("cartProdList");
            List orderCatList = (List)UserProfile.get("orderCatList");
            List orderProdList = (List)UserProfile.get("orderProdList");
            List rvCatList = (List)UserProfile.get("rvCatList");
            List rvDeviceList = (List)UserProfile.get("rvDeviceList");
            List rvAccessoryList = (List)UserProfile.get("rvAccessoryList");

            UserProfile up = loadUserProfile(profileId,jedis);
            if(up != null){


                if(cartCatList!=null && !cartCatList.isEmpty()) {
                    up.getCartCategoryList().addAll(cartCatList);
                }
                if(cartProdList!=null && !cartProdList.isEmpty()) {
                    up.getCartProductList().addAll(cartProdList);
                }
                if(orderCatList!=null && !orderCatList.isEmpty()) {
                    up.getOrderCategoryList().addAll(orderCatList);
                }
                if(orderProdList!=null && !orderProdList.isEmpty()) {
                    up.getOrderProductList().addAll(orderProdList);

                }
                if(rvDeviceList!=null && !rvDeviceList.isEmpty()) {
                    up.getRvDeviceList().addAll(rvDeviceList);
                }
                if(rvAccessoryList!=null && !rvAccessoryList.isEmpty()) {
                    up.getRvAccessoryList().addAll(rvAccessoryList);
                }
                if(rvCatList!=null && !rvCatList.isEmpty()) {
                    up.getRvCategoryList().addAll(rvCatList);
                }
                //store
                String json = gson.toJson(up);
                jedis.set(profileId,json);
                getSender().tell("Ok", getSelf());
            }else {

                up = new UserProfile();
                up.setGlobalId(profileId);
                if(cartCatList!=null && !cartCatList.isEmpty()) {
                    up.setCartCategoryList(cartCatList);
                }
                if(cartProdList!=null && !cartProdList.isEmpty()) {
                    up.setCartProductList(cartProdList);
                }
                if(orderCatList!=null && !orderCatList.isEmpty()) {
                    up.setOrderCategoryList(orderCatList);
                }
                if(orderProdList!=null && !orderProdList.isEmpty()) {
                    up.setOrderProductList(orderProdList);

                }
                if(rvDeviceList!=null && !rvDeviceList.isEmpty()) {
                    up.setRvDeviceList(rvDeviceList);
                }
                if(rvAccessoryList!=null && !rvAccessoryList.isEmpty()) {
                    up.getRvAccessoryList().addAll(rvAccessoryList);
                }
                if(rvCatList!=null && !rvCatList.isEmpty()) {
                    up.setRvCategoryList(rvCatList);
                }
                //store
                String json = gson.toJson(up);
                jedis.set(profileId,json);
                getSender().tell("Ok", getSelf());
            }

        } else {
            System.out.print("Invalid request");
            unhandled(message);
        }
    }

    public UserProfile loadUserProfile(String key, Jedis jedis){
        UserProfile up = null;
        Gson gson = new Gson();
        String json = jedis.get(key);
        if(json !=null && json.trim() !="") {
            up = gson.fromJson(json, UserProfile.class);
        }
        //fill all attributes
        return up;
    }
}