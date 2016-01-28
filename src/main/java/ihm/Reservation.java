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
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import db.Database;

public class Reservation extends JFrame implements ActionListener{

	private JPanel contentPane;
	JTextField textField;
	JTextField textField_1;
	int id = -1;
	JButton btnValider;
	JComboBox comboBox;
	JComboBox comboBox_1;
	JComboBox comboBox_2;
	JComboBox comboBox_3;
	JComboBox comboBox_4;
	JComboBox comboBox_5;
	JComboBox comboBox_6;
	SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
	java.sql.Date date_debut;
	java.sql.Date date_fin;
	

	/**
	 * Create the frame.
	 */
	public Reservation() {
		setTitle("Nouvelle r\u00E9servation");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 512, 330);
		this.setIconImage(new ImageIcon("car.gif").getImage());
		this.setTitle("Location de voitures - Reservation");	
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 105, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		try {
			comboBox.setModel(new DefaultComboBoxModel(Database.getMatricule()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboBox.setBounds(225, 114, 136, 20);
		contentPane.add(comboBox);
		
		JLabel lblVoitureRserv = new JLabel("Voiture r\u00E9serv\u00E9e");
		lblVoitureRserv.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblVoitureRserv.setForeground(new Color(255, 255, 255));
		lblVoitureRserv.setBounds(80, 116, 125, 14);
		contentPane.add(lblVoitureRserv);
		
		textField = new JTextField();
		textField.setBackground(new Color(255, 255, 255));
		textField.setBounds(225, 31, 136, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(255, 255, 255));
		textField_1.setBounds(225, 72, 136, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPrenomDuClient = new JLabel("Prenom du client");
		lblPrenomDuClient.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblPrenomDuClient.setForeground(new Color(255, 255, 255));
		lblPrenomDuClient.setBackground(Color.WHITE);
		lblPrenomDuClient.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrenomDuClient.setBounds(80, 33, 125, 14);
		contentPane.add(lblPrenomDuClient);
		
		JLabel lblNomDuClient = new JLabel("Nom du client");
		lblNomDuClient.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNomDuClient.setForeground(new Color(255, 255, 255));
		lblNomDuClient.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomDuClient.setBounds(91, 74, 114, 14);
		contentPane.add(lblNomDuClient);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBackground(new Color(255, 255, 255));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_1.setBounds(225, 152, 40, 20);
		contentPane.add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Janvier", "F\u00E9vrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Ao\u00FBt", "Septembre", "Octobre", "Novembre", "D\u00E9cembre"}));
		comboBox_2.setBounds(275, 152, 86, 20);
		contentPane.add(comboBox_2);
		
		comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"2015", "2016", "2017", "2018", "2019", "2020"}));		comboBox_3.setBounds(373, 152, 67, 20);
		contentPane.add(comboBox_3);
		
		comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_4.setBounds(225, 183, 40, 20);
		contentPane.add(comboBox_4);
		
		comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"Janvier", "F\u00E9vrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Ao\u00FBt", "Septembre", "Octobre", "Novembre", "D\u00E9cembre"}));
		comboBox_5.setBounds(275, 183, 86, 20);
		contentPane.add(comboBox_5);
		
		comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"2015", "2016", "2017", "2018", "2019", "2020"}));
		comboBox_6.setBounds(373, 183, 67, 20);
		contentPane.add(comboBox_6);
		
		JLabel lblNewLabel = new JLabel("Date de d\u00E9but de location");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(0, 154, 205, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblDateDeFin = new JLabel("Date de fin de location");
		lblDateDeFin.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblDateDeFin.setForeground(new Color(255, 255, 255));
		lblDateDeFin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateDeFin.setBounds(28, 185, 177, 14);
		contentPane.add(lblDateDeFin);
		
		btnValider = new JButton("Valider");
		btnValider.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnValider.setForeground(new Color(65, 105, 225));
		btnValider.setBackground(new Color(65, 105, 225));
		btnValider.addActionListener(this);
		btnValider.setBounds(382, 234, 80, 25);
		contentPane.add(btnValider);
		
	}
	
	public void wrongDate(){
		JOptionPane.showMessageDialog(new JFrame(), "La date de début doit être antérieure à la date de fin !", "Erreur - Date", JOptionPane.ERROR_MESSAGE);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		 
		if(source == btnValider){
			String debut = new String();
			String fin = new String();
			debut =  comboBox_3.getSelectedItem().toString()+"-" + comboBox_2.getSelectedIndex()+1 +"-"+ comboBox_1.getSelectedItem().toString();
			fin =  comboBox_6.getSelectedItem().toString()+"-" + comboBox_5.getSelectedIndex()+1 +"-"+ comboBox_4.getSelectedItem().toString();
			try {
				Date parsed = dt.parse(debut);
				date_debut = new java.sql.Date(parsed.getTime());
				parsed = dt.parse(fin);
				date_fin = new java.sql.Date(parsed.getTime());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			if(date_debut.compareTo(date_fin) > 0){
				wrongDate();
			}
			else{
				try {
					if(id == -1){
						if(!Database.addResa(textField_1.getText(), textField.getText(), comboBox.getSelectedItem().toString(), date_debut, date_fin)){
							Fiche_client fc = new Fiche_client();
							fc.setVisible(true);
						}
						else{
							this.dispose();
						}
					}
					else{
						Database.editResa(id, textField_1.getText(), textField.getText(), comboBox.getSelectedItem().toString(), date_debut, date_fin);
						this.dispose();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
