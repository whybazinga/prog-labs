import java.util.*;
import java.io.*;

public class Main {
    static Locale createLocale(String[] args) {
        if (args.length == 2) {
            return new Locale(args[0], args[1]);
        } else if (args.length == 4) {
            return new Locale(args[2], args[3]);
        }
        return null;
    }

    static void setupConsole(String[] args) {
        if (args.length >= 2) {
            if (args[0].compareTo("-encoding") == 0) {
                try {
                    System.setOut(new PrintStream(System.out, true, args[1]));
                } catch (UnsupportedEncodingException ex) {
                    System.err.println("Unsupported encoding: " + args[1]);
                    System.exit(1);
                }
            }
        }
    }

    public static void main(String[] args) {
        setupConsole(args);
        Locale loc = createLocale(args);
        Bureau newBureau = null;
        try {
            if (loc == null) {
                System.err.println("Invalid argument(s)");
                System.exit(1);
            }
            AppLocale.set(loc);
            Connector con = new Connector("band.dat");
            Bureau test = new Bureau();
            test.hireWorkers(5);
            System.out.println(test);
            con.write(test);
            newBureau = con.read();
            System.out.println("\n" + AppLocale.getString(AppLocale.bureau) + ":\n");
            System.out.println(newBureau);
        } catch (Exception e) {
            System.err.println(e);
        }
        Task task = new Task(12, 3, 100, 10);
        newBureau.setCurrentTask(task);
        newBureau.startCurrentTask();
    }
}
