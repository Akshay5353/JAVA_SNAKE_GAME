
package snake.game;

import javax.swing.*;


public class Snake extends JFrame {
    Snake()
    {
        super("Snake Game");
        add(new Board());
        pack();
        
        //setLocationRelativeTo(null) is used to get window in center 
        // we can also use setLocation(x-axis,Y-axis) to manually locate window
        setLocationRelativeTo(null);
        setTitle("Snake Game");
        // we can use super class also only condition is super class must be the first statement in constructor.
        setResizable(false);
        
    }
    public static void main(String[] args) {
        new Snake().setVisible(true);
    }
}
