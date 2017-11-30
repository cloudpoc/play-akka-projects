package remote.productapp;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

public class PersonalizeApplication {
  public static void main(String[] args) {
      startRemoteProductActorSystem();
  }

  public static void startRemoteProductActorSystem() {
    final ActorSystem system = ActorSystem.create("PersonalizationApp",
        ConfigFactory.load(("profileData")));
    system.actorOf(Props.create(ProfileDataServiceActor.class), "profileDataService");
    system.actorOf(Props.create(ProfileDataCollectorActor.class), "profileDataCollector");
    System.out.println("Started ProfileDataService");
  }
}
