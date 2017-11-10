package tires;

import java.util.Random;

public abstract class Tires {
    public int grip;
    public double price;

    public Tires(int grp, double prc) {
        Random r = new Random();
        grip = grp + r.nextInt(15);
        price = prc + r.nextInt(15) + Math.random();
    }

    public Tires makeZeroElement() {
        grip = 0;
        price = 0;
        return this;
    }

    @Override
    public String toString() {
        return new String("Grip: " + grip + "; Price: " + price);
    }
}
