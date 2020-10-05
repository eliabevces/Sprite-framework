package spaceinvaders;

import java.awt.EventQueue;

import spriteframework.AbstractBoard;
import spriteframework.MainFrame;

public class SpaceInvadersGame extends MainFrame {


	public SpaceInvadersGame () {
		super("Space Invaders", "/images/player.png", CommonsSpaceInvaders.BOARD_WIDTH, CommonsSpaceInvaders.BOARD_HEIGHT);
	}
	
	protected  AbstractBoard createBoard() {
		return new SpaceInvadersBoard("/images/player.png", false);
	}


	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {

			new SpaceInvadersGame();
		});
	}

}
