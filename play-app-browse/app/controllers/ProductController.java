package controllers;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import javax.inject.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Product;
import models.ProductStore;
import akka.actor.Scheduler;
import play.*;
import play.libs.Akka;
import play.libs.F;
import play.libs.Json;
import play.mvc.*;
import java.util.concurrent.Executor;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;
import scala.concurrent.ExecutionContextExecutor;
import utils.Util;
import scala.compat.java8.FutureConverters;
import javax.inject.*;
import java.util.concurrent.CompletionStage;
import static akka.pattern.Patterns.ask;

/**
 * This controller contains an action that demonstrates how to write
 * simple asynchronous code in a controller. It uses a timer to
 * asynchronously delay sending a response for 1 second.
 */
@Singleton
public class ProductController extends Controller {

    private final ActorSystem actorSystem;
    private final ExecutionContextExecutor exec;

    public Result retrieve(int id) {
        if (ProductStore.getInstance().getProduct(id) == null) {
            return notFound(Util.createResponse("Product with id:" + id + " not found", false));
        }
        JsonNode jsonObjects = Json.toJson(ProductStore.getInstance().getProduct(id));
        return ok(Util.createResponse(jsonObjects, true));
    }
    /**
     * @param actorSystem We need the {@link ActorSystem}'s
     * {@link Scheduler} to run code after a delay.
     * @param exec We need a Java {@link Executor} to apply the result
     * of the {@link CompletableFuture} and a Scala
     * {@link ExecutionContext} so we can use the Akka {@link Scheduler}.
     * An {@link ExecutionContextExecutor} implements both interfaces.
     */
    @Inject
    public ProductController(ActorSystem actorSystem, ExecutionContextExecutor exec) {
        this.actorSystem = actorSystem;
        this.exec = exec;
    }
    // From Remote Actor
    public CompletionStage<Result> getProduct(int id) {
        String actorPath = "akka.tcp://ProductSystem@127.0.0.1:2552/user/product"; // get akka path of your worker, this will not show in my example
        ActorSelection actor = actorSystem.actorSelection(actorPath); // get actor ref

        return FutureConverters.toJava(ask(actor, new String(String.valueOf(id)), 10000))
                .thenApply(response -> ok(Util.createResponse( Json.toJson((Product)response),true)));
    }
}
