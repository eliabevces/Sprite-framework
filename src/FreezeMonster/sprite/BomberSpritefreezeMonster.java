package FreezeMonster.sprite;

import java.util.LinkedList;

import java.awt.Image;

import javax.swing.ImageIcon;

import FreezeMonster.CommonsfreezeMonster;
import spriteframework.sprite.BadSprite;
import spriteframework.sprite.BadnessBoxSprite;

public class BomberSpritefreezeMonster extends BadnessBoxSprite {

    private BombfreezeMonster bomb;

    public BomberSpritefreezeMonster(int x, int y, int j) {

        initBomber(x, y, j);
    }

    private void initBomber(int x, int y, int j) {

        this.x = x;
        this.y = y;

        bomb = new BombfreezeMonster(x, y);
        String alienImg = "images/monster"+(j+1)+".png";  // colocar codigo de figura de escala antes de mudar a imagem, se n�o fica muito grande
        ImageIcon ii = new ImageIcon(alienImg);
        Image scaledImage = ii.getImage().getScaledInstance(CommonsfreezeMonster.ALIEN_WIDTH, CommonsfreezeMonster.ALIEN_HEIGHT, Image.SCALE_SMOOTH);
        setImage(scaledImage);
    }



    public BombfreezeMonster getBomb() {

        return bomb;
    }


	@Override
	public LinkedList<BadSprite> getBadnesses() {
		LinkedList<BadSprite> aBomb = new LinkedList<BadSprite>();
		aBomb.add(bomb);
		return aBomb;
	}
}
