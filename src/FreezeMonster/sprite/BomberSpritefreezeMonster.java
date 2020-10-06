package FreezeMonster.sprite;

import java.util.LinkedList;

import java.awt.Image;

import javax.swing.ImageIcon;

import spriteframework.sprite.BadSprite;
import spriteframework.sprite.BadnessBoxSprite;

public class BomberSpritefreezeMonster extends BadnessBoxSprite {

    private BombfreezeMonster bomb;

    public BomberSpritefreezeMonster(int x, int y) {

        initBomber(x, y);
    }

    private void initBomber(int x, int y) {

        this.x = x;
        this.y = y;

        bomb = new BombfreezeMonster(x, y);

        String alienImg = "images/monster1.png";  // colocar codigo de figura de escala antes de mudar a imagem, se n�o fica muito grande
        ImageIcon ii = new ImageIcon(alienImg);
        Image scaledImage = ii.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
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
