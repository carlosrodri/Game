package views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import constants.ConstantsUI;

public class DialogMoreInfo extends JDialog{
	
	private static final long serialVersionUID = 1L;
	private JLabel lbTitle, lbLetter;
	
	public DialogMoreInfo() {
		setSize(ConstantsUI.DIMENSION_DIALOG_INIT);
		this.getContentPane().setBackground(ConstantsUI.RED_COLOR_AVENGERS);
		setLocationRelativeTo(null);
		
		lbTitle = new JLabel(ConstantsUI.HISTORY_WORD);
		lbTitle.setForeground(Color.WHITE);
		lbTitle.setHorizontalAlignment(0);
		lbTitle.setFont(ConstantsUI.FONT_TITLE);
		lbTitle.setBackground(ConstantsUI.RED_COLOR_AVENGERS);
		lbTitle.setOpaque(true);
		add(lbTitle, BorderLayout.NORTH);
		
		lbLetter = new JLabel(ConstantsUI.INSTRUCTIONS_TEXT);
		lbLetter.setForeground(Color.WHITE);
		lbLetter.setFont(ConstantsUI.FONT_TEXT);
		lbLetter.setHorizontalTextPosition(0);
		lbLetter.setBackground(ConstantsUI.RED_COLOR_AVENGERS);
		lbLetter.setOpaque(true);
		add(new JScrollPane(lbLetter), BorderLayout.CENTER);
	}
	
	public void visibleDialogMoreInfo (boolean option) {
		setVisible(option);
	}
}
