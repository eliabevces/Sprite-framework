package spriteframework.sprite;

import javax.swing.ImageIcon;

import spriteframework.Commons;

import java.awt.Image;
import java.awt.event.KeyEvent;

public class Player extends Sprite {

    private int width;
    private int height;
    private Boolean Twodimensions;

    public Player(String playerdir, Boolean Twodimension) {
        loadImage(playerdir);
		getImageDimensions();
		resetState();
		this.Twodimensions = Twodimension; 
    }

    protected void loadImage (String playerdir) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(playerdir));
        width = ii.getImage().getWidth(null);
        height = ii.getImage().getHeight(null);
        setImage(ii.getImage());
    }
    
    public void act() {

        x += dx;
        y += dy;
        
        if(Twodimensions == true) { //permite mover para cima e para lados
        	if (x <= 2) {

                x = 2;
            }
            
            if (y <= 2) {

                y = 2;
            }

            if (x >= Commons.BOARD_WIDTH - 2 * width) {

                x = Commons.BOARD_WIDTH - 2 * width;
            }
            
            if (y >= Commons.BOARD_HEIGHT - 2 * height) {

                y = Commons.BOARD_HEIGHT - 2 * height;
            }
        }
        
        if(Twodimensions == false) { //permite mover apenas  para lados
            	if (x <= 2) {

                    x = 2;
                }


                if (x >= Commons.BOARD_WIDTH - 2 * width) {

                    x = Commons.BOARD_WIDTH - 2 * width;
                }

            

        }

        
    }

    public void keyPressed(KeyEvent e ) {

        int key = e.getKeyCode();
        
        if(Twodimensions == true) { //permite mover para cima e para lados
        	if (key == KeyEvent.VK_LEFT) {

                dx = -2;
            }

            if (key == KeyEvent.VK_RIGHT) {

                dx = 2;
            }
            if (key == KeyEvent.VK_UP) {

                dy = -1;
            }
            if (key == KeyEvent.VK_DOWN) {

                dy = 1;
            }
        }
        
        if(Twodimensions == false) { //permite mover apenas  para lados
        	if (key == KeyEvent.VK_LEFT) {

                dx = -2;
            }

            if (key == KeyEvent.VK_RIGHT) {

                dx = 2;
            }

        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }
        if (key == KeyEvent.VK_UP) {

            dy = 0;
        }
        if (key == KeyEvent.VK_DOWN) {

            dy = -0;
        }
    }
    private void resetState() {

        setX(Commons.INIT_PLAYER_X);
        setY(Commons.INIT_PLAYER_Y);
    }
}
