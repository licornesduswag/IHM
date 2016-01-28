package ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.Database;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;
	private JLabel lblPass;
	private JButton connectButton;
	
	public void wrongId(){
		JOptionPane.showMessageDialog(new JFrame(), "Le nom d'utilisateur et/ou le mot de passe est erroné", "Erreur - Connexion", JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * Create the frame.
	 */
	public Login() {
		Database.db_connect();
		this.setIconImage(new ImageIcon("car.gif").getImage());
		this.setTitle("Location de voitures - Login");
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
		pass.setHorizontalAlignment(SwingConstants.CENTER);
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
		this.connectButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
					if(connect()){
						ListeReservations lr = new ListeReservations();
						lr.setVisible(true);
						dispose();
					}
					else{
						wrongId();
					}
			}
		});
	}

	public boolean connect(){
		try {
			return Database.connectUser(user.getText(), String.valueOf(pass.getPassword()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
