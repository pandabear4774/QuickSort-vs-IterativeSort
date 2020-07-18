import java.awt.Graphics;
import java.awt.Graphics2D;

public class Rect {
    int length;
    int height;
    int xLocation;
    int yLocation;
    public Rect(int x, int height, int yLocation) {
        xLocation = x;
        length = 5;
        this.height = height;
        this.yLocation = yLocation;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int newValue) {
        height = newValue;
    }
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.scale(1, -1);
        g2d.translate(0, -Frame.screenSize.height);
        g2d.drawRect(50+xLocation, yLocation, length, height);
    }
}