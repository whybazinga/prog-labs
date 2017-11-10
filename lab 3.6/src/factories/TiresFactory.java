package factories;

import engine.Engine;
import tires.*;
import transmission.Transmission;

public class TiresFactory extends AbstractFactory {
    @Override
    public Tires getTires(String country) {
        if (country == null) {
            return null;
        }

        if (country.equalsIgnoreCase("RUSSIA")) {
            return new RusTires();

        } else if (country.equalsIgnoreCase("GERMANY")) {
            return new GerTires();

        } else if (country.equalsIgnoreCase("JAPAN")) {
            return new JapTires();
        }

        return null;
    }

    @Override
    public Engine getEngine(String country) {
        return null;
    }

    @Override
    public Transmission getTransmission(String country) {
        return null;
    }
}
