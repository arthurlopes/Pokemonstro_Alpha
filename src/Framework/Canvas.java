package Framework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public abstract class Canvas extends JPanel implements KeyListener {
    
    // Keyboard states - Here are stored states for keyboard keys - is it down or not.
    private static boolean[] keyboardState = new boolean[525];

    public Canvas()
    {
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setBackground(Color.black);
        
        this.addKeyListener(this);
    }
    
    public abstract void Draw(Graphics2D g2d);
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g; 
        super.paintComponent(g2d);
        Draw(g2d);
    }
    
    public static boolean keyboardKeyState(int key) {
        return keyboardState[key];
    }
    
    // Methods of the keyboard listener.
    @Override
    public void keyPressed(KeyEvent e) {
        keyboardState[e.getKeyCode()] = true;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        keyboardState[e.getKeyCode()] = false;
        keyReleasedFramework(e);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    	
    }
    
    public abstract void keyReleasedFramework(KeyEvent e);
}