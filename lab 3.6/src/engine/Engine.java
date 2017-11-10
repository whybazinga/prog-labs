package engine;

import java.util.Random;

public abstract class Engine {
    public int power;
    public double price;

    public Engine(int pwr, double prc) {
        Random r = new Random();
        power = pwr + r.nextInt(15);
        price = prc + r.nextInt(15) + Math.random();
    }

    public Engine makeZeroElement() {
        power = 0;
        price = 0;
        return this;
    }

    @Override
    public String toString() {
        return new String("Power: " + power + "; Price: " + price);
    }
}
