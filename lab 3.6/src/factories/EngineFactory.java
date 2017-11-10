package factories;

import engine.*;
import tires.Tires;
import transmission.Transmission;

public class EngineFactory extends AbstractFactory {
    @Override
    public Engine getEngine(String country) {

        if (country == null) {
            return null;
        }

        if (country.equalsIgnoreCase("RUSSIA")) {
            return new RusEngine();

        } else if (country.equalsIgnoreCase("GERMANY")) {
            return new GerEngine();

        } else if (country.equalsIgnoreCase("JAPAN")) {
            return new JapEngine();
        }

        return null;
    }

    @Override
    public Tires getTires(String country) {
        return null;
    }

    @Override
    public Transmission getTransmission(String country) {
        return null;
    }
}
