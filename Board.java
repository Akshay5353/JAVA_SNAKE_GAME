
package snake.game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Board extends JPanel implements ActionListener{

    private Image apple;
    private Image body;
    private Image head;
    
    private final int DOT_SIZE = 10; //300 * 300 =90000 / (10*10)=900
    private final int ALL_DOTS= 900;
    private final int RANDOM_POSITION =29;
    
    private int apple_x;
    private int apple_y;
    private int Score=0;
    
    private Timer timer;
    
    private final int X[] =new int[ALL_DOTS];
    private final int Y[]= new int [ALL_DOTS];
    
    private boolean leftDirection = false;
    private boolean upDirection = true;
    private boolean downDirection = true;
    private boolean rightDirection = true;
    private boolean inGame =true;
    private int dots;
    
    public Board() {
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(300,300));
        setFocusable(true);
        loadImages();
        initGame();
        
    }
    public void loadImages(){
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("snake/game/icon/apple.png"));
        apple =i1.getImage();
        ImageIcon i2=new ImageIcon(ClassLoader.getSystemResource("snake/game/icon/body.png"));
        body= i2.getImage();
        ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("snake/game/icon/head2.png"));
        head= i3.getImage();
        
    }
    
    public void initGame(){
        
        dots=3;
        
        for(int z=0;z< dots;z++)
        {
            X[z]= 50 - z * DOT_SIZE;
            Y[z]=50;
        }   
        locateapple();
        
        timer = new Timer(140, this);
        timer.start();
    }
    
    public void locateapple()
    {
        int r= (int) (Math.random() * RANDOM_POSITION);
        apple_x= (r * DOT_SIZE);
        
        r= (int) (Math.random() * RANDOM_POSITION);
        apple_y= (r * DOT_SIZE);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); 
        draw(g);
    }
    
    public void draw(Graphics g)
    {
        if(inGame)
        {
        g.drawImage(apple,apple_x,apple_y,this);
        
        for(int z = 0; z < dots; z++)
        {
         if(z==0)
         {
             g.drawImage(head,X[z],Y[z], this);
         }
         else
         {
             g.drawImage(body,X[z],Y[z], this);
         }
         Toolkit.getDefaultToolkit().sync();
        }
        }
        else
        {
             gameOver(g);
        }
    }
    
    public void gameOver(Graphics g)
    {
        String msg="Game Over";
        String msg2="Score is: "+Score;
        Font font = new Font("SAN_SERIF",Font.BOLD,14);
        FontMetrics metrics = getFontMetrics(font);
        
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(msg,(300-metrics.stringWidth(msg))/2,300/2);
        g.drawString(msg2,(300-metrics.stringWidth(msg))/2,(300/2)+20);
    }
    
    public void checkCollision()
    {
        for(int z = dots; z > 0; z--)
        {
            if((z > 4) && (X[0]==X[z] && Y[0]==Y[z]))
            {
                inGame=false;
            }
        }
        if(Y[0] >= 300)
        {
            inGame=false;
        } 
        if(X[0] >=300)
        {
            inGame = false;
        }
        if(X[0] < 0)
        {
            inGame = false;
        }
        if(Y[0] < 0)
        {
            inGame = false;
        }
        if(!inGame)
        {
            timer.stop();
        }
        
    }
    
    public void checkApple()
    {
        if(X[0]==apple_x && Y[0]==apple_y)
        {
            Score++;
            dots++;
            locateapple();
            
        }
        
    }
    
    public void move()
    {
        for(int z = dots; z > 0; z--)
        {
            X[z] = X[z-1];
            Y[z] = Y[z-1];
        }
        if(leftDirection)
        {
            X[0] = X[0] - DOT_SIZE;
        }
        if(rightDirection)
        {
            X[0] = X[0] + DOT_SIZE;
        }
        if(upDirection)
        {
            Y[0] = Y[0] - DOT_SIZE;
        }
        if(downDirection)
        {
            Y[0] = Y[0] + DOT_SIZE;
        }
         
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(inGame){
            checkApple();
            checkCollision();
            move();
            
             
        }
        repaint();
    }
    
    private class TAdapter extends KeyAdapter{
        
        @Override
        public void keyPressed(KeyEvent e)
        {
            
           int key = e.getKeyCode();
           
            if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W && (!downDirection))
           {
               upDirection = true;
               leftDirection= false;
               rightDirection = false;
               
             
           }
            if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D  && (!leftDirection))
           {
               rightDirection = true;
               upDirection = false;
               downDirection = false;
           }
           
             if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S && (!upDirection))
           {
               downDirection = true;
               rightDirection = false;
               leftDirection = false;
               
           }
           
           if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A  && (!rightDirection))
           {
               leftDirection = true;
               upDirection = false;
               downDirection = false;
           }
           
        }
    }
    
    
}
