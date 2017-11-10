import java.util.Iterator;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class IsoscelesTriangle extends Triangle implements Comparable<IsoscelesTriangle>, Iterable<Double>, Iterator<Double> {
    private int iteratorInd = 0;

    IsoscelesTriangle(double sideIn, double angleIn) {
        super(sideIn, sideIn, angleIn);
    }

    IsoscelesTriangle(String inputString) {
        StringTokenizer inputStringTok = new StringTokenizer(inputString, " ,;");
        if (inputStringTok.countTokens() == 2) {
            data[0] = Double.parseDouble(inputStringTok.nextToken());
            data[1] = data[0];
            data[2] = Double.parseDouble(inputStringTok.nextToken());   //degrees
        } else throw new IllegalArgumentException();
    }

    @Override
    public double area() {
        return 0.5 * data[0] * data[0] * sin(data[2] * PI / 180);
    }

    @Override
    public double perimeter() {
        return 2 * data[0] + sqrt(2 * data[0] * data[0] - 2 * data[0] * data[1] * cos(data[2] * PI / 180));
    }

    @Override
    public int compareTo(IsoscelesTriangle other) {
        return Double.compare(data[compInd], other.data[compInd]);
    }

    @Override
    public Iterator<Double> iterator() {
        reset();
        return this;
    }

    public void reset() {
        iteratorInd = 0;
    }

    public boolean hasNext() {
        return iteratorInd < 3 ? true : false;
    }

    public Double next() {
        if (iteratorInd < 3) {
            return data[iteratorInd++];
        }
        reset();
        return null;
    }
}
