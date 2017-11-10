import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Race {
    private ArrayList<Car> cars;
    private double trackLength;

    Race(double trackLength) {
        cars = new ArrayList<Car>();
        this.trackLength = trackLength;
    }

    void makeRace() {
        Random random = new Random();
        double[] passedWay = new double[cars.size()];
        for (int i = 0; i < cars.size(); i++) {
            passedWay[i] = 0;
        }
        int i = 0;
        int mem = i;
        while (passedWay[mem] < trackLength) {
            mem = i;
            passedWay[i] += random.nextInt((int) trackLength / 5) + cars.get(i).getResultBonus();
            i = (++i) % cars.size();

        }
        System.out.println("Finish!\n");
        for (int j = 0; j < cars.size(); j++) {
            System.out.println("Car #" + cars.get(j).getID() + " passed " + new DecimalFormat("#0.00").format(passedWay[j]) + "/"
                    + new DecimalFormat("#0.00").format(trackLength));
        }
        System.out.println("The winner is car #" + cars.get(mem).getID());
    }

    public double getTrackLength() {
        return trackLength;
    }

    public void addCar(Car input) {
        cars.add(input);
    }

    public Car getCar(int i) {
        return cars.get(i);
    }
}
