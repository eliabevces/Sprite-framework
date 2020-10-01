package spriteframework;

import javax.swing.JFrame;

public abstract class MainFrame extends JFrame  {
	//passar todos os objetos unicos por aq
    // hotspot
    protected abstract AbstractBoard createBoard();
    
    public MainFrame(String t, String playerimagedir) {
          
        add(createBoard());
		
		setTitle(t);
		setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
    }


//    public static void main(String[] args) {
//
//        EventQueue.invokeLater(() -> {
//
//            MainFrameExtended ex = new MainFrameExtended();
//        });
//    }
    
}
