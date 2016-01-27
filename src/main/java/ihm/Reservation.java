package ihm;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

import db.Database;

public class Reservation extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservation frame = new Reservation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Reservation() {
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1244, 664);
		getContentPane().add(scrollPane);
		String[] columns = {"Client", "Immatriculation","Date début", "Date fin", "Prix"};	
		String[][] data;
		try {
			Database.db_connect();
			data = Database.getResa();
			table = new JTable(data, columns);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		table.setBounds(10, 11, 1244, 707);
		table.setRowHeight(32);
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		JButton ajout = new JButton("Ajouter Réservation");
		ajout.setBounds(10, 686, 200, 32);
		getContentPane().add(ajout);
		
		JButton edit = new JButton("Modifier Réservation");
		edit.setBounds(220, 686, 200, 32);
		getContentPane().add(edit);
		
		JButton cancel = new JButton("Annuler réservation");
		cancel.setBounds(430, 686, 200, 32);
		getContentPane().add(cancel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 768);
	}
}
