package ihm;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import db.Database;
import java.awt.Font;

public class ListeReservations extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JTable table;
	JButton ajout;
	JButton edit;
	JButton cancel;
	JButton refresh;
	String[][] data;
	String[] columns = {"ID", "Nom", "Prenom", "Immatriculation","Date début", "Date fin"};	
	JScrollPane scrollPane = new JScrollPane();
	public ListeReservations() {
		getContentPane().setBackground(new Color(65, 105, 225));
		getContentPane().setLayout(null);
		this.setIconImage(new ImageIcon("car.gif").getImage());
		this.setTitle("Location de voitures - Login");		
		
		scrollPane.setBounds(10, 11, 1244, 664);
		getContentPane().add(scrollPane);
		
		refreshData();
		
		ajout = new JButton("Ajouter Réservation");
		ajout.setForeground(new Color(65, 105, 225));
		ajout.setFont(new Font("Calibri", Font.PLAIN, 14));
		ajout.setBackground(new Color(65, 105, 225));
		ajout.setBounds(10, 686, 200, 32);
		getContentPane().add(ajout);
		ajout.addActionListener(this);
		
		edit = new JButton("Modifier Réservation");
		edit.setForeground(new Color(70, 130, 180));
		edit.setFont(new Font("Calibri", Font.PLAIN, 14));
		edit.setBackground(new Color(65, 105, 225));
		edit.setBounds(220, 686, 200, 32);
		getContentPane().add(edit);
		edit.addActionListener(this);
		
		cancel = new JButton("Annuler réservation");
		cancel.setFont(new Font("Calibri", Font.PLAIN, 14));
		cancel.setForeground(new Color(65, 105, 225));
		cancel.setBackground(new Color(65, 105, 225));
		cancel.setBounds(430, 686, 200, 32);
		getContentPane().add(cancel);
		cancel.addActionListener(this);
		
		refresh = new JButton("Rafaîchir");
		refresh.setForeground(new Color(65, 105, 225));
		refresh.setFont(new Font("Calibri", Font.PLAIN, 14));
		refresh.setBackground(new Color(65, 105, 225));
		refresh.setBounds(1055, 686, 200, 32);
		getContentPane().add(refresh);
		refresh.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 768);
	}
	
	public void refreshData(){
		
		try {
			data = Database.getResa();
			table = new JTable(data, columns);
			getContentPane().add(table);
			table.setBounds(10, 11, 1244, 707);
			table.setRowHeight(32);
			table.setAutoCreateRowSorter(true);
			scrollPane.setViewportView(table);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == ajout){
			Reservation r = new Reservation();
			r.setVisible(true);
		}
		else if(source == edit){
			int row = table.getSelectedRow();
			if(row != -1){
				int id = Integer.parseInt((String)table.getValueAt(row, 0));
				String nom = (String)table.getValueAt(row, 1);
				String prenom = (String)table.getValueAt(row, 2);
				Reservation r = new Reservation();
				r.textField.setText(prenom);
				r.textField_1.setText(nom);
				r.id = id;
				r.setVisible(true);
			}
		}
		else if(source == cancel){
			int row = table.getSelectedRow();
			int id = Integer.parseInt((String)table.getValueAt(row, 0));
			try {
				Database.removeResa(id);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else if(source == refresh){
			refreshData();
		}
	}
}
