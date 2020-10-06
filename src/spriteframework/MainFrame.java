package spriteframework;

import javax.swing.JFrame;

public abstract class MainFrame extends JFrame  {

	private static final long serialVersionUID = 1L;

	// passar todos os objetos unicos por aq
    // hotspot
    protected abstract AbstractBoard createBoard();
    
    public MainFrame(String t, String playerimagedir, int boardWidth, int boardHeight) {
          
        add(createBoard());
		
		setTitle(t);
		setSize(boardWidth, boardHeight);
		
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
