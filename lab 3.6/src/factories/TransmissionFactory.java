package factories;

import engine.Engine;
import tires.Tires;
import transmission.*;

public class TransmissionFactory extends AbstractFactory {
    @Override
    public Transmission getTransmission(String country) {

        if (country == null) {
            return null;
        }

        if (country.equalsIgnoreCase("RUSSIA")) {
            return new RusTransmission();

        } else if (country.equalsIgnoreCase("GERMANY")) {
            return new GerTransmission();

        } else if (country.equalsIgnoreCase("JAPAN")) {
            return new JapTransmission();
        }

        return null;
    }

    @Override
    public Tires getTires(String country) {
        return null;
    }

    @Override
    public Engine getEngine(String country) {
        return null;
    }
}
