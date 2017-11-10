public class Task {
    private int floors;
    private double area;
    private double floorHeight;
    private int days;

    Task(int floors, double floorHeight, double area, int days) {
        this.changeTask(floors, floorHeight, area, days);
    }

    public void changeTask(int floors, double floorHeight, double area, int days) {
        this.floors = floors;
        this.area = area;
        this.floorHeight = floorHeight;
        this.days = days;
    }

    public double getSize() {
        return area * floorHeight * floors;
    }

    public int getDays() {
        return days;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(AppLocale.Task + ":\n");
        result.append(AppLocale.getString(AppLocale.area) + " " + area + "; ");
        result.append(AppLocale.getString(AppLocale.floorHeight) + " " + floorHeight + "; ");
        result.append(AppLocale.getString(AppLocale.floors) + " " + floors + "; ");
        result.append("\n" + AppLocale.getString(AppLocale.days) + " " + days + "\n");
        return result.toString();
    }
}
