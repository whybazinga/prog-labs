abstract class Triangle {
    double[] data;
    int compInd;

    Triangle() {
        this(0, 0, 0);
    }

    Triangle(double sideOneIn, double sideTwoIn, double angleIn) {
        data[0] = sideOneIn;
        data[1] = sideTwoIn;
        data[2] = angleIn;  //degrees
        compInd = 0;
    }

    public void setCompInd(int i) {
        if (i <= 2 && i >= 0) {
            compInd = i;
        }
    }

    public abstract double area();

    public abstract double perimeter();

    @Override
    public String toString() {
        String output = new String("First side: " + data[0] + "; Second side: " + data[1] + "; Angle: " + data[2]);
        return output;
    }
}
