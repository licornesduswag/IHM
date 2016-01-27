package ihm;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;
	private JLabel lblPass;
	private JButton connectButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		user = new JTextField();
		user.setHorizontalAlignment(SwingConstants.CENTER);
		user.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		user.setBounds(125, 125, 150, 30);
		contentPane.add(user);
		user.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(125, 200, 150, 30);
		contentPane.add(pass);
		
		JLabel lblId = new JLabel("Identifiant");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblId.setBackground(Color.WHITE);
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(125, 100, 150, 14);
		contentPane.add(lblId);
		
		lblPass = new JLabel("Mot de passe");
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblPass.setForeground(Color.WHITE);
		lblPass.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblPass.setBackground(Color.WHITE);
		lblPass.setBounds(125, 175, 150, 14);
		contentPane.add(lblPass);
		
		connectButton = new JButton("Connexion");
		connectButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		connectButton.setBounds(125, 275, 150, 30);
		contentPane.add(connectButton);
		connectButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
								
			}
		});
	}

}
