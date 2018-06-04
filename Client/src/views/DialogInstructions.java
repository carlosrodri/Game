package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import constants.ConstantsUI;

public class DialogInstructions extends JDialog{
	
	private static final long serialVersionUID = 1L;
	private JLabel lbTitle, lbHabilities, lbImgAbility, lbMovement;
	private JPanel panelCneter;
	
	public DialogInstructions() {
		setSize(ConstantsUI.DIMENSION_DIALOG_INIT);
		setBackground(Color.WHITE);
		
		lbTitle = new JLabel(ConstantsUI.INSTRUCTIONS_WORD);
		lbTitle.setForeground(ConstantsUI.RED_COLOR_AVENGERS);
		lbTitle.setHorizontalAlignment(0);
		lbTitle.setFont(ConstantsUI.FONT_TITLE);
		add(lbTitle, BorderLayout.NORTH);
		
		lbHabilities = new JLabel(ConstantsUI.HABILITIES_TEXT);
		lbHabilities.setForeground(ConstantsUI.RED_COLOR_AVENGERS);
		lbHabilities.setHorizontalAlignment(0);
		lbHabilities.setFont(ConstantsUI.FONT_TEXT);
		
		lbImgAbility = new JLabel(new ImageIcon(getClass().getResource("/img/abilities.png")));
		
		Image i = new ImageIcon(getClass().getResource("/img/movement.jpg")).getImage().getScaledInstance(200, 150, 10);
		
		lbMovement = new JLabel(new ImageIcon(i));
		
		panelCneter = new JPanel();
		panelCneter.setLayout(new GridLayout(3,1));
		panelCneter.add(lbHabilities);
		panelCneter.add(lbImgAbility);
		panelCneter.add(lbMovement);
		add(new JScrollPane(panelCneter), BorderLayout.CENTER);
	}
	
	public void visibleDialogInstructions (boolean option) {
		setVisible(option);
	}
}
