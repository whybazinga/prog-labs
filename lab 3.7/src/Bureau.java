import java.io.Serializable;
import java.util.ArrayList;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Bureau implements Serializable {
    public Task currentTask;
    public ArrayList<Worker> employees;
    public ArrayList<Worker> busy;
    public Planner planner;

    public final Date creationDate = new Date();

    Bureau() {
        employees = new ArrayList<>();
        busy = new ArrayList<>();
        planner = new Planner();
        currentTask = null;
    }

    public void hireWorkers(int workersAmount) {
        for (int i = 0; i < workersAmount; i++) {
            employees.add(new Worker());
        }
        employees.sort((item1, item2) -> item1.compareTo(item2));
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
        if (this.currentTask != null) {
            planner.setTask(currentTask, (new Random()).nextInt(5));
        } else {
            planner.resetTask();
        }
        if (!busy.isEmpty()) {
            employees.addAll(busy);
            employees.sort((item1, item2) -> item1.compareTo(item2));
        }
    }

    public void startCurrentTask() {
        if (currentTask != null) {
            int complexity = planner.getComplexity();
            int groupEfficiency = 0;
            double totalPrice = 0;
            int totalDays = currentTask.getDays();
            while (groupEfficiency * 8 * totalDays < complexity && !employees.isEmpty()) {
                busy.add(employees.remove(employees.size() - 1));
                groupEfficiency += busy.get(busy.size() - 1).efficiency;
                totalPrice += busy.get(busy.size() - 1).price * 8;
            }
            if (groupEfficiency * 8 * currentTask.getDays() < complexity) {
                totalDays = (int) Math.ceil(complexity / (groupEfficiency * 8));
            }
            totalPrice *= totalDays;
            totalPrice += planner.getBill();

            System.out.println(taskMessage(totalDays, totalPrice));
        } else {
            System.out.println("No current task");
        }
    }

    private String taskMessage(double totalDays, double totalPrice) {
        StringBuilder result = new StringBuilder(AppLocale.getString(AppLocale.Task) + " - " + AppLocale.getString(AppLocale.information) + ":\n");
        result.append(AppLocale.getString(AppLocale.workers + ":"));
        if (!employees.isEmpty()) {
            busy.forEach((item) -> result.append(" " + item.ID + ","));
        } else {
            result.append(AppLocale.getString(AppLocale.everyone));
        }
        result.append("\n" + AppLocale.getString(AppLocale.days) + " " + currentTask.getDays() + " + " + (int) (totalDays - currentTask.getDays()) + "\n");
        result.append(AppLocale.getString(AppLocale.price) + " " + String.format("%.2f", totalPrice) + "$");
        return result.toString();
    }

    public String getCreationDate() {
        DateFormat dateFormatter = DateFormat.getDateTimeInstance(
                DateFormat.DEFAULT, DateFormat.DEFAULT, AppLocale.get());
        String dateOut = dateFormatter.format(creationDate);
        return dateOut;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(currentTask + "\n");
        result.append(AppLocale.getString(AppLocale.statusFree) + " " + AppLocale.getString(AppLocale.workers) + ":\n");
        employees.forEach((item) -> result.append(item + "\n"));
        if (!busy.isEmpty()) {
            result.append(AppLocale.getString(AppLocale.statusBusy) + " " + AppLocale.getString(AppLocale.workers) + ":\n");
            busy.forEach((item) -> result.append(item + "\n"));
        }
        result.append(planner + "\n");
        result.append(AppLocale.getString(AppLocale.creation) + " " + getCreationDate());
        return result.toString();
    }
}
