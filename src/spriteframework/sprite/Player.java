package spriteframework.sprite;

import javax.swing.ImageIcon;

import spriteframework.Commons;

import java.awt.Image;
import java.awt.event.KeyEvent;

public class Player extends Sprite {

    private int width;
    private int height;
    private int boardwidth;
    private int boardheight;
    
    private Boolean Twodimensions;

    public Player(String playerdir, Boolean Twodimension, int playerwidth, int playerheight, int boardwidth, int boardheight) {
        loadImage(playerdir, playerwidth, playerheight);
        this.boardheight = boardheight;
        this.boardwidth = boardwidth;
		getImageDimensions();
		resetState();
		this.Twodimensions = Twodimension; 
    }

    protected void loadImage (String playerdir, int w, int h) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(playerdir));
        Image scaledImage = ii.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        width = w;
        height = h;
        setImage(scaledImage);
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

            if (x >= boardwidth - 2 * width) {

                x = boardwidth - 2 * width;
            }
            
            if (y >= boardheight - 2 * height) {

                y = boardheight - 2 * height;
            }
        }
        
        if(Twodimensions == false) { //permite mover apenas  para lados
            	if (x <= 2) {

                    x = 2;
                }


                if (x >= boardwidth - 2 * width) {

                    x = boardwidth - 2 * width;
                }

            

        }

        
    }

    public void keyPressed(KeyEvent e ) {

        int key = e.getKeyCode();
        
        if(Twodimensions == true) { //permite mover para cima e para lados
        	if (key == KeyEvent.VK_LEFT) {

                dx = -2; // apertando <-
            }

            if (key == KeyEvent.VK_RIGHT) {

                dx = 2; // apertando ->
            }
            if (key == KeyEvent.VK_UP) {

                dy = -1; // apertando ^
            }
            if (key == KeyEvent.VK_DOWN) {

                dy = 1; // apertando v
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
