import javax.swing.*;
import java.awt.*;
public class Frame extends JFrame{
    public Panel panel;
    public static Dimension screenSize;
    public Frame(){
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
    	panel = new Panel(screenSize.width,screenSize.height);
    	add(panel);
    	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    	setVisible(true);
    }
}