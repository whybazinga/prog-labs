package factories;

import engine.Engine;
import tires.Tires;
import transmission.Transmission;

public abstract class AbstractFactory {
    public abstract Engine getEngine(String country);

    public abstract Transmission getTransmission(String country);

    public abstract Tires getTires(String country);
}
