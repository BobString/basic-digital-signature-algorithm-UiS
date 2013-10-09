package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import main.DSA;
import main.Session;
import utils.TextAreaOutputStream;

public class EntryPoint implements ActionListener {

	private JFrame frmDsaImplementation;
	private JButton globalKeysButton;
	private JPanel panel;
	private JPanel panel_1;
	private Session session;
	private JButton personalKeysBt;
	private JTextArea textPrivKey;
	private JTextArea textPubKey;
	private JLabel labelStart1;
	private JLabel label_3;
	private JLabel label_1;
	private JButton btnSign;
	private JTextArea textArea;
	private JTextArea textArea_2;
	private JTextArea textArea_3;
	private JTextArea textArea_6;
	private JButton btnVerify;
	private JTextArea textArea_5;
	private JTextArea textArea_4;
	private JTextArea textArea_7;
	private JTextArea textArea_1;
	private JLabel lblNewLabel_1;
	private JPanel panel_3;
	private JTextArea txtrHola;
	private TextAreaOutputStream taos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EntryPoint window = new EntryPoint();
					window.frmDsaImplementation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EntryPoint() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDsaImplementation = new JFrame();
		frmDsaImplementation.setTitle("DSA implementation");
		frmDsaImplementation.setBounds(100, 100, 534, 438);
		frmDsaImplementation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDsaImplementation.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 534, 332);
		frmDsaImplementation.getContentPane().add(tabbedPane);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("First step", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Initiate the session: ");
		lblNewLabel.setBounds(131, 36, 128, 16);
		panel_2.add(lblNewLabel);

		globalKeysButton = new JButton("Run");
		globalKeysButton.addActionListener(this);
		globalKeysButton.setBounds(264, 30, 75, 29);
		panel_2.add(globalKeysButton);

		JLabel lblGetPersonalKey = new JLabel("Get personal key:");
		lblGetPersonalKey.setBounds(131, 75, 128, 16);
		panel_2.add(lblGetPersonalKey);

		personalKeysBt = new JButton("Run");
		personalKeysBt.addActionListener(this);
		personalKeysBt.setBounds(264, 71, 75, 29);
		panel_2.add(personalKeysBt);

		JLabel lblSession = new JLabel("Session");
		lblSession.setBounds(385, 269, 61, 16);
		panel_2.add(lblSession);

		labelStart1 = new JLabel("not started");
		labelStart1.setBounds(438, 269, 69, 16);
		panel_2.add(labelStart1);

		JLabel lblPersonalKeys = new JLabel("Personal keys:");
		lblPersonalKeys.setBounds(17, 131, 90, 16);
		panel_2.add(lblPersonalKeys);

		JLabel lblPrivateKey = new JLabel("Private key:");
		lblPrivateKey.setBounds(60, 159, 75, 16);
		panel_2.add(lblPrivateKey);

		textPrivKey = new JTextArea();
		textPrivKey.setEditable(false);
		textPrivKey.setBounds(147, 159, 337, 35);
		panel_2.add(textPrivKey);

		JLabel lblPublicKey = new JLabel("Public key:");
		lblPublicKey.setBounds(60, 208, 75, 16);
		panel_2.add(lblPublicKey);

		textPubKey = new JTextArea();
		textPubKey.setEditable(false);
		textPubKey.setBounds(147, 208, 337, 35);
		panel_2.add(textPubKey);

		panel = new JPanel();
		tabbedPane.addTab("Sign", null, panel, null);
		tabbedPane.setEnabledAt(1, true);
		panel.setLayout(null);

		JLabel label = new JLabel("Session");
		label.setBounds(375, 265, 69, 20);
		panel.add(label);

		label_1 = new JLabel("not started");
		label_1.setBounds(427, 265, 80, 20);
		panel.add(label_1);

		JLabel lblPleaseIntroduceA = new JLabel(
				"Please introduce a text to sign: ");
		lblPleaseIntroduceA.setBounds(17, 64, 204, 16);
		panel.add(lblPleaseIntroduceA);

		textArea = new JTextArea();
		textArea.setBounds(17, 92, 473, 39);
		panel.add(textArea);

		btnSign = new JButton("Sign");
		btnSign.addActionListener(this);
		btnSign.setBounds(377, 143, 92, 29);
		panel.add(btnSign);

		JLabel label_4 = new JLabel("Personal keys:");
		label_4.setBounds(17, 6, 90, 16);
		panel.add(label_4);

		JLabel label_6 = new JLabel("Private key:");
		label_6.setBounds(60, 34, 75, 16);
		panel.add(label_6);

		textArea_2 = new JTextArea();
		textArea_2.setBounds(147, 32, 343, 20);
		panel.add(textArea_2);

		JLabel lblR = new JLabel("R: ");
		lblR.setBounds(17, 168, 61, 16);
		panel.add(lblR);

		textArea_3 = new JTextArea();
		textArea_3.setEditable(false);
		textArea_3.setBounds(17, 184, 473, 29);
		panel.add(textArea_3);

		textArea_6 = new JTextArea();
		textArea_6.setEditable(false);
		textArea_6.setBounds(17, 233, 473, 29);
		panel.add(textArea_6);

		JLabel lblS = new JLabel("S: ");
		lblS.setBounds(17, 216, 61, 16);
		panel.add(lblS);

		panel_1 = new JPanel();
		tabbedPane.addTab("Verify", null, panel_1, null);
		tabbedPane.setEnabledAt(2, true);
		panel_1.setLayout(null);

		JLabel label_2 = new JLabel("Session");
		label_2.setBounds(376, 265, 69, 20);
		panel_1.add(label_2);

		label_3 = new JLabel("not started");
		label_3.setBounds(427, 265, 80, 20);
		panel_1.add(label_3);

		JLabel label_8 = new JLabel("Personal keys:");
		label_8.setBounds(6, 6, 90, 16);
		panel_1.add(label_8);

		JLabel label_9 = new JLabel("Public key:");
		label_9.setBounds(43, 26, 75, 16);
		panel_1.add(label_9);

		textArea_1 = new JTextArea();
		textArea_1.setBounds(130, 26, 346, 16);
		panel_1.add(textArea_1);

		textArea_5 = new JTextArea();
		textArea_5.setBounds(6, 173, 470, 39);
		panel_1.add(textArea_5);

		JLabel lblPleaseIntroduceA_1 = new JLabel(
				"Please introduce a text to verify: ");
		lblPleaseIntroduceA_1.setBounds(6, 147, 222, 16);
		panel_1.add(lblPleaseIntroduceA_1);

		btnVerify = new JButton("Verify");
		btnVerify.addActionListener(this);
		btnVerify.setBounds(384, 224, 92, 29);
		panel_1.add(btnVerify);

		JLabel label_5 = new JLabel("R: ");
		label_5.setBounds(6, 50, 61, 16);
		panel_1.add(label_5);

		textArea_4 = new JTextArea();
		textArea_4.setBounds(6, 66, 470, 20);
		panel_1.add(textArea_4);

		JLabel label_7 = new JLabel("S: ");
		label_7.setBounds(6, 98, 61, 16);
		panel_1.add(label_7);

		textArea_7 = new JTextArea();
		textArea_7.setBounds(6, 115, 470, 20);
		panel_1.add(textArea_7);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel_1.setBounds(43, 229, 210, 45);
		panel_1.add(lblNewLabel_1);

		panel_3 = new JPanel();
		panel_3.setBounds(0, 323, 534, 87);
		frmDsaImplementation.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		txtrHola = new JTextArea();
		txtrHola.setBounds(6, 5, 522, 76);
		panel_3.add(txtrHola);
		taos = TextAreaOutputStream.getInstance(txtrHola);
		System.out.println("Holaaa");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == globalKeysButton) {
			session = Session.getInstance(true);
			panel.setEnabled(true);
			panel_1.setEnabled(true);
			globalKeysButton.enableInputMethods(false);
			labelStart1.setText("started");
			label_1.setText("started");
			label_3.setText("started");

		} else if (e.getSource() == personalKeysBt) {
			Pair<BigInteger, BigInteger> privateKeys = session.getPrivateKey();
			textPrivKey.setText(privateKeys.getFirst().toString());
			textPubKey.setText(privateKeys.getSecond().toString());
		} else if (e.getSource() == btnSign) {
			Pair<String, Pair<BigInteger, BigInteger>> sign = DSA.sign(false,
					textArea.getText(), session.getGlobalKeyG(),
					session.getGlobalKeyP(), session.getGlobalKeyG(),
					new BigInteger(textArea_2.getText()));
			textArea_3.setText(sign.getSecond().getFirst().toString());
			textArea_6.setText(sign.getSecond().getSecond().toString());

		} else if (e.getSource() == btnVerify) {
			Boolean res = DSA.verify(false, textArea_5.getText(),
					new BigInteger(textArea_4.getText()), new BigInteger(
							textArea_7.getText()), session.getGlobalKeyG(),
					session.getGlobalKeyP(), session.getGlobalKeyQ(),
					new BigInteger(textArea_1.getText()));
			if (res) {
				lblNewLabel_1.setText("Integrity granted!");
			} else {
				lblNewLabel_1.setText("Integrity denied!");
			}

		}
	}
}