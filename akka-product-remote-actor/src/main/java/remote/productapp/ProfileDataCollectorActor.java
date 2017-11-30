package remote.productapp;

import akka.actor.Props;
import akka.actor.UntypedActor;
import com.google.gson.Gson;
import models.BrowseInfo;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;

/**
 * Created by bthiru on 11/16/2017.
 */
public class ProfileDataCollectorActor extends UntypedActor {

    public static Props props = Props.create(ProfileDataServiceActor.class);
    //address of your redis server
    private static final String redisHost = "localhost";
    private static final Integer redisPort = 6379;

    //the jedis connection pool..
    private static JedisPool pool = null;

    public ProfileDataCollectorActor() {
        //configure our pool connection
        pool = new JedisPool(redisHost, redisPort);

    }
    @Override
    public void onReceive(Object message) {
        System.out.print("request receved-->"+message);
        if (message instanceof HashMap) {
            //get a jedis connection jedis connection pool
            String profileId = "";
            Jedis jedis = pool.getResource();
            Gson gson = new Gson();

            HashMap siteHistory =  (HashMap)message;
            System.out.print("request String -->"+siteHistory);

            String globalId = (String)siteHistory.get("globalId");
            String mtn = (String)siteHistory.get("mtn");
            String accountNo = (String)siteHistory.get("accountNo");

            if(accountNo!=null && mtn !=null){
                profileId = profileId+"@"+accountNo+mtn;
            }else{
                profileId = profileId+globalId;
            }

            List cartDeviceList = (List)siteHistory.get("cartDeviceList");
            List cartAccessoryList = (List)siteHistory.get("cartAccessoryList");
            List cartCategoryList = (List)siteHistory.get("cartCategoryList");

            List orderedDeviceList = (List)siteHistory.get("orderedDeviceList");
            List orderedAccessoryList = (List)siteHistory.get("orderedAccessoryList");
            List orderCategoryList = (List)siteHistory.get("orderCategoryList");

            List browsedDeviceList = (List)siteHistory.get("browsedDeviceList");
            List browsedAccessoryList = (List)siteHistory.get("browsedAccessoryList");
            List browsedCategoryList = (List)siteHistory.get("browsedCategoryList");

            BrowseInfo sh = loadSiteHistory(profileId,jedis);
            if(sh != null){

                if(cartDeviceList!=null && !cartDeviceList.isEmpty()) {
                    sh.getCartDeviceList().addAll(cartDeviceList);
                }
                if(cartAccessoryList!=null && !cartAccessoryList.isEmpty()) {
                    sh.getCartAccessoryList().addAll(cartAccessoryList);
                }
                if(cartCategoryList!=null && !cartCategoryList.isEmpty()) {
                    sh.getCartCategoryList().addAll(cartCategoryList);
                }
                //------------
                if(orderedDeviceList!=null && !orderedDeviceList.isEmpty()) {
                    sh.getOrderedDeviceList().addAll(orderedDeviceList);
                }
                if(orderedAccessoryList!=null && !orderedAccessoryList.isEmpty()) {
                    sh.getOrderedAccessoryList().addAll(orderedAccessoryList);
                }
                if(orderCategoryList!=null && !orderCategoryList.isEmpty()) {
                    sh.getOrderCategoryList().addAll(orderCategoryList);
                }
                //-----------
                if(browsedDeviceList!=null && !browsedDeviceList.isEmpty()) {
                    sh.getBrowsedDeviceList().addAll(browsedDeviceList);
                }
                if(browsedAccessoryList!=null && !browsedAccessoryList.isEmpty()) {
                    sh.getBrowsedAccessoryList().addAll(browsedAccessoryList);
                }
                if(browsedCategoryList!=null && !browsedCategoryList.isEmpty()) {
                    sh.getBrowsedCategoryList().addAll(browsedCategoryList);
                }
                //store
                String json = gson.toJson(sh);
                jedis.set(profileId,json);
                getSender().tell("Ok", getSelf());
            }else {

                sh = new BrowseInfo();
                if(cartDeviceList!=null && !cartDeviceList.isEmpty()) {
                    sh.getCartDeviceList().addAll(cartDeviceList);
                }
                if(cartAccessoryList!=null && !cartAccessoryList.isEmpty()) {
                    sh.getCartAccessoryList().addAll(cartAccessoryList);
                }
                if(cartCategoryList!=null && !cartCategoryList.isEmpty()) {
                    sh.getCartCategoryList().addAll(cartCategoryList);
                }
                //------------
                if(orderedDeviceList!=null && !orderedDeviceList.isEmpty()) {
                    sh.getOrderedDeviceList().addAll(orderedDeviceList);
                }
                if(orderedAccessoryList!=null && !orderedAccessoryList.isEmpty()) {
                    sh.getOrderedAccessoryList().addAll(orderedAccessoryList);
                }
                if(orderCategoryList!=null && !orderCategoryList.isEmpty()) {
                    sh.getOrderCategoryList().addAll(orderCategoryList);
                }
                //-----------
                if(browsedDeviceList!=null && !browsedDeviceList.isEmpty()) {
                    sh.getBrowsedDeviceList().addAll(browsedDeviceList);
                }
                if(browsedAccessoryList!=null && !browsedAccessoryList.isEmpty()) {
                    sh.getBrowsedAccessoryList().addAll(browsedAccessoryList);
                }
                if(browsedCategoryList!=null && !browsedCategoryList.isEmpty()) {
                    sh.getBrowsedCategoryList().addAll(browsedCategoryList);
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