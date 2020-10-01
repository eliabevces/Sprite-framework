package FreezeMonster.sprite;

import javax.swing.ImageIcon;

import spriteframework.sprite.BadSprite;

public class BombfreezeMonster extends BadSprite {

    private boolean destroyed;

    public BombfreezeMonster(int x, int y) {

        initBomb(x, y);
    }

    private void initBomb(int x, int y) {

        setDestroyed(true);

        this.x = x;
        this.y = y;

        String bombImg = "images/bomb.png"; // colocar codigo de figura de escala antes de mudar a imagem, se não fica muito grande
        ImageIcon ii = new ImageIcon(bombImg);
        setImage(ii.getImage());
    }

    public void setDestroyed(boolean destroyed) {

        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {

        return destroyed;
    }
    
    
}
