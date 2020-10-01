package FreezeMonster;

import java.awt.EventQueue;

import spriteframework.AbstractBoard;
import spriteframework.MainFrame;

public class FreezeMonsterGame extends MainFrame {


	public FreezeMonsterGame () {
		super("Freeze Monster","/images/woody.png");
	}
	
	protected  AbstractBoard createBoard() {
		return new FreezeMonsterBoard("/images/woody.png", true);
	}


	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {

			new FreezeMonsterGame();
		});
	}

}
