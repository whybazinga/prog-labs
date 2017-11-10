import factories.*;

public class FactoryProducer {
    public static AbstractFactory getFactory(String choice) {

        if (choice.equalsIgnoreCase("ENGINE")) {
            return new EngineFactory();
        } else if (choice.equalsIgnoreCase("TRANSMISSION")) {
            return new TransmissionFactory();
        } else if (choice.equalsIgnoreCase("TIRES")) {
            return new TiresFactory();
        }

        return null;
    }
}
