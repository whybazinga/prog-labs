package transmission;

import java.util.Random;

public abstract class Transmission {
    public int durability;
    public double price;

    public Transmission(int dur, double prc) {
        Random r = new Random();
        durability = dur + r.nextInt(15);
        price = prc + r.nextInt(15) + Math.random();
    }

    public Transmission makeZeroElement() {
        durability = 0;
        price = 0;
        return this;
    }

    @Override
    public String toString() {
        return new String("Durability: " + durability + "; Price: " + price);
    }
}
