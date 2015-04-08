package Actors;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import Framework.Canvas;
import Framework.Framework;

public class Player {
	
	private static int dx;
	private static int dy;
	private static int x;
	private static int y;
	private static int height;
	private static int width;
    private String image_string;
    private Image image;
    private Rectangle rect;
	
    public Player() {
    	image_string = "/Images/player.png";
    	image = new ImageIcon(getClass().getResource(image_string)).getImage();
    	x  = 0;
    	y  = 0;
    	height = image.getHeight(null);
    	width  = image.getWidth(null);
    	dx = 5;
    	dy = 5;
    }
    
    public Rectangle getRectangle() {
    	return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }
    
    public int getX() {
    	return x;
    }
    
    public int getY() {
    	return y;
    }
    
    public Image getImage() {
    	return image;
    }
    
	public void move(Rectangle rec) {
		
		if (Canvas.keyboardKeyState(KeyEvent.VK_UP)) {
			if (rec != null) {
				if ((rec.getY() + rec.getHeight()) <= (this.getY())) {
					y -= dy;
				}
			} else {
				y -= dy;
			}
		}

		if (Canvas.keyboardKeyState(KeyEvent.VK_DOWN)) {
			/*if (rec != null) {
				if ((rec.getY()) >= (this.getY() + this.getImage().getHeight(null))) {
					y += dy;
				}
			} else {*/
				y += dy;
			//}
		}

		if (Canvas.keyboardKeyState(KeyEvent.VK_RIGHT)) {
			if (rec != null) {
				if ((rec.getX()) >= (this.getX() + this.getImage().getWidth(null))) {
					x += dx;
				}
			} else {
				x += dx;
			}
		}

		if (Canvas.keyboardKeyState(KeyEvent.VK_LEFT)) {
			/*if (rec != null) {
				if ((rec.getX() + rec.getWidth()) <= (this.getX())) {
					x -= dx;
				}
			} else {*/
				x -= dx;
			//}
		}
		
	}
	
	public void finish_move(KeyEvent e) {
		//TO DO
		/*if (e.getKeyCode() == KeyEvent.VK_UP) {
			y -= (y % height);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			y += (y % height);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			x += (x % width);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			x -= (x % width);
		}*/
	}
}