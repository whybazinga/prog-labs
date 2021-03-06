import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;

public class Graph extends Applet {
    public ArrayList<Integer> humidity;
    int count;

    public final int HEIGHT = 600;
    public final int WIDTH = 900;

    public class CoordinateSystem {
        Point center;
        private static final int PADDING = 50;
        int csHeight;
        int csWidth;

        CoordinateSystem() {
            center = new Point(PADDING, HEIGHT - PADDING);
            csHeight = HEIGHT - 2 * PADDING;
            csWidth = WIDTH - 2 * PADDING;
        }
    }

    CoordinateSystem cs = new CoordinateSystem();

    public void init() {
        humidity = new ArrayList<>();
        int i = 0;
        while (this.getParameter("param_" + i) != null) {
            humidity.add(Integer.parseInt(getParameter("param_" + i++)));
        }
        count = i;
    }

    int[][][] makeParts() {
        int[] coordinates = new int[count];
        int partWidth = Math.round((cs.csWidth - 20) / (count - 1));
        for (int i = 0; i < count; i++) {
            coordinates[i] = Math.round((cs.csHeight - 20) * humidity.get(i) / 100);
        }
        int[][][] parts = new int[2][count][4];
        for (int i = 0; i < count - 1; i++) {
            parts[0][i][0] = cs.center.x + (i + 1) * partWidth;
            parts[0][i][1] = cs.center.x + (i + 1) * partWidth;
            parts[0][i][2] = cs.center.x + i * partWidth;
            parts[0][i][3] = cs.center.x + i * partWidth;
            parts[1][i][0] = cs.center.y - coordinates[i + 1];
            parts[1][i][1] = cs.center.y;
            parts[1][i][2] = cs.center.y;
            parts[1][i][3] = cs.center.y - coordinates[i];
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
        int[][][] parts = makeParts();
        for (int i = 0; i < count - 1; i++) {
            g.drawPolygon(parts[0][i], parts[1][i], 4);
            g.drawString(" " + (i + 1) + ": ( " + humidity.get(i) + "% )", parts[0][i][3] + 10, parts[1][i][3] + 5);
        }
        g.drawString(" " + (count) + ": ( " + humidity.get(count - 1) + "% )", parts[0][count - 2][0] + 10, parts[1][count - 2][0] + 5);
    }

}
