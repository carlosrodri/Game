package views;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import constants.ConstantsUI;
import controllers.Controller;
import controllers.MyActions;

public class DialogLoggin extends JDialog{

	private static final long serialVersionUID = 1L;
	private JButton btnInstrusctions, btnPlay, btnMoreInfo, btnExit;
	private JLabel lbIcon, lbTitle;
	
	public DialogLoggin(Controller controller) {
		GridSystem gridSystem = new GridSystem(this);
		setSize(ConstantsUI.DIMENSION_DIALOG_INIT_I);
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);
		
		lbTitle = new JLabel(ConstantsUI.TITLE_GAME);
		lbTitle.setHorizontalAlignment(0);
		lbTitle.setFont(ConstantsUI.FONT_LETTER);
		lbTitle.setForeground(ConstantsUI.COLOR_AVENGERS_LOGO);
		lbTitle.setBackground(Color.WHITE);
		add(lbTitle, gridSystem.insertComponent(0, 0, 12, 0.01));
		
		lbIcon = new JLabel(new ImageIcon(getClass().getResource("/img/avengers.png")));
		add(lbIcon, gridSystem.insertComponent(1, 1, 10, 0.1));
		
		btnInstrusctions = new JButton(ConstantsUI.INSTRUCTIONS_WORD);
		btnInstrusctions.setFont(ConstantsUI.FONT_LETTER);
		btnInstrusctions.setForeground(Color.WHITE);
		btnInstrusctions.setActionCommand(MyActions.INSTRUCTIONS.toString());
		btnInstrusctions.addActionListener(controller);
		btnInstrusctions.setBackground(ConstantsUI.COLOR_AVENGERS_LOGO);
		btnInstrusctions.setBorder(null);
		btnInstrusctions.setBorder(new LineBorder(Color.WHITE, 5));
		add(btnInstrusctions, gridSystem.insertComponent(2, 1, 10, 0.01));
		
		btnPlay = new JButton(ConstantsUI.PLAY_WORD);
		btnPlay.setFont(ConstantsUI.FONT_LETTER);
		btnPlay.setForeground(Color.WHITE);
		btnPlay.setBackground(ConstantsUI.COLOR_AVENGERS_LOGO);
		btnPlay.setActionCommand(MyActions.PLAY.toString());
		btnPlay.setBorder(null);
		btnPlay.addActionListener(controller);
		btnPlay.setBorder(new LineBorder(Color.WHITE, 5));
		add(btnPlay, gridSystem.insertComponent(4, 1, 10, 0.01));
		
		btnMoreInfo = new JButton(ConstantsUI.MOREINFO_WORD);
		btnMoreInfo.setFont(ConstantsUI.FONT_LETTER);
		btnMoreInfo.setForeground(Color.WHITE);
		btnMoreInfo.setBackground(ConstantsUI.COLOR_AVENGERS_LOGO);
		btnMoreInfo.setActionCommand(MyActions.MOREINFO.toString());
		btnMoreInfo.addActionListener(controller);
		btnMoreInfo.setBorder(null);
		btnMoreInfo.setBorder(new LineBorder(Color.WHITE, 5));
		add(btnMoreInfo, gridSystem.insertComponent(6, 1, 10, 0.01));
		
		btnExit = new JButton(ConstantsUI.EXIT_WORD);
		btnExit.setFont(ConstantsUI.FONT_LETTER);
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(ConstantsUI.COLOR_AVENGERS_LOGO);
		btnExit.setActionCommand(MyActions.EXIT.toString());
		btnExit.addActionListener(controller);
		btnExit.setBorder(null);
		btnExit.setBorder(new LineBorder(Color.WHITE, 5));
		add(btnExit, gridSystem.insertComponent(6, 1, 10, 0.01));
		
		setVisible(true);
	}
}
