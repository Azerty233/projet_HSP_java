package appli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;


import controller.Manager_connexion;
import org.eclipse.swt.widgets.Composite;

public class mdpOublie
{

	/**
	* fondation du contenu de la fen"etre
	 * @wbp.parser.entryPoint
	*/
	protected void createContents()
	{
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(494, 381);
		shell.setText("Hopital de Paris | Connexion");
		//Identifiant
		textEmail = new Text(shell, SWT.BORDER);
		textEmail.setBounds(133, 94, 215, 35);
	
		textMotdePasse = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		textMotdePasse.setBounds(133, 177, 215, 34);
	
		Label lblErreur = new Label(shell, SWT.NONE);
		lblErreur.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblErreur.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblErreur.setBounds(128, 275, 317, 25);
		lblErreur.setText("Erreur dans l'identifiant ou le mot de passe");
		lblErreur.setVisible(false);
			
			Composite composite_1 = new Composite(shell, SWT.NONE);
			composite_1.setBackground(SWTResourceManager.getColor(100, 149, 237));
			composite_1.setBounds(0, 0, 486, 45);
			
			txtEspaceHpitalDe = new Text(composite_1, SWT.NONE);
			txtEspaceHpitalDe.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			txtEspaceHpitalDe.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
			txtEspaceHpitalDe.setFont(SWTResourceManager.getFont("Segoe UI Historic", 14, SWT.BOLD));
			txtEspaceHpitalDe.setText("Espace H\u00F4pital de Paris");
			txtEspaceHpitalDe.setBounds(130, 10, 249, 25);
			
			Composite composite_1_1 = new Composite(shell, SWT.NONE);
			composite_1_1.setBackground(SWTResourceManager.getColor(100, 149, 237));
			composite_1_1.setBounds(0, 301, 486, 45);
			
			txtApplication = new Text(composite_1_1, SWT.NONE);
			txtApplication.setText("@2022 - Application mobile de l'h\u00F4pital de Paris.\r\nTous droits r\u00E9serv\u00E9s.\r\nEspace r\u00E9serv\u00E9 aux collaborateurs.");
			txtApplication.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			txtApplication.setFont(SWTResourceManager.getFont("Segoe UI Historic", 7, SWT.NORMAL));
			txtApplication.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
			txtApplication.setBounds(10, 10, 449, 25);
			
			Label lblMotDePasse = new Label(shell, SWT.NONE);
			lblMotDePasse.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
			lblMotDePasse.setBounds(133, 66, 97, 20);
			lblMotDePasse.setText("Mot de passe");
			
			Label lblConfirmationMotDe = new Label(shell, SWT.NONE);
			lblConfirmationMotDe.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
			lblConfirmationMotDe.setBounds(133, 151, 188, 20);
			lblConfirmationMotDe.setText("Confirmation mot de passe");
			
			Button btnRenitialiserVotreMot = new Button(shell, SWT.NONE);
			btnRenitialiserVotreMot.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					
					String pstr = textEmail.getText();
	                try {
	                    System.out.println("update mdp email ");
	                    System.out.println("update mdp");

	                    Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/projet_java?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	                        "root", "");
	                    
	                    PreparedStatement st = (PreparedStatement) con
	                        .prepareStatement("Update utilisateurs set mdp=? where email=?");

	                    st.setString(1, pstr);
	                    st.executeUpdate();
	                    JOptionPane.showInputDialog(btnRenitialiserVotreMot, "Password has been successfully changed");

	                } catch (SQLException sqlException) {
	                    sqlException.printStackTrace();
	                }
				}
			});
			btnRenitialiserVotreMot.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
			btnRenitialiserVotreMot.setBounds(128, 239, 233, 30);
			btnRenitialiserVotreMot.setText("Renitialiser votre mot de passe");
	
	}

	protected Shell shell;
	private Text textEmail;
	private Text textMotdePasse;
	private Text txtEspaceHpitalDe;
	private Text txtApplication;

	/**
	* Launch the application.
	* @param args
	 * @wbp.parser.entryPoint
	*/
	public static void main(String[] args)
	{
		try
		{
			Connexion window = new Connexion();
			window.open();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	* Ouverture de la fenetre.
	*/
	public void open()
	{
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}
}
