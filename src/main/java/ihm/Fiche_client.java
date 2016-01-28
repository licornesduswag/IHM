package ihm;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import db.Database;


public class Fiche_client extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;
	JButton btnEnregistrer;
	JComboBox comboBox_1;
	JComboBox comboBox_2;
	JComboBox comboBox_3;
	JComboBox comboBox_4;
	JComboBox comboBox_5;
	JComboBox comboBox_6;
	SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
	java.sql.Date date_naissance; 
	java.sql.Date date_permis; 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Fiche_client frame = new Fiche_client();
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
	public Fiche_client() {
		setTitle("Fiche client");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 730, 520);
		this.setIconImage(new ImageIcon("car.gif").getImage());
		this.setTitle("Location de voitures - Reservation");	
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(149, 61, 135, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setForeground(Color.WHITE);
		lblNom.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNom.setBounds(57, 64, 46, 14);
		contentPane.add(lblNom);
		
		textField_1 = new JTextField();
		textField_1.setBounds(474, 61, 133, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		lblPrnom.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPrnom.setForeground(Color.WHITE);
		lblPrnom.setBounds(366, 64, 46, 14);
		contentPane.add(lblPrnom);
		
		JLabel lblDateDeNaissance = new JLabel("Date de naissance");
		lblDateDeNaissance.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblDateDeNaissance.setForeground(Color.WHITE);
		lblDateDeNaissance.setBounds(119, 140, 111, 14);
		contentPane.add(lblDateDeNaissance);
		
		btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setBounds(560, 432, 89, 23);
		btnEnregistrer.addActionListener(this);
		contentPane.add(btnEnregistrer);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_1.setBounds(305, 137, 40, 20);
		contentPane.add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Janvier", "F\u00E9vrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Ao\u00FBt", "Septembre", "Octobre", "Novembre", "D\u00E9cembre"}));
		comboBox_2.setBounds(366, 137, 86, 20);
		contentPane.add(comboBox_2);
		
		comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018"}));
		comboBox_3.setBounds(474, 137, 67, 20);
		contentPane.add(comboBox_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(305, 198, 116, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblLieuDeNaisssance = new JLabel("Lieu de naisssance");
		lblLieuDeNaisssance.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblLieuDeNaisssance.setForeground(Color.WHITE);
		lblLieuDeNaisssance.setBounds(119, 201, 111, 14);
		contentPane.add(lblLieuDeNaisssance);
		
		textField_3 = new JTextField();
		textField_3.setBounds(305, 323, 116, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(198, 387, 116, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(496, 387, 116, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNPermis = new JLabel("N\u00B0 Permis");
		lblNPermis.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNPermis.setForeground(Color.WHITE);
		lblNPermis.setHorizontalAlignment(SwingConstants.LEFT);
		lblNPermis.setBounds(217, 326, 67, 14);
		contentPane.add(lblNPermis);
		
		JLabel lblDateDlivrance = new JLabel("Date obtention");
		lblDateDlivrance.setForeground(Color.WHITE);
		lblDateDlivrance.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblDateDlivrance.setBounds(119, 269, 97, 14);
		contentPane.add(lblDateDlivrance);
		
		JLabel lblNewLabel = new JLabel("Lieu d'obtention");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(54, 390, 99, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblVilleDlivrance = new JLabel("Ville d'obtention");
		lblVilleDlivrance.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblVilleDlivrance.setForeground(Color.WHITE);
		lblVilleDlivrance.setBounds(372, 390, 97, 14);
		contentPane.add(lblVilleDlivrance);
		
		comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_4.setBounds(305, 266, 40, 20);
		contentPane.add(comboBox_4);
		
		comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"Janvier", "F\u00E9vrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Ao\u00FBt", "Septembre", "Octobre", "Novembre", "D\u00E9cembre"}));
		comboBox_5.setBounds(366, 266, 86, 20);
		contentPane.add(comboBox_5);
		
		comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018"}));
		comboBox_6.setBounds(474, 266, 67, 20);
		contentPane.add(comboBox_6);
	}
	
	public void wrongName(){
		JOptionPane.showMessageDialog(new JFrame(), "Le nom et le prénom sont déjà utilisés", "Erreur - Inscription", JOptionPane.ERROR_MESSAGE);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		 
		if(source == btnEnregistrer){
			String recu_1 = new String();
			recu_1 =  comboBox_3.getSelectedItem().toString()+"-" + comboBox_2.getSelectedIndex()+1 +"-"+ comboBox_1.getSelectedItem().toString();
			String recu_2 = new String();
			recu_2 =  comboBox_6.getSelectedItem().toString()+"-" + comboBox_5.getSelectedIndex()+1 +"-"+ comboBox_4.getSelectedItem().toString();
			try {
				Date parsed = dt.parse(recu_1);
				date_naissance = new java.sql.Date(parsed.getTime());
				parsed = dt.parse(recu_2);
				date_permis = new java.sql.Date(parsed.getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if(!Database.addClient(textField.getText(), textField_1.getText(), date_naissance, textField_2.getText(), textField_3.getText(), date_permis, textField_5.getText(), textField_6.getText())){
					wrongName();
				}
				else{
					this.dispose();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
