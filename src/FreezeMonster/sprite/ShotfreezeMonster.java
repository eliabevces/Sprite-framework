package FreezeMonster.sprite;

import javax.swing.ImageIcon;

import spriteframework.sprite.BadSprite;


public class ShotfreezeMonster extends BadSprite {

    public ShotfreezeMonster() {
    }

    public ShotfreezeMonster(int x, int y) {

        initShot(x, y);
    }

    private void initShot(int x, int y) {

        String shotImg = "images/shot.png";  // colocar codigo de figura de escala antes de mudar a imagem, se não fica muito grande
        ImageIcon ii = new ImageIcon(shotImg);
        setImage(ii.getImage());

        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
}
