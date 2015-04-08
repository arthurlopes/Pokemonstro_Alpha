package Actors;

import java.awt.Image;

import javax.swing.ImageIcon;

public class NPC {
	
	private int x;
	private int y;
	private int x_start;
	private int y_start;
	private int x_final;
	private int y_final;
	private int x_velocity;
	private int y_velocity;
	
	private String image_string;
    private Image image;
	
	public NPC() {
		image_string = "/Images/NPC1.png";
		image = new ImageIcon(getClass().getResource(image_string)).getImage();
		
		x = 100;
		y = 120;
		x_start = 100;
		y_start = 120;
		x_final = 200;
		y_final = 120;
		x_velocity = 1;
		y_velocity = 0;
	}
	
	public Image getImage() {
    	return image;
    }
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setXvelocity(int Xv) {
		this.x_velocity = Xv;
	}
	
	public void setYvelocity(int Yv) {
		this.x_velocity = Yv;
	}
	
	public void move() {
		if (this.x == this.x_final+1 || this.x == this.x_start-1) {
			x_velocity *= (-1);
		}
		
		this.x += x_velocity;
		
		if (this.y == this.y_final+1 || this.y == this.y_start-1) {
			y_velocity *= (-1);
		}
		
		this.y += y_velocity;
	}
}
