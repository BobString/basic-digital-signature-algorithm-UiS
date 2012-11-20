package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class EntryPoint {

	private JFrame frmDsaImplementation;

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
		frmDsaImplementation.setBounds(100, 100, 450, 300);
		frmDsaImplementation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmDsaImplementation.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Private key", null, panel_2, null);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Sign", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Verify", null, panel_1, null);
	}
}
