package FreezeMonster;


import java.awt.Graphics;

import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;

import FreezeMonster.sprite.BomberSpritefreezeMonster;
import FreezeMonster.sprite.BombfreezeMonster;
import FreezeMonster.sprite.ShotfreezeMonster;
import spriteframework.AbstractBoard;
import spriteframework.sprite.BadSprite;
import spriteframework.sprite.Player;

// import spaceinvaders.sprite.*;

public class FreezeMonsterBoard extends AbstractBoard{  

    private static final long serialVersionUID = 1L;

    public FreezeMonsterBoard(String playerdir, Boolean Twodimension) {
		super(playerdir, Twodimension, CommonsfreezeMonster.ALIEN_WIDTH, CommonsfreezeMonster.ALIEN_HEIGHT, CommonsfreezeMonster.BOARD_WIDTH, CommonsfreezeMonster.BOARD_HEIGHT, CommonsfreezeMonster.GROUND);
		// TODO Auto-generated constructor stub
	}

	//define sprites
    //private List<BadSprite> aliens;
    private ShotfreezeMonster shot;    
    
    // define global control vars   
    private int direction = -1;
    private int deaths = 0;


    private String explImg = "images/explosion.png";

    protected void createBadSprites() {  // create sprites
            for (int j = 0; j < 9; j++) {
            	BomberSpritefreezeMonster alien = new BomberSpritefreezeMonster(CommonsfreezeMonster.ALIEN_INIT_X + 18 * (int)(Math.random()*10) ,
                        CommonsfreezeMonster.ALIEN_INIT_Y + 18 * (int)(Math.random()*10), j); //cria aliens em lugares aleatorios
                badSprites.add(alien);
            }
        
    }
    
    protected void createOtherSprites() {
        shot = new ShotfreezeMonster();
    }

    private void drawShot(Graphics g) {

        if (shot.isVisible()) {

            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    // Override
    protected void drawOtherSprites(Graphics g) {
        drawShot(g);
    }
    
    protected void processOtherSprites(Player player, KeyEvent e) {
		int x = player.getX();
		int y = player.getY();

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_SPACE) {

			if (inGame) {

				if (!shot.isVisible()) {

					shot = new ShotfreezeMonster(x, y);
				}
			}
		}
	}

//    private void gameOver(Graphics g) {
//
//        g.setColor(Color.black);
//        g.fillRect(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
//
//        g.setColor(new Color(0, 32, 48));
//        g.fillRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);
//        g.setColor(Color.white);
//        g.drawRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);
//
//        Font small = new Font("Helvetica", Font.BOLD, 14);
//        FontMetrics fontMetrics = this.getFontMetrics(small);
//
//        g.setColor(Color.white);
//        g.setFont(small);
//        g.drawString(message, (Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2,
//                Commons.BOARD_WIDTH / 2);
//    }

    protected void update() {

        if (deaths == CommonsfreezeMonster.NUMBER_OF_ALIENS_TO_DESTROY) {

            inGame = false;
            timer.stop();
            message = "Game won!";
        }

        // player
        for (Player player: players) 
        	player.act();

        // shot
        if (shot.isVisible()) {

            int shotX = shot.getX();
            int shotY = shot.getY();

            for (BadSprite alien : badSprites) {

                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && shot.isVisible()) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + CommonsfreezeMonster.ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + CommonsfreezeMonster.ALIEN_HEIGHT)) {

                        ImageIcon ii = new ImageIcon(explImg);
                        alien.setImage(ii.getImage());
                        alien.setDying(true);
                        deaths++;
                        shot.die();
                    }
                }
            }

            int y = shot.getY();
            y -= 4;

            if (y < 0) {
                shot.die();
            } else {
                shot.setY(y);
            }
        }

        // aliens

        for (BadSprite alien : badSprites) {

            int x = alien.getX();

            if (x >= CommonsfreezeMonster.BOARD_WIDTH - CommonsfreezeMonster.BORDER_RIGHT && direction != -1) {

                direction = -1;

                Iterator<BadSprite> i1 = badSprites.iterator();

                while (i1.hasNext()) {
                    BadSprite a2 = i1.next();
                    a2.setY(a2.getY() + CommonsfreezeMonster.GO_DOWN);
                }
            }

            if (x <= CommonsfreezeMonster.BORDER_LEFT && direction != 1) {

                direction = 1;

                Iterator<BadSprite> i2 = badSprites.iterator();

                while (i2.hasNext()) {

                    BadSprite a = i2.next();
                    a.setY(a.getY() + CommonsfreezeMonster.GO_DOWN);
                }
            }
        }

        Iterator<BadSprite> it = badSprites.iterator();

        while (it.hasNext()) {

            BadSprite alien = it.next();

            if (alien.isVisible()) {

                int y = alien.getY();

                if (y > CommonsfreezeMonster.GROUND - CommonsfreezeMonster.ALIEN_HEIGHT) {
                    inGame = false;
                    message = "Invasion!";
                }

                alien.moveX(direction);
            }
        }

        // bombs
        
        updateOtherSprites();
    }

	
    protected void updateOtherSprites() {
		Random generator = new Random();

        for (BadSprite alien : badSprites) {

            int shot = generator.nextInt(15);
            BombfreezeMonster bomb = ((BomberSpritefreezeMonster)alien).getBomb();

            if (shot == CommonsfreezeMonster.CHANCE && alien.isVisible() && bomb.isDestroyed()) {

                bomb.setDestroyed(false);
                bomb.setX(alien.getX());
                bomb.setY(alien.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = players.get(0).getX();
            int playerY = players.get(0).getY();

            if (players.get(0).isVisible() && !bomb.isDestroyed()) {

                if (bombX >= (playerX)
                        && bombX <= (playerX + CommonsfreezeMonster.PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + CommonsfreezeMonster.PLAYER_HEIGHT)) {

                    ImageIcon ii = new ImageIcon(explImg);
                    players.get(0).setImage(ii.getImage());
                    players.get(0).setDying(true);
                    bomb.setDestroyed(true);
                }
            }

            if (!bomb.isDestroyed()) {

                bomb.setY(bomb.getY() + 1);

                if (bomb.getY() >= CommonsfreezeMonster.GROUND - CommonsfreezeMonster.BOMB_HEIGHT) {

                    bomb.setDestroyed(true);
                }
            }
        }
	}    
}

