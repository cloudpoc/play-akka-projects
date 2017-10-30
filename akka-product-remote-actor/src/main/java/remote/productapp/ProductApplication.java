package remote.productapp;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

public class ProductApplication {
  public static void main(String[] args) {
      startRemoteProductActorSystem();
  }

  public static void startRemoteProductActorSystem() {
    final ActorSystem system = ActorSystem.create("ProductSystem",
        ConfigFactory.load(("product")));
    system.actorOf(Props.create(ProductDetailActor.class), "product");
    System.out.println("Started Product Actor System");
  }
}
