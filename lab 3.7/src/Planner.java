import java.io.Serializable;
import java.util.Random;

public class Planner implements Serializable {
    private double price;
    private double salary;
    private int complexity;

    Planner() {
        Random r = new Random();
        salary = r.nextInt(10) + 5 + r.nextDouble();
        complexity = 0;
    }

    public void setTask(Task task, double pricePerFloor) {
        price = task.getSize() * pricePerFloor;
        complexity = (int) task.getSize();
    }

    public void resetTask() {
        price = 0;
        complexity = 0;
    }

    public int getComplexity() {
        return complexity;
    }

    double getBill() {
        return salary + price;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(AppLocale.Planner + ":\n");
        result.append(AppLocale.getString(AppLocale.price) + " " + String.format("%.2f", price) + " ");
        result.append(AppLocale.getString(AppLocale.salary) + " " + String.format("%.2f", salary) + " ");
        result.append(AppLocale.getString(AppLocale.complexity) + " " + complexity + " ");
        return result.toString();
    }
}
