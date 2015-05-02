package br.com.softctrl.pos.gui.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.text.MaskFormatter;

import br.com.softctrl.pos.control.login.LoginControl;
import br.com.softctrl.pos.gui.main.SCGUIMainForm;
import br.com.softctrl.sys029.model.User;

/**
 * 
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 14, 2012 9:09:13 AM
 * 
 */
public class SCGUILogin extends br.com.softctrl.util.gui.dialog.SCGUIDialogApp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5788101294481560457L;

	// Estruturar para melhorar o desempenho
	private final Font FONT_LUCIDA_GRANDE_BOLD_13 = new Font("Lucida Grande",
			Font.BOLD, 13);
	private final Font FONT_LUCIDA_GRANDE_BOLD_16 = new Font("Lucida Grande",
			Font.BOLD, 16);
	private final Font FONT_LUCIDA_GRANDE_BOLD_ITALIC_32 = new Font(
			"Lucida Grande", Font.BOLD | Font.ITALIC, 32);

	private final LoginControl loginControl = new LoginControl();

	private JPanel contentPane = null;
	private JFormattedTextField jFtfUserCode = null;
	private JPasswordField jPwfUserPassword = null;
	private JButton jBtnLogin = null;

	private static final int MAX_LETTERS_USER_CODE = 30;
	private static final int MAX_LETTERS_USER_PASS = MAX_LETTERS_USER_CODE;

	private static MaskFormatter maskNumbers = null;
	static {
		try {
			maskNumbers = new MaskFormatter("###");
			maskNumbers.setValidCharacters("0123456789");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public boolean canLogin() {
		return (jFtfUserCode.getText().length() > 0 && jPwfUserPassword
				.getPassword().length > 0);
	}

	private final KeyAdapter keyEnterAdapterListener = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent kevt) {

			if ((kevt.getKeyCode() == KeyEvent.VK_ENTER) && canLogin()) {

				jBtnLogin.doClick();

			}

		}
	};

	private final ActionListener loginActionListener = new ActionListener() {

		public void actionPerformed(ActionEvent actEvt) {

			if (canLogin()) {

				User user = loginControl.login(jFtfUserCode.getText(),
						String.valueOf(jPwfUserPassword.getPassword()));
				if (user != null) {

					try {
						getApp().setLoggedIn(user, new Date());
						SCGUIMainForm form = new SCGUIMainForm();
						form.showMe(false);
						close();
					} catch (ParseException e) {
						System.err.println(getString("INEXISTENT_USER"));
						e.printStackTrace();
					}

				} else {
					System.err.println(getString("INEXISTENT_USER"));
				}

			}

		}
	};

	private final ActionListener onButtonNumberClickedListener = new ActionListener() {

		public void actionPerformed(ActionEvent actEvt) {

			char letter = ((JButton) actEvt.getSource()).getText()
					.toCharArray()[0];

			if (jFtfUserCode.isFocusOwner()) {
				int count = (jFtfUserCode.getText() == null ? 0 : jFtfUserCode
						.getText().trim().length());
				if (count < MAX_LETTERS_USER_CODE) {
					jFtfUserCode.setText(String.format("%s%c", jFtfUserCode
							.getText().trim(), letter));
				}
			} else if (jPwfUserPassword.isFocusOwner()) {
				int count = (jPwfUserPassword.getPassword() == null ? 0
						: jPwfUserPassword.getPassword().length);
				if (count < MAX_LETTERS_USER_PASS) {
					jPwfUserPassword.setText(String.format("%s%c",
							String.valueOf(jPwfUserPassword.getPassword()),
							letter));
				}
			}

		}
	};

	private final ActionListener onButtonClearClickedListener = new ActionListener() {

		public void actionPerformed(ActionEvent actEvt) {

			jFtfUserCode.setText("");
			jPwfUserPassword.setText("");

		}
	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SCGUILogin frame = new SCGUILogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SCGUILogin() {
		setResizable(false);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel jPnlRigth = new JPanel();
		contentPane.add(jPnlRigth, BorderLayout.EAST);

		JPanel jPnlRigthAll = new JPanel();
		GroupLayout gl_jPnlRigth = new GroupLayout(jPnlRigth);
		gl_jPnlRigth.setHorizontalGroup(gl_jPnlRigth.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_jPnlRigth
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPnlRigthAll, GroupLayout.PREFERRED_SIZE,
								331, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		gl_jPnlRigth.setVerticalGroup(gl_jPnlRigth.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_jPnlRigth
						.createSequentialGroup()
						.addGap(5)
						.addComponent(jPnlRigthAll, GroupLayout.DEFAULT_SIZE,
								503, Short.MAX_VALUE).addContainerGap()));
		jPnlRigthAll.setLayout(null);

		JLabel jLblEnterUserCode = new JLabel(getString("ENTER_YOUR_CODE"));
		jLblEnterUserCode.setFont(FONT_LUCIDA_GRANDE_BOLD_13);
		jLblEnterUserCode.setBounds(6, 6, 220, 16);
		jPnlRigthAll.add(jLblEnterUserCode);

		JLabel jLblEnterUserPassword = new JLabel(
				getString("ENTER_YOUR_PASSWORD"));
		jLblEnterUserPassword.setFont(FONT_LUCIDA_GRANDE_BOLD_13);
		jLblEnterUserPassword.setBounds(6, 74, 220, 16);
		jPnlRigthAll.add(jLblEnterUserPassword);

		jFtfUserCode = new JFormattedTextField(maskNumbers);
		jFtfUserCode.addKeyListener(keyEnterAdapterListener);
		jFtfUserCode.setHorizontalAlignment(SwingConstants.CENTER);
		jFtfUserCode.setFont(FONT_LUCIDA_GRANDE_BOLD_16);
		jFtfUserCode.setBounds(6, 34, 319, 30);
		jPnlRigthAll.add(jFtfUserCode);
		jPnlRigth.setLayout(gl_jPnlRigth);

		jPwfUserPassword = new JPasswordField();
		jPwfUserPassword.setHorizontalAlignment(SwingConstants.CENTER);
		jPwfUserPassword.addKeyListener(keyEnterAdapterListener);
		jPwfUserPassword.setFont(FONT_LUCIDA_GRANDE_BOLD_16);
		jPwfUserPassword.setBounds(6, 102, 319, 30);
		jPnlRigthAll.add(jPwfUserPassword);

		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(6, 145, 311, 415);
		jPnlRigthAll.add(panel);
		panel.setLayout(null);

		JButton jBtnNumber7 = new JButton("7");
		jBtnNumber7.addActionListener(onButtonNumberClickedListener);
		jBtnNumber7.setFont(FONT_LUCIDA_GRANDE_BOLD_ITALIC_32);
		jBtnNumber7.setMnemonic('7');
		jBtnNumber7.setBounds(9, 6, 90, 90);
		jBtnNumber7.setFocusable(false);
		panel.add(jBtnNumber7);

		JButton jBtnNumber8 = new JButton("8");
		jBtnNumber8.addActionListener(onButtonNumberClickedListener);
		jBtnNumber8.setMnemonic('8');
		jBtnNumber8.setFont(FONT_LUCIDA_GRANDE_BOLD_ITALIC_32);
		jBtnNumber8.setBounds(111, 6, 90, 90);
		jBtnNumber8.setFocusable(false);
		panel.add(jBtnNumber8);

		JButton jBtnNumber9 = new JButton("9");
		jBtnNumber9.addActionListener(onButtonNumberClickedListener);
		jBtnNumber9.setMnemonic('9');
		jBtnNumber9.setFont(FONT_LUCIDA_GRANDE_BOLD_ITALIC_32);
		jBtnNumber9.setBounds(213, 6, 90, 90);
		jBtnNumber9.setFocusable(false);
		panel.add(jBtnNumber9);

		JButton jBtnNumber4 = new JButton("4");
		jBtnNumber4.addActionListener(onButtonNumberClickedListener);
		jBtnNumber4.setMnemonic('4');
		jBtnNumber4.setFont(FONT_LUCIDA_GRANDE_BOLD_ITALIC_32);
		jBtnNumber4.setBounds(9, 108, 90, 90);
		jBtnNumber4.setFocusable(false);
		panel.add(jBtnNumber4);

		JButton jBtnNumber5 = new JButton("5");
		jBtnNumber5.addActionListener(onButtonNumberClickedListener);
		jBtnNumber5.setMnemonic('5');
		jBtnNumber5.setFont(FONT_LUCIDA_GRANDE_BOLD_ITALIC_32);
		jBtnNumber5.setBounds(111, 108, 90, 90);
		jBtnNumber5.setFocusable(false);
		panel.add(jBtnNumber5);

		JButton jBtnNumber6 = new JButton("6");
		jBtnNumber6.addActionListener(onButtonNumberClickedListener);
		jBtnNumber6.setMnemonic('6');
		jBtnNumber6.setFont(FONT_LUCIDA_GRANDE_BOLD_ITALIC_32);
		jBtnNumber6.setBounds(213, 108, 90, 90);
		jBtnNumber6.setFocusable(false);
		panel.add(jBtnNumber6);

		JButton jBtnNumber0 = new JButton("0");
		jBtnNumber0.addActionListener(onButtonNumberClickedListener);
		jBtnNumber0.setMnemonic('0');
		jBtnNumber0.setFont(FONT_LUCIDA_GRANDE_BOLD_ITALIC_32);
		jBtnNumber0.setBounds(9, 304, 90, 90);
		jBtnNumber0.setFocusable(false);
		panel.add(jBtnNumber0);

		JButton jBtnClear = new JButton(getString("CLEAR_FIELDS"));
		jBtnClear.addActionListener(onButtonClearClickedListener);
		jBtnClear.setFont(FONT_LUCIDA_GRANDE_BOLD_ITALIC_32);
		jBtnClear.setBounds(104, 304, 199, 90);
		jBtnClear.setFocusable(false);
		panel.add(jBtnClear);

		JButton jBtnNumber1 = new JButton("1");
		jBtnNumber1.addActionListener(onButtonNumberClickedListener);
		jBtnNumber1.setMnemonic('1');
		jBtnNumber1.setFont(FONT_LUCIDA_GRANDE_BOLD_ITALIC_32);
		jBtnNumber1.setBounds(9, 210, 90, 90);
		jBtnNumber1.setFocusable(false);
		panel.add(jBtnNumber1);

		JButton jBtnNumber2 = new JButton("2");
		jBtnNumber2.addActionListener(onButtonNumberClickedListener);
		jBtnNumber2.setMnemonic('2');
		jBtnNumber2.setFont(FONT_LUCIDA_GRANDE_BOLD_ITALIC_32);
		jBtnNumber2.setBounds(111, 210, 90, 90);
		jBtnNumber2.setFocusable(false);
		panel.add(jBtnNumber2);

		JButton jBtnNumber3 = new JButton("3");
		jBtnNumber3.addActionListener(onButtonNumberClickedListener);
		jBtnNumber3.setMnemonic('3');
		jBtnNumber3.setFont(FONT_LUCIDA_GRANDE_BOLD_ITALIC_32);
		jBtnNumber3.setBounds(213, 210, 90, 90);
		jBtnNumber3.setFocusable(false);
		panel.add(jBtnNumber3);

		jBtnLogin = new JButton(getString("LOGIN"));
		jBtnLogin.addActionListener(loginActionListener);
		jBtnLogin
				.setIcon(new ImageIcon(
						SCGUILogin.class
								.getResource("/com/jgoodies/looks/windows/icons/Computer.gif")));
		jBtnLogin.setFont(FONT_LUCIDA_GRANDE_BOLD_ITALIC_32);
		jBtnLogin.setFocusable(false);
		jBtnLogin.setBounds(6, 572, 311, 60);
		jPnlRigthAll.add(jBtnLogin);

		JButton jBtnLogout = new JButton(getString("LOGOUT"));
		jBtnLogout.setIcon(new ImageIcon(SCGUILogin.class
				.getResource("/com/jgoodies/looks/plastic/icons/Error.png")));
		jBtnLogout.setFont(FONT_LUCIDA_GRANDE_BOLD_ITALIC_32);
		jBtnLogout.setFocusable(false);
		jBtnLogout.setBounds(6, 636, 311, 60);
		jPnlRigthAll.add(jBtnLogout);

	}

	@Override
	public void showMe(boolean modal) {

		this.setResizable(false);
		this.setSize(getDefaultToolKit().getScreenSize());
		this.setAlwaysOnTop(modal);
		this.setVisible(true);

	}
}
