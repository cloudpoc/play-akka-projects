package controllers;

import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import javax.inject.*;

import akka.actor.Scheduler;
import models.BrowseInfo;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import scala.concurrent.ExecutionContext;
import scala.concurrent.ExecutionContextExecutor;
import utils.Util;
import scala.compat.java8.FutureConverters;

import static akka.pattern.Patterns.ask;

/**
 * This controller contains an action that demonstrates how to write
 * simple asynchronous code in a controller. It uses a timer to
 * asynchronously delay sending a response for 1 second.
 */
@Singleton
public class PersonalizationController extends Controller {

    private final ActorSystem actorSystem;
    private final ExecutionContextExecutor exec;

    /**
     * @param actorSystem We need the {@link ActorSystem}'s
     * {@link Scheduler} to run code after a delay.
     * @param exec We need a Java {@link Executor} to apply the result
     * of the {@link CompletableFuture} and a Scala
     * {@link ExecutionContext} so we can use the Akka {@link Scheduler}.
     * An {@link ExecutionContextExecutor} implements both interfaces.
     */
    @Inject
    public PersonalizationController(ActorSystem actorSystem, ExecutionContextExecutor exec) {
        this.actorSystem = actorSystem;
        this.exec = exec;
    }

    // From Remote Actor
    public CompletionStage<Result> getProduct(int id) {
        String actorPath = "akka.tcp://PersonalizationApp@127.0.0.1:2552/user/profileDataService"; // get akka path of your worker, this will not show in my example
        ActorSelection actor = actorSystem.actorSelection(actorPath); // get actor ref

        return FutureConverters.toJava(ask(actor, new String(String.valueOf(id)), 10000))
                .thenApply(response -> ok(Util.createResponse( Json.toJson((BrowseInfo)response),true)));
    }


    public CompletionStage<Result> collect() {
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        String globalId = dynamicForm.get("globalId");
        String mtn = dynamicForm.get("mtn");
        String accountNo = dynamicForm.get("accountNo");

        String cartDevice = dynamicForm.get("cartDeviceList");
        List cartDeviceList=null;
        if(cartDevice!=null){
            cartDeviceList= Arrays.asList(cartDevice.split(","));
        }
        String cartAccessory = dynamicForm.get("cartAccessoryList");
        List cartAccessoryList=null;
        if(cartAccessory!=null){
            cartAccessoryList= Arrays.asList(cartAccessory.split(","));
        }
        String cartCategory = dynamicForm.get("cartCategoryList");
        List cartCategoryList = null;
        if(cartCategory!=null){
             cartCategoryList = Arrays.asList(cartCategory.split(","));
        }
        String browsedDevice = dynamicForm.get("browsedDeviceList");
        List browsedDeviceList =null;
        if(browsedDevice!=null){
            browsedDeviceList= Arrays.asList(browsedDevice.split(","));
        }
        String browsedAccessory = dynamicForm.get("browsedAccessoryList");
        List browsedAccessoryList = null;
        if(cartCategory!=null){
            browsedAccessoryList = Arrays.asList(browsedAccessory.split(","));
        }
        String browsedCategory = dynamicForm.get("browsedCategoryList");
        List browsedCategoryList = null;
        if(cartCategory!=null){
            browsedCategoryList= Arrays.asList(browsedCategory.split(","));
        }

        Map m = new HashMap();
        m.put("globalId",globalId);
        m.put("accountNo",accountNo);
        m.put("mtn",mtn);

        m.put("cartDeviceList",cartDeviceList);
        m.put("cartAccessoryList",cartAccessoryList);
        m.put("cartCategoryList",cartCategoryList);

        m.put("browsedDeviceList",browsedDeviceList);
        m.put("browsedAccessoryList",browsedAccessoryList);
        m.put("browsedCategoryList",browsedCategoryList);

        String actorPath = "akka.tcp://PersonalizationApp@127.0.0.1:2552/user/profileDataCollector"; // get akka path of your worker, this will not show in my example
        ActorSelection actor = actorSystem.actorSelection(actorPath); // get actor ref

        return FutureConverters.toJava(ask(actor, m, 10000))
                .thenApply(response -> ok((String) response));
    }
}
