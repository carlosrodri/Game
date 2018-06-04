package views;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import constants.ConstantsUI;
import controllers.Controller;
import controllers.MyActions;

public class DialogAvatar extends JDialog{

	private static final long serialVersionUID = 1L;
	private JLabel lbAvatars;
	private JSpinner spSelection;
	private JButton btnAcceptAvatar;

	public DialogAvatar(Controller controller) {
		GridSystem gridSystem = new GridSystem(this);
		setSize(ConstantsUI.DIMENSION_AVATAR_DIALOG);

		lbAvatars = new JLabel(new ImageIcon(getClass().getResource("/img/heros.png")));
		lbAvatars.setBorder(new TitledBorder(ConstantsUI.CHOOSE_HERO));
		add(lbAvatars, gridSystem.insertComponent(0, 0, 12, 0.1));

		spSelection = new JSpinner(new SpinnerNumberModel(1, 1, 6, 1));
		add(spSelection, gridSystem.insertComponent(1, 4, 4, 0.01));

		btnAcceptAvatar = new JButton(ConstantsUI.ACCEPT);
		btnAcceptAvatar.setActionCommand(MyActions.CHOOSE.toString());
		btnAcceptAvatar.addActionListener(controller);
		add(btnAcceptAvatar, gridSystem.insertComponent(2, 0, 12, 0.01));

	}

	public String getHero() {
		switch (Integer.parseInt(spSelection.getValue().toString())) {
		case 1:
			return ConstantsUI.CAP;
		case 2:
			return ConstantsUI.SPIDER;
		case 3:
			return ConstantsUI.HULK;
		case 4:
			return ConstantsUI.GROOT;
		case 5:
			return ConstantsUI.POOL;
		case 6:
			return ConstantsUI.LATERN;
		}
		return ConstantsUI.GUNMAN_IMG;
	}
}
