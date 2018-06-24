package views;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private JLabel lbInfo;
	
	public MainWindow() {
		setSize(200, 150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		lbInfo = new JLabel("Server online");
		add(lbInfo);
	}

}
