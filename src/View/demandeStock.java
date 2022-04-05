package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import org.eclipse.swt.widgets.Shell;

import Manager.Manager_connexion;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class demandeStock extends JFrame{

	private JFrame frame;
	private JTextField textObject;
	private JTextField textCorps;
	
	protected Shell shlMenuAdmin;


	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public demandeStock() {
		
		
		this.setTitle("Demande de stock");
		this.setSize(553,500);
		this.setLocationRelativeTo(null);
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Demande de stock");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(124, 53, 139, 30);
		frame.getContentPane().add(lblNewLabel);
		
		textObject = new JTextField();
		textObject.setBounds(124, 88, 139, 19);
		frame.getContentPane().add(textObject);
		textObject.setColumns(10);
		
		textCorps = new JTextField();
		textCorps.setBounds(124, 117, 139, 69);
		frame.getContentPane().add(textCorps);
		textCorps.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Object :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(56, 91, 64, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Envoyer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Manager_connexion sendDemande = new Manager_connexion();
				try {
					Manager_connexion.sendDemande(textObject.getText(), textCorps.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
			}
		});
		btnNewButton.setBounds(151, 196, 85, 21);
		frame.getContentPane().add(btnNewButton);	}

	/**
	 * Initialize the contents of the frame.
	 */
	

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}


}
