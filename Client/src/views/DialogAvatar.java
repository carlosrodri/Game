package views;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import constants.ConstantsUI;
import controllers.Controller;
import controllers.MyActions;

public class DialogAvatar extends JDialog{

	private static final long serialVersionUID = 1L;
	private JButton btnCap, btnHulk, btnDead, btnSpider, btnGreen, btnGrood;

	public DialogAvatar(Controller controller) {
		setSize(ConstantsUI.DIMENSION_AVATAR_DIALOG);
		setLayout(new GridLayout(2, 3));
		setTitle(ConstantsUI.CHOOSE_HERO);

		btnCap = new JButton(new ImageIcon(getClass().getResource("/img/capitan.png")));
		btnCap.setName("cap");
		btnCap.setActionCommand(MyActions.CHOOSE.toString());
		btnCap.addActionListener(controller);
		btnCap.setBackground(Color.WHITE);
		add(btnCap);
		
		btnHulk = new JButton(new ImageIcon(getClass().getResource("/img/hulk.png")));
		btnHulk.setName("hulk");
		btnHulk.setActionCommand(MyActions.CHOOSE.toString());
		btnHulk.addActionListener(controller);
		btnHulk.setBackground(Color.WHITE);
		add(btnHulk);
		
		btnDead = new JButton(new ImageIcon(getClass().getResource("/img/deadpool.png")));
		btnDead.setName("deadpool");
		btnDead.setActionCommand(MyActions.CHOOSE.toString());
		btnDead.addActionListener(controller);
		btnDead.setBackground(Color.WHITE);
		add(btnDead);
		
		btnSpider = new JButton(new ImageIcon(getClass().getResource("/img/spiderman.png")));
		btnSpider.setActionCommand(MyActions.CHOOSE.toString());
		btnSpider.setName("spider");
		btnSpider.addActionListener(controller);
		btnSpider.setBackground(Color.WHITE);
		add(btnSpider);
		
		btnGreen = new JButton(new ImageIcon(getClass().getResource("/img/greenlatern.png")));
		btnGreen.setName("green");
		btnGreen.setActionCommand(MyActions.CHOOSE.toString());
		btnGreen.addActionListener(controller);
		btnGreen.setBackground(Color.WHITE);
		add(btnGreen);
		
		btnGrood = new JButton(new ImageIcon(getClass().getResource("/img/groot.png")));
		btnGrood.setName("groot");
		btnGrood.setActionCommand(MyActions.CHOOSE.toString());
		btnGrood.addActionListener(controller);
		btnGrood.setBackground(Color.WHITE);
		add(btnGrood);

	}
}
