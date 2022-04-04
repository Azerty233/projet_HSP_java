package appli;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.dbconnexion.Database;

import Manager.Manager_connexion;

import javax.swing.JComboBox;


/*Application réalisée du 01 au 05 Juillet 2020 à N'djaména au Tchad par
 *  TARGOTO CHRISTIAN
 *  Contact: 23560316682 / ct@chrislink.net / ctargoto@gmail.com / 23592957308 */

public class Chambre extends JFrame {
	Database cn=new Database();
	Statement st;
	ResultSet rst;
	JTable tb1;
	private JFrame frame;

	JScrollPane scrl1;
	JLabel lbtitre,lbnumporte,lbprix;
	JTextField tfnumporte,tfprix, jcomboBox;
	JButton badd,bresv;
	private JTextField textField;
	public Chambre (){
		
		
		this.setTitle("chcode_appli");
		this.setSize(553,500);
		this.setLocationRelativeTo(null);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		pn.setBackground(Color.BLUE);
		getContentPane().add(pn);
		lbtitre=new JLabel("Enregistrement des chambres");
		lbtitre.setBounds(30,10,400,30);
		lbtitre.setFont(new Font("Arial",Font.BOLD,20));
		pn.add(lbtitre);
		//label numéro porte

		lbnumporte=new JLabel("Numéro porte");
		lbnumporte.setBounds(40,50,110,25);
		lbnumporte.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbnumporte);
		//textfield numéro porte
		tfnumporte=new JTextField();
		tfnumporte.setBounds(190,50,100,25);
		pn.add(tfnumporte);
		//label prix par jour
		lbprix=new JLabel("Patient");
		lbprix.setBounds(40,90,140,25);
		lbprix.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbprix);
		//textfield prix par jour
		tfprix=new JTextField();
		tfprix.setBounds(190,90,100,25);
		pn.add(tfprix);

		//bouton ajout chambre
		badd=new JButton("Ajouter");
		badd.setBounds(190,175,100,25);
		badd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				String a=tfnumporte.getText(),b=tfprix.getText(), c=textField.getText();

				String sql="insert into chambree(numporte,nom,traitement) values('"+a+"','"+b+"','"+c+"')";
				try{
					st=cn.DbConnexion().createStatement();
					st.executeUpdate(sql);
					JOptionPane.showMessageDialog(null,"Ajout réussi !");
					dispose();
					Chambre ch=new Chambre();
					ch.setVisible(true);
				}
				catch(SQLException ex){
					JOptionPane.showMessageDialog(null,"Impossible d'ajouter !",null,JOptionPane.ERROR_MESSAGE);  
				}

			}
		});
		pn.add(badd);
		////
		DefaultTableModel df=new DefaultTableModel();
		init();
		df.addColumn("NUMERO PORTE");
		df.addColumn("PATIENT");
		df.addColumn("TRAITEMENT");

		tb1.setModel(df);
		pn.add(scrl1);

		JLabel lblTraitement = new JLabel("Traitement");
		lblTraitement.setFont(new Font("Arial", Font.BOLD, 16));
		lblTraitement.setBounds(40, 125, 140, 25);
		pn.add(lblTraitement);

		textField = new JTextField();
		textField.setBounds(190, 125, 100, 40);
		pn.add(textField);


		//String sql="select * from vente where datediff(datev,now())=0";
		String sql="select numporte,nom,traitement from chambree";

		cn=new Database();
		try{
			st=cn.DbConnexion().createStatement();
			rst=st.executeQuery(sql);
			while(rst.next()){
				df.addRow(new Object[]{
						rst.getString("numporte"),
						rst.getString("nom"),
						rst.getString("traitement")


				});
			}
		}
		catch(SQLException ex){

		}
	}

	private void init(){
		tb1=new JTable();
		scrl1=new JScrollPane();
		scrl1.setViewportView(tb1);
		scrl1.setBounds(20,210,500,230);
	}
	public void run() {
		frame.setVisible(true);
		
	}
	
	

}
