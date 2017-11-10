import java.io.Serializable;
import java.util.Random;

public class Worker implements Comparable<Worker>, Serializable {
    double price;
    int efficiency;

    public static int counter = 1;
    public final int ID = counter;

    Worker() {
        Random r = new Random();
        price = r.nextInt(10) + 5 + r.nextDouble();
        efficiency = r.nextInt(10) + 5;
        counter++;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(AppLocale.getString(AppLocale.worker) + " #" + ID + ": ");
        result.append(AppLocale.getString(AppLocale.efficiency) + " " + efficiency + "; ");
        result.append(AppLocale.getString(AppLocale.price) + " " + String.format("%.2f", price) + " $ per hour");
        return result.toString();
    }

    @Override
    public int compareTo(Worker other) {
        if (this.efficiency > other.efficiency) {
            return 1;
        } else if (this.efficiency < other.efficiency) {
            return -1;
        } else {
            return 0;
        }
    }
}
