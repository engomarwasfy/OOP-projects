package Model;

public class FactoryProducer {
  public static AbstractFactory getFactory(final String choice){

    if(choice.equalsIgnoreCase("Clown")){
       return new FactoryClown();

    }else if(choice.equalsIgnoreCase("COLOR")){
       return new FactoryImage();
    }

    return null;
 }
}
