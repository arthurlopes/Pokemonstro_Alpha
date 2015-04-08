package Framework;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window.Type;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import Actors.NPC;
import Actors.Player;
import Map.Building;
import Map.City;

public class Game {
	
	public Player player;
	public NPC npc;
	public City city;
	
	AffineTransform aff, camera, buildings, npc_camera;
	
	
    public Game() {
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
        
        Thread threadForInitGame = new Thread() {
            @Override
            public void run(){
                Initialize();
                LoadContent();
                
                Framework.gameState = Framework.GameState.PLAYING;
            }
        };
        threadForInitGame.start();
    }
    
    private void Initialize() {
    	player = new Player();
    	npc    = new NPC();
    	city   = new City("Pallet", 4);
    	
    	aff = new AffineTransform();
    	aff.setToIdentity();
    	camera = new AffineTransform();
    	camera.setToIdentity();
    	buildings = new AffineTransform();
    	buildings.setToIdentity();
    	npc_camera = new AffineTransform();
    	npc_camera.setToIdentity();
    }
    
    private void LoadContent() {
    	
    }
    
    public void UpdateGame(long gameTime) {
    	boolean collided = false;
		Rectangle new_rect = null;
    	int i = 0;

    	for (Building b : city.getBuildings()) {
			new_rect = null;
    		if (i != 0) {
    			if (b.getRectangle().intersects(player.getRectangle())) {
    				System.out.println(b.getName());
    				new_rect = new Rectangle(
						(int)(2*b.getRectangle().getX()),
    					(int)(2*b.getRectangle().getY()),
    					(int)(2*b.getRectangle().getWidth()),
    					(int)(2*b.getRectangle().getHeight())
					);

    				break;
    			}
    		}
    		i++;
    	}

    	player.move(new_rect);
		//if (collided_rect != null)
    	System.out.println(
				//collided_rect.getX() + " " + collided_rect.getY() + "  " +
				(player.getX() + " " +
				(player.getY())));
        npc.move();
    }
    
    public void Draw(Graphics2D g2d) {
    	//aff.setToScale(2, 2);
    	camera.setToTranslation((-1)*player.getX()+Framework.frameWidth/2  - player.getImage().getWidth(null)/2,
				                (-1)*player.getY()+Framework.frameHeight/2 - player.getImage().getHeight(null)/2);
    	camera.concatenate(aff);
    	
    	for (Building b : city.getBuildings()) {
			//buildings.setToIdentity();
			buildings.setToTranslation(b.getX(), b.getY());
	    	camera.concatenate(buildings);
	    	g2d.drawImage(b.getImage(), camera, null);

			g2d.drawRect((int)(2*b.getRectangle().getX() - player.getX() + 225),
					(int)(2*b.getRectangle().getY() - player.getY() + 125),
					2*(int)b.getRectangle().getWidth(), 2*(int)b.getRectangle().getHeight());

	    	try {
	    		camera.concatenate(buildings.createInverse());
	    	} catch (NoninvertibleTransformException ex) {
	    		ex.printStackTrace();
	    	}
    	}
    	
    	try {
    		camera.concatenate(aff.createInverse());
    	} catch (NoninvertibleTransformException e) {
    		e.printStackTrace();
    	}
    	
    	npc_camera.setToTranslation(npc.getX(), npc.getY());
    	npc_camera.concatenate(camera);
    	g2d.drawImage(npc.getImage(), npc_camera, null);
    	
		g2d.drawImage(player.getImage(), Framework.frameWidth/2 - player.getImage().getWidth(null)/2, 
					  Framework.frameHeight/2 - player.getImage().getHeight(null)/2, null);
    }
}