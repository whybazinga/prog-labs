import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;

public class Graph extends Applet {
    public ArrayList<Integer> humidity;
    int height;
    int width;
    int count;

    public class CoordinateSystem {
        Point center;
        private static final int PADDING = 25;
        int csHeight;
        int csWidth;

        CoordinateSystem() {
            center = new Point(height - PADDING, width - PADDING);
            csHeight = height - 2 * PADDING;
            csWidth = width - 2 * PADDING;
        }
    }

    CoordinateSystem cs = new CoordinateSystem();

    public Graph() {
        humidity = new ArrayList<>();
        int i = 0;
        while (this.getParameter("param_0" + i) != null) {
            humidity.add(Integer.parseInt(getParameter("param_" + i++)));
        }
        count = i;
        height = this.getSize().height;
        width = this.getSize().width;
    }

    int[][][] makeParts() {
        int[] coordinates = new int[count];
        int partWidth = Math.round((cs.csHeight - 20) / (count - 1));
        for (int i = 0; i <= count; i++) {
            coordinates[i] = Math.round((cs.csHeight - 20) * humidity.get(i) / 100);
        }
        int[][][] parts = new int[2][count][4];
        for (int i = 0; i < count; i++) {
            parts[0][i][0] = i * partWidth + cs.center.x;
            parts[0][i][1] = i * partWidth + cs.center.x;
            parts[0][i][2] = (i + 1) * partWidth + cs.center.x;
            parts[0][i][3] = (i + 1) * partWidth + cs.center.x;
            parts[1][i][0] = cs.center.y;
            parts[1][i][1] = cs.center.y - coordinates[i];
            parts[1][i][2] = cs.center.y - coordinates[i + 1];
            parts[1][i][3] = cs.center.y;
        }
        return parts;
    }

    private void drawCoordinateSystem(Graphics g) {
        g.drawLine(cs.center.x - 5, cs.center.y - cs.csHeight + 5, cs.center.x, cs.center.y - cs.csHeight);
        g.drawLine(cs.center.x + 5, cs.center.y - cs.csHeight + 5, cs.center.x, cs.center.y - cs.csHeight);
        g.drawLine(cs.center.x, cs.center.y - cs.csHeight, cs.center.x, cs.center.y);
        g.drawLine(cs.center.x + cs.csWidth - 5, cs.center.y - 5, cs.center.x + cs.csWidth, cs.center.y);
        g.drawLine(cs.center.x + cs.csWidth - 5, cs.center.y + 5, cs.center.x + cs.csWidth, cs.center.y);
        g.drawLine(cs.center.x + cs.csWidth, cs.center.y, cs.center.x, cs.center.y);
    }

    public void paint(Graphics g) {
        drawCoordinateSystem(g);
    }


}
