public class Main {

    public static void main(String[] args) {
        Shop test = new Shop(10);
        System.out.println(test);
        Car a = new Car(test, 50);
        Car b = new Car(test, 60);
        Car c = new Car(test, 100);
        Car d = new Car(test, 30);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        Race ss = new Race(100);
        ss.addCar(a);
        ss.addCar(b);
        ss.addCar(c);
        ss.addCar(d);
        System.out.println("==========================================================");
        ss.makeRace();
    }
}
