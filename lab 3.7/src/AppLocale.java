import java.util.*;

public class AppLocale {
    private static final String strMsg = "Msg";
    private static Locale loc = Locale.getDefault();
    private static ResourceBundle res = ResourceBundle.getBundle(AppLocale.strMsg, AppLocale.loc);

    static Locale get() {
        return AppLocale.loc;
    }

    static void set(Locale loc) {
        AppLocale.loc = loc;
        System.out.println(AppLocale.strMsg + " " + AppLocale.loc);
        res = ResourceBundle.getBundle(AppLocale.strMsg, AppLocale.loc);
        System.out.println(res.getLocale());
    }

    static ResourceBundle getBundle() {
        return AppLocale.res;
    }

    static String getString(String key) {
        return res.getString(key);
    }

    // Resource keys:

    public static final String bureau = "bureau";
    public static final String worker = "worker";
    public static final String workers = "workers";
    public static final String Task = "Task";
    public static final String creation = "creation";
    public static final String statusFree = "statusFree";
    public static final String statusBusy = "statusBusy";
    public static final String Planner = "Planner";
    public static final String price = "price";
    public static final String salary = "salary";
    public static final String complexity = "complexity";
    public static final String efficiency = "efficiency";
    public static final String area = "area";
    public static final String floorHeight = "floorHeight";
    public static final String floors = "floors";
    public static final String days = "days";
    public static final String information = "information";
    public static final String everyone = "everyone";
}