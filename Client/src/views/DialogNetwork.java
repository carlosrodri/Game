package views;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import constants.ConstantsUI;
import controllers.Controller;
import controllers.MyActions;

public class DialogNetwork extends JDialog{

	private static final long serialVersionUID = 1L;
	private JTextField tfIp, tfName;
	private JFormattedTextField tfPort;
	private JButton btnAccept;
	private JLabel lbTitle;
	
	public DialogNetwork(Controller controller) {
		setSize(ConstantsUI.DIMENSION_DIALOG_INIT_I);
		this.getContentPane().setBackground(ConstantsUI.COLOR_NETWORK_DIALOG);
		setLocationRelativeTo(null);
		GridSystem gridSystem = new GridSystem(this);
		
		lbTitle = new JLabel(ConstantsUI.TITLE_NETWORK);
		lbTitle.setHorizontalAlignment(0);
		lbTitle.setFont(ConstantsUI.FONT_LETTER);
		lbTitle.setForeground(Color.WHITE);
		add(lbTitle, gridSystem.insertComponent(0, 1, 11, 0.01));
		
		tfName = new JTextField();
		tfName.setBorder(new TitledBorder("Your name or nickname"));
		tfName.setBackground(ConstantsUI.COLOR_NETWORK_DIALOG);
		add(tfName, gridSystem.insertComponent(1, 1, 11, 0.01));
		
		tfIp = new JTextField();
		tfIp.setBorder(new TitledBorder("Ip direction"));
		tfIp.setText("localhost");
		tfIp.setBackground(ConstantsUI.COLOR_NETWORK_DIALOG);
		tfIp.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				tfIp.setText("");
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tfIp.setText("");
			}
		});
		add(tfIp, gridSystem.insertComponent(2, 1, 11, 0.01));
		
		tfPort = new JFormattedTextField(new Integer(10000));
		tfPort.setBorder(new TitledBorder("Select the port of conection"));
		tfPort.setText("2000");
		tfPort.setBackground(ConstantsUI.COLOR_NETWORK_DIALOG);
		tfPort.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				tfPort.setText("");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				tfPort.setText("");
			}
		});
		add(tfPort, gridSystem.insertComponent(3, 1, 11, 0.01));
		
		btnAccept = new JButton(ConstantsUI.ACCEPT);
		btnAccept.setActionCommand(MyActions.ACCEPT_REGISTRY.toString());
		btnAccept.addActionListener(controller);
		btnAccept.setBackground(Color.WHITE);
		btnAccept.setBorder(null);
		btnAccept.setBorder(new LineBorder(Color.BLACK));
		btnAccept.setBackground(ConstantsUI.COLOR_NETWORK_DIALOG);
		add(btnAccept, gridSystem.insertComponent(4, 4, 5, 0.01));
	}

	public String getIp() {
		return tfIp.getText();
	}
	
	public int getPort() {
		return Integer.parseInt(tfPort.getText());
	}
	
	public String getName() {
		return tfName.getText();
	}
	
	public void setDatas() {
		tfName.setText("");
	}
}	
