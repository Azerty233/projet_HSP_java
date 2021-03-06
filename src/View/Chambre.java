package View;

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

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.dbconnexion.Database;

import Manager.Manager_connexion;

import javax.swing.JComboBox;



/*Application r?alis?e du 01 au 05 Juillet 2020 ? N'djam?na au Tchad par
 *  TARGOTO CHRISTIAN
 *  Contact: 23560316682 / ct@chrislink.net / ctargoto@gmail.com / 23592957308 */

public class Chambre extends JFrame {
	
	protected Shell shlMenuAdmin;

	
	Database cn=new Database();
	Statement st;
	ResultSet rst;
	JTable tb1;
	private JFrame frame;

	JScrollPane scrl1;
	JLabel lbtitre,lbnumporte,lbprix;
	JTextField tfnumporte,tfNom, jcomboBox;
	JButton badd,bresv;
	private JTextField textTraitement;
	private JButton btnNewButton;
	
	
	public Chambre (){
		
		
		this.setTitle("Chambre");
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
		//label num?ro porte

		lbnumporte=new JLabel("Num?ro porte");
		lbnumporte.setBounds(40,50,110,25);
		lbnumporte.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbnumporte);
		//textfield num?ro porte
		tfnumporte=new JTextField();
		tfnumporte.setBounds(190,50,100,25);
		pn.add(tfnumporte);
		//label prix par jour
		lbprix=new JLabel("Patient");
		lbprix.setBounds(40,90,140,25);
		lbprix.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbprix);
		//textfield prix par jour
		tfNom=new JTextField();
		tfNom.setBounds(190,90,100,25);
		pn.add(tfNom);

		//bouton ajout chambre
		badd=new JButton("Ajouter");
		badd.setBounds(190,175,100,25);
		badd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				String a=tfnumporte.getText(),b=tfNom.getText(), c=textTraitement.getText();

				String sql="insert into chambre(numporte,nom,traitement) values('"+a+"','"+b+"','"+c+"')";
				try{
					st=cn.DbConnexion().createStatement();
					st.executeUpdate(sql);
					JOptionPane.showMessageDialog(null,"Ajout r?ussi !");
					dispose();
					comboBoxMedicaments ch=new comboBoxMedicaments();
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

		textTraitement = new JTextField();
		textTraitement.setBounds(190, 125, 100, 40);
		pn.add(textTraitement);
		
		btnNewButton = new JButton("Retour");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				shlMenuAdmin.close();
				try
				{
					menu_Infermiere window = new menu_Infermiere();
					window.open();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(404, 50, 85, 21);
		pn.add(btnNewButton);


		String sql="select numporte,nom,traitement from chambre";

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
