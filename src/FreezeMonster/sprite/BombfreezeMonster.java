package FreezeMonster.sprite;

import javax.swing.ImageIcon;

import FreezeMonster.CommonsfreezeMonster;

import java.awt.Image;
import spriteframework.sprite.BadSprite;

public class BombfreezeMonster extends BadSprite {

    private boolean destroyed;
    private int direcao=0;

    

	public BombfreezeMonster(int x, int y) {

        initBomb(x, y);
    }

    private void initBomb(int x, int y) {

        setDestroyed(true);

        this.x = x;
        this.y = y;

        String bombImg = "images/gosma.png"; // colocar codigo de figura de escala antes de mudar a imagem, se n�o fica muito grande
        ImageIcon ii = new ImageIcon(bombImg);
        Image scaledImage = ii.getImage().getScaledInstance(CommonsfreezeMonster.ALIEN_WIDTH, CommonsfreezeMonster.ALIEN_HEIGHT, Image.SCALE_SMOOTH);
        setImage(scaledImage);
    }

    public void setDestroyed(boolean destroyed) {

        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {

        return destroyed;
    }
    
    public int isDirecao() {
		return direcao;
	}

	public void setDirecao(int direcao) {
		this.direcao = direcao;
	}
    
    
}
