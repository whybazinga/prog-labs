import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        IsoscelesTriangle a = new IsoscelesTriangle("2 30");
        IsoscelesTriangle b = new IsoscelesTriangle("2 8.2");
        IsoscelesTriangle c = new IsoscelesTriangle("3 10.1");
        IsoscelesTriangle d = new IsoscelesTriangle(5, 60);
        a.setCompInd(2);
        b.setCompInd(2);
        System.out.println("" + d + " Perimeter: " + d.perimeter() + "; Area: " + d.area());
        System.out.println(a);
        System.out.println(b);
        System.out.println(a.compareTo(b));
        IsoscelesTriangle[] arr = {d, a, b, c};
        for (IsoscelesTriangle it : arr) {
            it.setCompInd(0);
        }
        Arrays.stream(arr).forEach((it) -> System.out.println(it));
        System.out.println();
        Arrays.sort(arr);
        Arrays.stream(arr).forEach((it) -> System.out.println(it));

        System.out.println();
        Iterator<Double> it = a.iterator();
        while (it.hasNext()) {
            System.out.println("-> " + it.next());
        }
    }
}
