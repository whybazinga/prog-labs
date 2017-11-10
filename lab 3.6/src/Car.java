import engine.*;
import tires.*;
import transmission.*;

public class Car {
    public Engine engine;
    public Transmission transmission;
    public Tires tires;
    private static int count = 0;
    private final int ID = count;

    Car(Shop shop, double money) {
        if (count == 0) {
            shop.makeRandomSelection(money);
        } else {
            shop.makeBestSelection(money);
        }
        engine = shop.getEngine();
        transmission = shop.getTransmission();
        tires = shop.getTires();
        shop.removeSelection();
        count++;
    }

    int getID() {
        return ID;
    }

    double getResultBonus() {
        return engine.power * 0.6 + transmission.durability * 0.4 + tires.grip * 0.2;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Car #" + this.ID + "\n");
        result.append("Engine:\n");
        result.append(">" + engine + "\n");
        result.append("Transmission:\n");
        result.append(">" + transmission + "\n");
        result.append("Tires:\n");
        result.append(">" + tires + "\n");
        return result.toString();
    }
}
