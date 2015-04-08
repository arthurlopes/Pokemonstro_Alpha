package Map;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Building {
	
	private String name;
	private String city;
	private int x;
	private int y;
	private String image_name;
	private Image image;
	private Rectangle rect;
	
	public Building(String image_name, int x, int y, String name) {
		this.name = name;
		this.image_name = image_name;
		this.x = x;
		this.y = y;
		image = new ImageIcon(getClass().getResource(image_name)).getImage();
		rect = new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
	}
	
	public Rectangle getRectangle() {
		return rect;
	}
	
	public Image getImage() {
		return image;
	}
	
	public String getName() {
		return name;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}