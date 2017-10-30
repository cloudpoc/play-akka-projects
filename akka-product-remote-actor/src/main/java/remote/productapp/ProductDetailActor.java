package remote.productapp;

import akka.actor.Props;
import akka.actor.UntypedActor;
import models.ProductStore;

public class ProductDetailActor extends UntypedActor {
  public static Props props = Props.create(ProductDetailActor.class);

  @Override
  public void onReceive(Object message) {
    System.out.print("request receved-->"+message);

    if (message instanceof String) {
      System.out.print("request String");
      getSender().tell(ProductStore.getInstance().getProduct((String) message), getSelf());

    } else {
      System.out.print("request No String");
      unhandled(message);
    }
  }
}
