import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Mate on 11/05/16.
 */
public class View {
    private MapElement firstElement;
    private int row;
    private int column;
    private JPanel panel;
    private ArrayList<Drawable> map;

    public View() {
        JFrame window = new JFrame("Portal Game Liskovtheorem");
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                drawMap(g);
            }
        };

        window.add(panel);

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setBounds(100, 100, 800, 800);
        window.setVisible(true);
    }

    public void drawMap(Graphics g){
        for(int i = 0; i < map.size(); i++) {
            for (int a = 0; a < row; a++)
                for(int b = 0; b < column; b++){
                    map.get(i).draw(g, a * 40, b * 40);
                }
        }
    }

    public void setList(MapElement fe, int r, int c){
        firstElement = fe;
        MapElement tmp = fe;
        row = r;
        column = c;

        for (int i = 0; i < row; i ++){
            map.add(fe.getDrawableClass());
            for (int j = 0; j < column; j++) {
                fe = fe.getNextElement(Direction.Right);
                map.add(fe.getDrawableClass());
            }
            fe = tmp.getNextElement(Direction.Down);
        }
    }
}
