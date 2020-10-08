package FreezeMonster;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
		super(playerdir, Twodimension, CommonsfreezeMonster.PLAYER_WIDTH, CommonsfreezeMonster.PLAYER_HEIGHT, CommonsfreezeMonster.BOARD_WIDTH, CommonsfreezeMonster.BOARD_HEIGHT, CommonsfreezeMonster.GROUND, CommonsfreezeMonster.backgorundColor);
		// TODO Auto-generated constructor stub
	}

	//define sprites
    //private List<BadSprite> aliens;
    private ShotfreezeMonster shot;    
    
    // define global control vars   
    private int direction = -1;
    private int deaths = 0;
    private int direc = 0;

    private String explImg = "images/explosion.png";

    protected void createBadSprites() {  // create sprites
            for (int j = 0; j < 9; j++) {
            	BomberSpritefreezeMonster alien = new BomberSpritefreezeMonster(CommonsfreezeMonster.ALIEN_INIT_X + 28 * (int)(Math.random()*10) ,
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
        for (Player player: players) {
        	player.act();
        }
        // shot
        if (shot.isVisible()) {

            int shotX = shot.getX();
            int shotY = shot.getY();
            int i = 0;
            for (BadSprite alien : badSprites) {
            	i++;
                int alienX = alien.getX();
                int alienY = alien.getY();

                if (!alien.isDyingvisible() && shot.isVisible()) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + CommonsfreezeMonster.ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + CommonsfreezeMonster.ALIEN_HEIGHT)) {
                        ImageIcon ii = new ImageIcon("images/monster"+i+"bg.png");
                        Image scaledImage = ii.getImage().getScaledInstance(CommonsfreezeMonster.ALIEN_WIDTH, CommonsfreezeMonster.ALIEN_HEIGHT, Image.SCALE_SMOOTH);
                        alien.setImage(scaledImage);
                        alien.setDyingvisible(true);
                        deaths++;
                        shot.die();
                    }
                }
            }
            int x = shot.getX();
            int y = shot.getY();
            if((players.get(0).getDy()==-1 && players.get(0).getDx()==0 && direc == 0) || direc == 1 ) { //tiro para cima
            	y -= 4;
            	direc = 1;
            }
            if((players.get(0).getDy()==1 && players.get(0).getDx()==0 && direc == 0) || direc == 2 ) { //tiro para baixo
            	y += 4;
            	direc = 2;
            }
            if((players.get(0).getDy()==0 && players.get(0).getDx()==2 && direc == 0) || direc == 3 ) { //tiro para direita
            	x += 4;
            	direc = 3;
            }
            if((players.get(0).getDy()==0 && players.get(0).getDx()==-2 && direc == 0) || direc == 4 ) { //tiro para esquerda
            	x -= 4;
            	direc = 4;
            }
            

            if (y < 0 || y >=CommonsfreezeMonster.BOARD_HEIGHT || x < 0 || x>=CommonsfreezeMonster.BOARD_WIDTH) {
                shot.die();
                direc = 0;
            } else {
                shot.setY(y);
                shot.setX(x);
            }
        }

        // aliens

        for (BadSprite alien : badSprites) {

            int x = alien.getX();
            int y = alien.getY();

            if (x >= CommonsfreezeMonster.BOARD_WIDTH - CommonsfreezeMonster.BORDER_RIGHT && !alien.isDyingvisible()) {
                
            	int temp = new Random().nextInt(3);
                if(temp == 0) {
                	alien.setDirecaomonstro(2);
                }
                if(temp == 1) {
                	alien.setDirecaomonstro(7);
                }
                if(temp == 2) {
                	alien.setDirecaomonstro(3);
                }
          
            }

            if (x <= CommonsfreezeMonster.BORDER_LEFT && !alien.isDyingvisible()) {
            	int temp = new Random().nextInt(3);
                if(temp == 0) {
                	alien.setDirecaomonstro(4);
                }
                if(temp == 1) {
                	alien.setDirecaomonstro(5);
                }
                if(temp == 2) {
                	alien.setDirecaomonstro(1);
                }
            }
            if (y >= CommonsfreezeMonster.BOARD_HEIGHT - 50 && !alien.isDyingvisible()) {
                int temp = new Random().nextInt(3);
                if(temp == 0) {
                	alien.setDirecaomonstro(2);
                }
                if(temp == 1) {
                	alien.setDirecaomonstro(8);
                }
                if(temp == 2) {
                	alien.setDirecaomonstro(4);
                }
          
            }

            if (y <= 0 && !alien.isDyingvisible()) {

            	int temp = new Random().nextInt(3);
                if(temp == 0) {
                	alien.setDirecaomonstro(3);
                }
                if(temp == 1) {
                	alien.setDirecaomonstro(6);
                }
                if(temp == 2) {
                	alien.setDirecaomonstro(1);
                }
            }
        }

        Iterator<BadSprite> it = badSprites.iterator();
       
        while (it.hasNext()) {

            BadSprite alien = it.next();
            if(alien.getDirecaomonstro()==0) {
            	alien.setDirecaomonstro(new Random().nextInt(8)+1);
            }
            if (!alien.isDyingvisible()) {

                int y = alien.getY();


                if(alien.getDirecaomonstro() == 1) {
                	alien.moveX(1);
                    alien.moveY(1);
            	}
                if(alien.getDirecaomonstro() == 2) {
                	alien.moveX(-1);
                    alien.moveY(-1);
            	}
                if(alien.getDirecaomonstro() == 3) {
                	alien.moveX(-1);
                    alien.moveY(1);
            	}
                if(alien.getDirecaomonstro() == 4) {
                	alien.moveX(1);
                    alien.moveY(-1);
            	}
                if(alien.getDirecaomonstro() == 5) {
                	alien.moveX(1);
            	}
                if(alien.getDirecaomonstro() == 6) {
                    alien.moveY(1);
            	}
                if(alien.getDirecaomonstro() == 7) {
                	alien.moveX(-1);
            	}
                if(alien.getDirecaomonstro() == 8) {
                    alien.moveY(-1);
            	}
                    
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
            
            if(bomb.isDirecao() == 0) {
                switch (alien.getDirecaomonstro()){
                    case 1: bomb.setDirecao(3); break;
                    case 2: bomb.setDirecao(8); break;
                    case 3: bomb.setDirecao(2); break;
                    case 4: bomb.setDirecao(7); break;
                    case 5: bomb.setDirecao(4); break;
                    case 6: bomb.setDirecao(5); break;
                    case 7: bomb.setDirecao(6); break;
                    case 8: bomb.setDirecao(1); break;
                    default: bomb.setDirecao(new Random().nextInt(8)+1); break;
                }
            }
            if (shot == CommonsfreezeMonster.CHANCE && !(alien.isDyingvisible()) && bomb.isDestroyed()) {
                bomb.setDestroyed(false);
                bomb.setX(alien.getX());
                bomb.setY(alien.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = players.get(0).getX();
            int playerY = players.get(0).getY();
            int alienX = alien.getX();
            int alienY = alien.getY();
            if (players.get(0).isVisible() && !bomb.isDestroyed()) {

                if (bombX >= (playerX)
                        && bombX <= (playerX + CommonsfreezeMonster.PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + CommonsfreezeMonster.PLAYER_HEIGHT) ||
                        (alienX >= (playerX)
                        && alienX <= (playerX + CommonsfreezeMonster.PLAYER_WIDTH)
                        && alienY >= (playerY)
                        && alienY <= (playerY + CommonsfreezeMonster.PLAYER_HEIGHT) ) ){

                    ImageIcon ii = new ImageIcon(explImg);
                    players.get(0).setImage(ii.getImage());
                    players.get(0).setDying(true);
                    bomb.setDestroyed(true);
                }
            }

            if (!bomb.isDestroyed()) {
            	
            	if(bomb.isDirecao() == 1) {
            		bomb.setY(bomb.getY() - 1);
                    bomb.setX(bomb.getX() - 1);
            	}
            	if(bomb.isDirecao() == 2) {
            		bomb.setY(bomb.getY() + 1);
                    bomb.setX(bomb.getX() - 1);
            	}
            	if(bomb.isDirecao() == 3) {
            		bomb.setY(bomb.getY() - 1);
                    bomb.setX(bomb.getX() + 1);
            	}
            	if(bomb.isDirecao() == 4) {
            		bomb.setY(bomb.getY() + 1);
                    bomb.setX(bomb.getX() + 1);
            	}
            	if(bomb.isDirecao() == 5) {
            		bomb.setY(bomb.getY());
                    bomb.setX(bomb.getX() - 1);
            	}
            	if(bomb.isDirecao() == 6) {
            		bomb.setY(bomb.getY() - 1);
                    bomb.setX(bomb.getX());
            	}
            	if(bomb.isDirecao() == 7) {
            		bomb.setY(bomb.getY());
                    bomb.setX(bomb.getX() + 1);
            	}
            	if(bomb.isDirecao() == 8) {
            		bomb.setY(bomb.getY() + 1);
                    bomb.setX(bomb.getX());
            	}
            	
                
                if (bomb.getY() >= CommonsfreezeMonster.GROUND - CommonsfreezeMonster.BOMB_HEIGHT || bomb.getY()<= 0 || bomb.getX() <= 0 || bomb.getX() >=  CommonsfreezeMonster.BOARD_WIDTH) {

                    bomb.setDestroyed(true);
                }
            }
        }
	}    
}

